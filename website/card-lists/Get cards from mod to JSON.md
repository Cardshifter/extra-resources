To get a JSON list of all cards for a mod: 

- First either `./gradlew clean dist` on a working branch, or, download the latest release. 

- Start the server `java -jar cardshifter-server-<version>.jar`

- Start either of the clients (JavaFX or libGDX)

- Invite an AI to a game

- After the deck builder comes up, go back to the server terminal and search/look for `gameId=n` which should be right after the `Include cardset` notifications.

- Go back to the game lobby and type this chat message: `/export -game n` where `n` is the `gameId`. 

- This will create a file named `game-n.json` in the game folder.

- Run one of the Groovy scripts in this folder to parse the file to HTML, modifying the file path as needed.

- Add the generated HTML (from the console output) into the HTML files on the website, test on local Tomcat, then commit changes to the io.web repo. 

- When website server needs refreshed, just ping @SimonAndréForsberg in the [chat room](http://chat.stackexchange.com/rooms/16134/tcg-creation).
