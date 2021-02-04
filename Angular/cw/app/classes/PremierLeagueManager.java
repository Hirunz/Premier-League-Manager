package classes;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PremierLeagueManager implements LeagueManager  {
	public static final String fileName = "app/classes/log.txt";

// array list to hold all the clubs in the league.
	public static final List<FootballClub> leagueClubs= new ArrayList<>();

// array list to store all the played matches.
	public static final List<Match> matchesPlayed=new ArrayList<>();


	public  List<FootballClub> getLeagueClubs() {
		return leagueClubs;
	}

	public  List<Match> getMatchesPlayed() {
		return matchesPlayed;
	}

	@Override
	public void addFootballClub(FootballClub club) {

// The club ID is unique to each club.
// Therefore, checking whether the club with same Club ID is in the system.
// If exists, the system will show an error message.

		for (FootballClub c : leagueClubs){
			if (c.getClubId()==club.getClubId()){
				System.out.println(ConsoleLog.error("A club with same ID exists in the system.",
						"PremierLeagueManager.addFootballClub()\n"));
				return;
			}
		}
		leagueClubs.add(club);
		saveInFile();
		System.out.println("\nClub added successfully.\n");

	}

	@Override
	public void deleteFootballClub(int clubId) {
		if (leagueClubs.isEmpty()){
			System.out.println(ConsoleLog.error("Clubs are empty",
					"PremierLeagueManager.deleteFootballClub()\n"));
		}

// if the club with the given club id exists, it will be removed from the club list.
		for (FootballClub c : leagueClubs){
			if (c.getClubId()==clubId){
				leagueClubs.remove(c);
				System.out.println("\nClub deleted successfully.\n");
				saveInFile();
				return;
			}
		}
		System.out.println( ConsoleLog.error("The club does not exist in the league.",
				"PremierLeagueManager.deleteFootballClub()\n"));
	}

	@Override
	public void displayClubStat(int clubId) {

// displaying statistics of the club for given club id.
// Overrided toString method of the object will be used here.

		for (FootballClub c : leagueClubs){
			if (c.getClubId()==clubId){
				System.out.println(c);
				return;
			}
		}
		System.out.println(ConsoleLog.error("Club not found. Please check the Club ID and try again.",
				"PremierLeagueManager.displayClubStat()\n"));
	}

	@Override
	public void displayLeagueTable() {

// printing the league table
//first, the club list will be sorted to get the elements in the ascending order.
// here, the overrided compareTo method will be used when sorting.

		sortByPoints();

// A football club to hold each club in the league in the loop.

		FootballClub club;

		System.out.println("\n---------------------------------------------------------------------------" +
			"---------------------------------------------------------------------------------------");
		System.out.println("                                                                         " +
			"LEAGUE TABLE" +
			"                                                                             ");
		System.out.println("---------------------------------------------------------------------------" +
			"---------------------------------------------------------------------------------------\n");
		System.out.println(" Rank     " +
			"Club ID                "+
			"Club Name              " +
			"Club Location        " +
			"Wins      " +
			"Draws      " +
			"Defeats      " +
			"Goals Scored      " +
			"Goals Received      " +
			"Points      " +
			"Matches ");

		for (int i =0; i<leagueClubs.size();i++) {

// assigning the current club to the football club declared above

			club=leagueClubs.get(i);

// string formatting to put the club statistics in the correct order.

			String tableFormat=" %3d      %5d        %-30s     %-15s     " +
				"%3d       %3d         %3d             %3d               " +
				"%3d              %3d         %3d\n";

			System.out.printf(tableFormat,
				leagueClubs.size()-i,
				club.getClubId(),
				club.getClubName(),
				club.getClubLocation(),
				club.getNumOfWins(),
				club.getNumOfDraws(),
				club.getNumOfDefeats(),
				club.getNumOfGoalsScored(),
				club.getNumOfGoalsReceived(),
				club.getNumOfPoints(),
				club.getNumOfMatches()
			);
		}
	}

	@Override
	public void addPlayedMatch(Match match) {
		

		// flags to check whether the both clubs entered are in the system.
		boolean clubInTheSystem1= false;
		boolean clubInTheSystem2= false;

		for (FootballClub c:leagueClubs){

			if (clubInTheSystem1 && clubInTheSystem2){
			// to reduce compiling time by exiting the loop if both clubs are found
				break;
			}
			if (c.getClubId()== match.getClub1()){
				clubInTheSystem1=true;
			}
			else if (c.getClubId()==match.getClub2()){
				clubInTheSystem2=true;
			}

		}

		if (clubInTheSystem1 && clubInTheSystem2
				&& (!bothMatchesPlayed(match.getClub1(), match.getClub2()))) {

			if (!match.isDraw()) {
				matchesPlayed.add(match);
				boolean updatedWinner = false;
				boolean updatedDefeated = false;

				for (FootballClub c : leagueClubs) {

					// updating the club statistics for the winner club
					if (c.getClubId() == match.getWinnerClub()) {
						c.setNumOfWins(c.getNumOfWins() + 1);
						c.setNumOfGoalsScored(c.getNumOfGoalsScored() + match.getWinnerGoalsScored());
						c.setNumOfGoalsReceived(c.getNumOfGoalsReceived() + match.getDefeatedScoredGoals());
						c.setNumOfMatches(c.getNumOfMatches() + 1);
						c.setNumOfPoints(c.getNumOfPoints() + 3);
						updatedWinner = true;
						continue;
					}
					// updating the club statistics for the defeated club.
					if (c.getClubId() == match.getDefeatedClub()) {
						c.setNumOfDefeats(c.getNumOfDefeats() + 1);
						c.setNumOfGoalsScored(c.getNumOfGoalsScored() + match.getDefeatedScoredGoals());
						c.setNumOfGoalsReceived(c.getNumOfGoalsReceived() + match.getWinnerGoalsScored());
						c.setNumOfMatches(c.getNumOfMatches() + 1);
						updatedDefeated = true;
						continue;
					}

					if (updatedWinner && updatedDefeated) {
						saveInFile();
						return;
					}

				}
			} else {
				// if the match is draw
				matchesPlayed.add(match);
				int count = 0;
				for (FootballClub c : leagueClubs) {

					// updating the club statistics for the winner club
					if (c.getClubId() == match.getClub1() || c.getClubId() == match.getClub2()) {
						c.setNumOfDraws(c.getNumOfDraws() + 1);
						c.setNumOfGoalsScored(c.getNumOfGoalsScored() + match.getClub1GoalsScored());
						c.setNumOfGoalsReceived(c.getNumOfGoalsReceived() + match.getClub2GoalsScored());
						c.setNumOfMatches(c.getNumOfMatches() + 1);
						c.setNumOfPoints(c.getNumOfPoints() + 1);
						count++;
					}
					if (count >= 2) {
						saveInFile();
						return;
					}
				}
			}
		}
		else if(clubInTheSystem1 && clubInTheSystem2 && bothMatchesPlayed(match.getClub1(),match.getClub2())){
			System.out.println(ConsoleLog.warn("Both matches have been played. Check club ID and try again.\n"));
		}
		else {
			System.out.println(ConsoleLog.warn("The clubs entered are not in the system."));
			System.out.println(ConsoleLog.warn("Please check club ID's and try again.\n"));
		}
	}

	@Override
	public void saveInFile()  {
		try {
			try (// a try with resource type to avoid duplicate try catch blocks.
				 // this can be done since both the resources are autoclosable.
					FileOutputStream fos = new FileOutputStream(new File(fileName));
					ObjectOutputStream oos = new ObjectOutputStream(fos);
			) {
				for (FootballClub c : leagueClubs) {
					oos.writeObject(c);
				}
				for (Match m : matchesPlayed) {
					oos.writeObject(m);
				}
				// flushing the output stream to avoid duplication.
				oos.flush();


			}
		}catch (IOException e){
			System.out.println(ConsoleLog.error("An error occured while saving the file.",
					"PremierLeagueManager.saveInFile()\n"));
		}
	}

	@Override
	public void readFromFile() {
		try {

			try (// a try with resource type to avoid duplicate try catch blocks.
				 // this can be done since both the resources are autoclosable.
				 FileInputStream fis = new FileInputStream(new File(fileName));
				 ObjectInputStream ois = new ObjectInputStream(fis);
			) {
				while (true) {
					try {
						Object obj = ois.readObject();
						// first the input is read as Objects then carefully type casting to relevant types.
						// Checking whether the clubs already in the system are being created again
						// and exclude them while reading.
						if (obj instanceof FootballClub) {
							FootballClub c = (FootballClub) obj;
							if (!leagueClubs.contains(c)){
								leagueClubs.add(c);
							}


						}
						if (obj instanceof Match) {
							Match m = (Match) obj;
							if (!matchesPlayed.contains(m)){
								matchesPlayed.add(m);
							}

						}

					} catch (EOFException eof) {
						// End of File exception marks the exit of the while loop.
						break;
					}
				}

			}
		}catch (FileNotFoundException e){
			System.out.println(ConsoleLog.error("File not found.","PremierLeagueManager.readFromFile()"));
			File file=new File(fileName);
			try {
				file.createNewFile();
				System.out.println("A new file created.\n\n");

			} catch (IOException ioException) {
				System.out.println(ConsoleLog.error("File already exists or file path wrong. Please delete and restart the app."));
			}

		}catch (IOException | ClassNotFoundException e){
			System.out.println(ConsoleLog.error(" An error has occurred while reading from the file.\n",
					"PremierLeagueManager.readFromFile()",e.getMessage()));
			e.printStackTrace();
		}
	}



	// according to google search, premier league held only 2 matches for same 2 teams.
	// one match in one team's locations and the other one is held at their location.
	// this method is to check whether both the matches are played.
	public boolean bothMatchesPlayed(int clubId1, int clubId2){
		int count=0;
		for(Match m: matchesPlayed){
			if (m.getClub1()== clubId1 | m.getClub1()==clubId2){
				// if a match has one of the given ids, then check for the second id.
				// if the match count is 2, then both matches have been played.
				if (m.getClub2()== clubId1 | m.getClub2()==clubId2){
					count++;
					if (count>=2){
						return true;
					}
				}
			}
		}
		return false;
	}
	// a method to display all mathces in console
	public void displayPlayedMatches(){
		for (Match m: matchesPlayed){
			System.out.println(m);
		}
	}
	// using sort method in the Collections framework
	// since the default comparing method is defined in the relavent class
	public void sortByPoints(){
		Collections.sort(leagueClubs,Collections.reverseOrder());
		saveInFile();
	}

	// using bubble sort to sort league table by number of goals sccored.
	public void sortByGoalsScored(){
		FootballClub temp;
		for(int i=0; i<leagueClubs.size()-1;i++){
			for(int j=0; j<leagueClubs.size()-(i+1);j++){
				if (leagueClubs.get(j).getNumOfGoalsScored()<leagueClubs.get(j+1).getNumOfGoalsScored()){
					temp= leagueClubs.get(j);
					leagueClubs.set(j,leagueClubs.get(j+1));
					leagueClubs.set(j+1,temp);
				}
			}
		}
		saveInFile();
	}


	// using bubble sort to sort league table by number of wins.
	public void sortByWins(){
		FootballClub temp;
		for(int i=0; i<leagueClubs.size()-1;i++){
			for(int j=0; j<leagueClubs.size()-(i+1);j++){
				if (leagueClubs.get(j).getNumOfWins()<leagueClubs.get(j+1).getNumOfWins()){
					temp= leagueClubs.get(j);
					leagueClubs.set(j,leagueClubs.get(j+1));
					leagueClubs.set(j+1,temp);
				}
			}
		}
		saveInFile();
	}

	// sort matches by date using the compareTo method in date class and bubble sort.
	public void sortMatchesByDate(){
		Match temp;
		for (int i=0; i< matchesPlayed.size()-1;i++){
			for (int j=0;j<matchesPlayed.size()-(i+1);j++){
				if (matchesPlayed.get(j).getDate().compareTo(getMatchesPlayed().get(j+1).getDate())>0){
					temp=matchesPlayed.get(j);
					matchesPlayed.set(j,matchesPlayed.get(j+1));
					matchesPlayed.set(j+1,temp);
				}
			}
		}
		saveInFile();
	}

	// to search matches by date.
	// returns a list of mathces with the same date.
	public List<Match> matchesInADate(Date date){
		List<Match> list = new ArrayList<>();
		for (Match m: matchesPlayed) {
			if (date.compareTo(m.getDate()) == 0) {
				list.add(m);
			}
		}
		return list;
	}

}
