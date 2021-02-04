import java.util.InputMismatchException;
import java.util.Scanner;

abstract public class Console{
    public static final Scanner sc= new Scanner(System.in);
    // a final premier league manager object to be used within the entire program.
    public static final LeagueManager manager=new PremierLeagueManager();
    public static void main(String[] args) {



        // a main loop to run the console menu
        main:
        while (true){
            manager.readFromFile();
            //introduction with instructions
            intro();
            int option;
            System.out.print("\nSelect an option to continue: ");
            if(sc.hasNextInt()){
                // checks whether the next in the input stream is an integer.
                //if true, then checks whether it belongs to an option.
                option = sc.nextInt();
                if (option<1 || option >7){
                    System.out.println(ConsoleLog.error("Invalid option. Please enter again.","Console => main \n"));
                    continue main;
                }
            }
            else{
                System.out.println(ConsoleLog.error("Invalid option. Please enter integers only.","Console => main \n"));
                sc.next();
                continue main;
            }

            // switch block for the options
            switch(option){
                case 1: addNewClub();
                        break;
                case 2: deleteClub();
                        break;
                case 3: addPlayedMatch();
                        break;
                case 4: displayClubStatistics();
                        break;
                case 5: manager.displayLeagueTable();
                        break;
                case 6: openGUI();
                        break;
                case 7: manager.saveInFile();
                        System.out.println("\n Thank you for using the system\n System exiting...");
                        break main;
            }

        }
        sc.close();
    }

    private static void addNewClub() {
        try{
            // club id must be an integer. If the input is not matching,
            // then this try block will handle the exception.
            // aquiring necessary details to make a club.
            // Since this is a premier league manager, there is no need to include other clubs such as university
            // and school football clubs.
            // the exact clubId validation is done in the PremierLeagueManager class
        System.out.print("\nEnter club ID: ");
        int clubId=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter club Name: ");
        String clubName=sc.nextLine();
        System.out.print("Enter club Location: ");
        String clubLocation=sc.nextLine();

        FootballClub club=new FootballClub(clubId, clubName, clubLocation);
        manager.addFootballClub(club);
        manager.saveInFile();
        }catch(InputMismatchException e){
            System.out.println(ConsoleLog.error("Club ID must be an integer.","Console.addNewClub()\n"));
            sc.next();
        }


    }

    private static void deleteClub() {
        try{
            // the input may not be an integer.
            // a try-catch block to handle if an error occurs.
            // getting the clubId to delete.
        System.out.print("\nEnter club Id: ");
        int clubId=sc.nextInt();
        System.out.println(ConsoleLog.warn("You are about to delete the following club \n"));
        manager.displayClubStat(clubId);
        // if the club exists in the system,
        // the system will display statistics and ask the user to confirm.
        System.out.print("\nPlease enter the club ID again to confirm: ");
        int clubIdCopy=sc.nextInt();

        if(clubId==clubIdCopy){
            manager.deleteFootballClub(clubId);
            manager.saveInFile();
        }
        else{
            System.out.println(ConsoleLog.warn("Confirmation failed. Please try again.\n"));
        }
        }catch(InputMismatchException e){
            System.out.println(ConsoleLog.error("Club ID must be an integer.",
                    "Console.deleteFootballClub()\n"));
            sc.next();
        }
    }

    private static void addPlayedMatch() {
    
        try{
            // another try catch block to handle input mismatch exception.
            // geting the relevant data to create an match.
        System.out.print("\nEnter First Club Id: ");
        int club1=sc.nextInt();
        System.out.print("Enter number of goals scored by the first club: ");
        int club1GoalsScored= sc.nextInt();
        System.out.print("Enter Second Club Id: ");
        int club2=sc.nextInt();
        System.out.print("Enter number of goals scored by the second club: ");
        int club2GoalsScored= sc.nextInt();

        System.out.println();

        System.out.println("Enter match date");
        System.out.print("Day: ");
        String day= sc.next();
        System.out.print("Month: ");
        String month= sc.next();
        System.out.print("Year: ");
        String year= sc.next();

	if (!Date.isAValidDate(day,month,year)){
		System.out.println(ConsoleLog.error("Invalid date.","Console.addPlayedMatch()\n"));
		return;
        }

        Match match=new Match(club1, club2, club1GoalsScored, club2GoalsScored, new Date(day, month, year));
        // the created match will be passed to the manager to do the necessary validations
            // and add it to the played matches.
       manager.addPlayedMatch(match);
            System.out.println("\n Match added successfully.");
       manager.saveInFile();
        
    }catch(InputMismatchException e){
        System.out.println(ConsoleLog.error("Invalid Input. club IDs and goals scored must be integer."
                ,"Console.addPlayedMatch()\n"));

    }

        

        
    

    }

    private static void displayClubStatistics() {
        try{
            // a method to display the club statistics.
        System.out.print("Enter club Id: ");
        int clubId=sc.nextInt();

        manager.displayClubStat(clubId);
        }catch(InputMismatchException e){
            System.out.println(ConsoleLog.error("Club ID must be an integer.",
                    "Console.displayClubStatistics()\n"));
            sc.next();
        }
    }




    public static void intro() {
        // introduction and instructions
        System.out.println("-----------------------------------------------------------------------------------------" +
                "---------------------------------------------------------------------------");
        System.out.println("\nWelcome To Premier League Manager");
        System.out.println("Please choose an option from following");

        System.out.println("Enter 1 - Add a club to the Premier League");
        System.out.println("Enter 2 - Delete a club from the Premier League");
        System.out.println("Enter 3 - Add Played Match");
        System.out.println("Enter 4 - Display Club Statistics");
        System.out.println("Enter 5 - Display League Table");
        System.out.println("Enter 6 - Open GUI");
        System.out.println("Enter 7 - Exit");
    }

    public static void openGUI(){
        try
        {
            Runtime.getRuntime().exec("cmd /c start cmd.exe /K \" sbt run\"");
        }
        catch (Exception e)
        {
            System.out.println(ConsoleLog.error("An error occurred while opening GUI.",
                    "Console.openGUI()", e.getMessage()));

        }
    }
}
