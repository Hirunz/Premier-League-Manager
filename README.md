# Premier-League-Manager

Angular + Play Framework project

The premier league manager is a system which is demonstrates the functionality of a football league system.
Frontend of the GUI is written in angular & typescript while the backend is written using java.
Backend has a console which can be operated using command prompt (or terminal) to maintain the GUI.

Basic functionalities of the system's CLI interface includes,
     * Adding, deleting a Football club.
     * Adding a played match.
     * Updating scoreboard.
     * Data persistance using file handling.
     * open the GUI
     
Basic functioalities of the angular frontend are,
     * Viewing both league table and match tables.
     * Adding a random match where an entire match is generated with scores.
          * Maximum of two matches can be played between two teams.
          * Maimum goals per teams is a random between 0 and average played score in football premier league history.
          * Once all matches are played, the season is over.
     * Searching a match using the match date.

All inputs are validated and the system currently operates via localhost but hosting is possible.
The GUI system can be opened via a single command.
     * Open a terminal with path set to the play project( folder where the ui-build.sbt file exists).
     * Run "sbt run" command.
The CLI system can be opened using an IDE Terminal.
      * The root class can be found inside the classes folder located in ../Angular/cw/app/classes folder.
      * Run the Console.java file.

To operate the system with only CLI, run the Console.java file in the Console folder.
          
          
     
  
