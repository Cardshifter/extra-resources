/**
 * @author github.com/Phrancis
 * Created 2015-07-07
 * Please follow the instructions to export cards from the game to JSON before using this script. 
 */

import groovy.json.JsonSlurper

// edit this path to the actual file path and name
def inputFile = new File("my/file/path/game-1.json")

def json = new JsonSlurper().parse(inputFile)

def outerListStyle = "class=\"no-bullets\""
def detailStyle = "class=\"card-detail\""

def cardHeader = 'h4'
def mechCardHeader = 'h4 class=\"mech-card-header\"'
def endMechCardHeader = 'h4'

def quoteStyle = '<span class=\"quote-style\">\"'
def endQuoteStyle = '\"</span>'

def mechQuoteStyle = '<code class=\"mech-quote-style\"># '
def endMechQuoteStyle = '</code>'

// Bio cards
println '<!-- BIO CARDS -->\n'
println '<!-- Add the resulting HTML to /io.web/src/main/webapp/WEB-INF/cyborg-chronicles/cards-bio.html -->\n'
json.each {
    if (it."type" == "Bio") {
        println "<${cardHeader}>${it."name"} ${it."ATTACK"} / ${it."HEALTH"}</${cardHeader}>"
        println "<ul ${outerListStyle}>"
        println "  <li>${quoteStyle}${it."flavor"}${endQuoteStyle}</li>"
            // stats details
            println "  <li><ul>"
            println "    <li ${detailStyle}>Mana cost: ${it."MANA_COST"}</li>"
            // check for Rush
            if (it."SICKNESS" == 0) {
                println "    <li ${detailStyle}>Rush</li>"
            }
            // check for Ranged
            if (it."DENY_COUNTERATTACK" == 1) {
                println "    <li ${detailStyle}>Ranged</li>"
            }
            // check for NoAttack
            if (it."ATTACK_AVAILABLE" == 0) {
                println "    <li ${detailStyle}>Cannot attack</li>"
            }
            // end stats detail
            println"  </ul></li>"
        // check for card effect
        if (it."description") {
            println "  <li>Effect: ${it."description"}</li>"
        }
        // end of card
        println "</ul>\n"
    }
}

// Mech cards
println '<!-- MECH CARDS -->\n'
println '<!-- Add the resulting HTML to /io.web/src/main/webapp/WEB-INF/cyborg-chronicles/cards-mech.html -->\n'
json.each {
    if (it."type" == "Mech") {
        println "<${mechCardHeader}>${it."name"} ${it."ATTACK"} / ${it."HEALTH"}</${endMechCardHeader}>"
        println "<ul ${outerListStyle}>"
        println "  <li>${mechQuoteStyle}${it."flavor"}${endMechQuoteStyle}</li>"
            // stats details
            println "  <li><ul>"
            println "    <li ${detailStyle}>Mana cost: ${it."MANA_COST"}</li>"
            println "    <li ${detailStyle}>Scrap value: ${it."SCRAP"}</li>"
            // check for Rush
            if (it."SICKNESS" == 0) {
                println "    <li ${detailStyle}>Rush</li>"
            }
            // check for Ranged
            if (it."DENY_COUNTERATTACK" == 1) {
                println "    <li ${detailStyle}>Ranged</li>"
            }
            // check for NoAttack
            if (it."ATTACK_AVAILABLE" == 0) {
                println "    <li ${detailStyle}>Cannot attack</li>"
            }
            // end stats detail
            println"  </ul></li>"
        // check for card effect
        if (it."description") {
            println "  <li>Effect: ${it."description"}</li>"
        }
        // end of card
        println "</ul>\n"
    }
}

// Other cards
println '<!-- OTHER CARDS -->\n'
println '<!-- Add the resulting HTML to /io.web/src/main/webapp/WEB-INF/cyborg-chronicles/cards-cybernetic.html -->\n'
json.each {
    if (it."type" != "Bio" && it."type" != "Mech") {
        println "<${cardHeader}>${it."name"}</${cardHeader}>"
        println "<ul ${outerListStyle}>"
        println "  <li>${quoteStyle}${it."flavor"}${endQuoteStyle}</li>"
            //card detail
            println "  <li><ul>"
            println "    <li ${detailStyle}>Scrap cost: ${it."SCRAP_COST"}</li>"
            // check for attack change
            def attack = it."ATTACK"
                if (attack) {
                    if (attack > 0) {
                        println "    <li ${detailStyle}>Attack +${attack}</li>"
                    } else if (attack < 0) {
                        println "    <li ${detailStyle}>Attack -${attack}</li>"
                    }
            }
            // check for health change
            def health = it."HEALTH"
            if (health) {
                if (health > 0) {
                    println "    <li ${detailStyle}>Health +${health}</li>"
                } else if (health < 0) {
                    println "    <li ${detailStyle}>Health -${health}</li>"
                }
            }
            if (it."description") {
                println "    <li ${detailStyle}>Effect: ${it."description"}</li>"
            }
            // end card detail
            println "  </ul></li>"
        // end card
        println "</ul>\n"
    }
}
