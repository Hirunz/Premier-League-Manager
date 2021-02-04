package classes;

import java.io.Serializable;
import java.util.Objects;

public class FootballClub extends SportsClub implements Comparable<FootballClub>, Serializable{
	public static final long serialVersionUID=50L; 

	private int numOfWins;	
	private int numOfDraws;
	private int numOfDefeats;
	private int numOfGoalsScored;
	private int numOfGoalsReceived;
	private int numOfPoints;
	private int numOfMatches;


// when a club is added to the system, all the variables here are 0
// because a club must exist before playing a match.
// therefore, only club id, location and name are the things
// that must be there at the creation of the object

	public FootballClub(int clubId, String clubName, String clubLocation) {
		super(clubId, clubName, clubLocation);
	}

// constructor for test purposes
	public FootballClub(int clubId, String clubName, String clubLocation,
			int numOfWins, int numOfDraws, int numOfDefeats, int numOfGoalsScored,
			int numOfGoalsReceived, int numOfPoints, int numOfMatches) {
	
		super(clubId, clubName, clubLocation);
		this.numOfWins = numOfWins;
		this.numOfDraws = numOfDraws;
		this.numOfDefeats = numOfDefeats;
		this.numOfGoalsScored = numOfGoalsScored;
		this.numOfGoalsReceived = numOfGoalsReceived;
		this.numOfPoints = numOfPoints;
		this.numOfMatches = numOfMatches;
	}

// getters and setters for each instance variable
	public int getNumOfWins() {
		return numOfWins;
	}

	public void setNumOfWins(int numOfWins) {
		this.numOfWins = numOfWins;
	}

	public int getNumOfDraws() {
		return numOfDraws;
	}

	public void setNumOfDraws(int numOfDraws) {
		this.numOfDraws = numOfDraws;
	}

	public int getNumOfDefeats() {
		return numOfDefeats;
	}

	public void setNumOfDefeats(int numOfDefeats) {
		this.numOfDefeats = numOfDefeats;
	}

	public int getNumOfGoalsReceived() {
		return numOfGoalsReceived;
	}

	public void setNumOfGoalsReceived(int numOfGoalsReceived) {
		this.numOfGoalsReceived = numOfGoalsReceived;
	}

	public int getNumOfPoints() {
		return numOfPoints;
	}

	public void setNumOfPoints(int numOfPoints) {
		this.numOfPoints = numOfPoints;
	}

	public int getNumOfMatches() {
		return numOfMatches;
	}

	public void setNumOfMatches(int numOfMatches) {
		this.numOfMatches = numOfMatches;
	}

	public int getNumOfGoalsScored() {
		return numOfGoalsScored;
	}

	public void setNumOfGoalsScored(int numOfGoalsScored) {
		this.numOfGoalsScored = numOfGoalsScored;
	}

// toString override to print all the details of the instance in specific format.
	@Override
	public String toString() {
		return super.toString()
			+"\n\nWins = " + numOfWins
			+"\nDraws = " + numOfDraws
			+"\nDefeats = " + numOfDefeats
			+"\nGoals Scored = " + numOfGoalsScored
			+"\nGoals Received = " + numOfGoalsReceived
			+"\nPoints = " + numOfPoints
			+"\nMatches = " + numOfMatches;
	}

// overriding the compareTo method in the comparable interface.
// clubs are compared by the number of points they have.
// if there are two clubs with same points, they are compared with goal difference.
// goal difference= num of goals scored - num of goals received.
// if that is also the same, then clubs are compared using the num of goals scored.
	@Override
	public int compareTo(FootballClub o) {
		if ((this.numOfPoints-o.numOfPoints)!=0){
			return this.numOfPoints-o.numOfPoints;
		}
		else {
			if ((this.numOfGoalsScored-this.numOfGoalsReceived) -
				(o.numOfGoalsScored-o.numOfGoalsReceived) != 0){

				return (this.numOfGoalsScored-this.numOfGoalsReceived) -
					(o.numOfGoalsScored - o.numOfGoalsReceived);
			}
			else {
				if (this.numOfGoalsScored-o.numOfGoalsReceived !=0){
					return this.numOfGoalsScored-o.numOfGoalsReceived;
				}
				else{
					return 0;
				}
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		FootballClub club = (FootballClub) o;
		return numOfWins == club.numOfWins &&
				numOfDraws == club.numOfDraws &&
				numOfDefeats == club.numOfDefeats &&
				numOfGoalsScored == club.numOfGoalsScored &&
				numOfGoalsReceived == club.numOfGoalsReceived &&
				numOfPoints == club.numOfPoints &&
				numOfMatches == club.numOfMatches;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), numOfWins, numOfDraws, numOfDefeats, numOfGoalsScored, numOfGoalsReceived, numOfPoints, numOfMatches);
	}
}
