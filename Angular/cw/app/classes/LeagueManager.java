package classes;


public interface LeagueManager {

	void addFootballClub(FootballClub club);
	void deleteFootballClub(int clubId);
	void displayClubStat(int clubId);	
	void displayLeagueTable();
	void addPlayedMatch(Match match);
	void saveInFile();
	void readFromFile();

}