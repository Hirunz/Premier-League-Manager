import java.io.IOException;


public class Test {

	public static void main(String[] args) throws IOException, ClassNotFoundException{

// 	Date d1=new Date("10","19","2020");
// 	System.out.println(d1);

	final PremierLeagueManager manager = new PremierLeagueManager();

	//List<FootballClub> clubs=new ArrayList<>();


	FootballClub c1=new FootballClub(1000,"Leicester City","Leicester",
		5,2,3,8,9,9,0);


	FootballClub c2=new FootballClub(1001,"Liverpool","Liverpool",
		5,2,3,8,9,10,0);


// two clubs with same points- goals scored is different

	FootballClub c3=new FootballClub(1002,"Manchester City","Manchester",
		5,2,3,10,9,5,0);

	FootballClub c4=new FootballClub(1003,"Manchester United","Old Trafford",
		5,2,3,10,8,5,0);


// two clubs with same points- goals scored and received are different,
// goals difference is same

	FootballClub c5=new FootballClub(1004,"Blackpool","Blackpool",
		5,2,3,10,9,7,0);

	FootballClub c6=new FootballClub(1005,"Charlton Athletic","London",
		5,2,3,8,7,7,0);

// //Test without manager

// 	for (FootballClub c: clubs){
// 		System.out.println(c);
// 	}
// 	System.out.println("\n-----------------------------------------------------------------------------\n");
// 	Collections.sort(clubs);

// 	for (int i =(clubs.size()-1); i>0;i--){
// 		System.out.println(clubs.get(i));
// 	}

// Test with manager
	manager.addFootballClub(c1);
	manager.addFootballClub(c2);
	manager.addFootballClub(c3);
	manager.addFootballClub(c4);
	manager.addFootballClub(c5);
	manager.addFootballClub(c6);
	//manager.readFromFile("test");

	manager.deleteFootballClub(1005);
	manager.displayClubStat(1005);
	manager.displayLeagueTable();



Match match= new Match(1001,1002,10,5, new Date("08","05","2020"));
	//System.out.println(match);

	Match match1= new Match(1001,1002,10,10, new Date("08","05","2020"));
		//Match match2= new Match(1001,1002,10,10, new Date("08","05","2020"));
	// System.out.println(match1);


	manager.addPlayedMatch(match);

	manager.addPlayedMatch(match1);

	manager.displayPlayedMatches();
	manager.displayLeagueTable();
	

	 manager.saveInFile();

	}
}
