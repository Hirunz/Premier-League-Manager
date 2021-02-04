import java.io.Serializable;
import java.util.Objects;

public class Match implements Serializable {
	public static final long  SerialVersionUID= 50L;
	// necessary variables.
	private int club1;
	private int club2;
	private int club1GoalsScored;
	private int club2GoalsScored;
	private int winnerClub;
	private int defeatedClub;
	private boolean draw;
	private int winnerGoalsScored;
	private int defeatedScoredGoals;
	private Date date;


	// constructor
	public Match(int club1, int club2, int club1GoalsScored, int club2GoalsScored, Date date) {
		this.club1 = club1;
		this.club2 = club2;
		this.date = date;
		this.club1GoalsScored=club1GoalsScored;
		this.club2GoalsScored=club2GoalsScored;

		// if club 1 has higher goals it wins.
		if (club1GoalsScored>club2GoalsScored){
			winnerClub=club1;
			defeatedClub=club2;
			winnerGoalsScored=club1GoalsScored;
			defeatedScoredGoals=club2GoalsScored;
			
		}// else if club 2 has higher goals, club2 wins.
		else if (club2GoalsScored>club1GoalsScored){
			winnerClub=club2;
			defeatedClub=club1;
			winnerGoalsScored=club2GoalsScored;
			defeatedScoredGoals=club1GoalsScored;
		}// the other options must be where both are equal. then it's a draw.
		else{
			draw=true;
		}
	}


	// relevant getters and setters for each variable.

	public int getClub1() {
		return club1;
	}

	public void setClub1(int club1) {
		this.club1 = club1;
	}

	public int getClub2() {
		return club2;
	}

	public void setClub2(int club2) {
		this.club2 = club2;
	}

	public int getClub1GoalsScored() {
		return club1GoalsScored;
	}

	public void setClub1GoalsScored(int club1GoalsScored) {
		this.club1GoalsScored = club1GoalsScored;
	}

	public int getClub2GoalsScored() {
		return club2GoalsScored;
	}

	public void setClub2GoalsScored(int club2GoalsScored) {
		this.club2GoalsScored = club2GoalsScored;
	}

	public int getWinnerClub() {
		return winnerClub;
	}

	public void setWinnerClub(int winnerClub){
		this.winnerClub=winnerClub;
	}


	public boolean isDraw() {
		return draw;
	}

	public void setDraw(boolean draw) {
		this.draw = draw;
	}


	public int getDefeatedClub() {
		return defeatedClub;
	}


	public int getWinnerGoalsScored() {
		return winnerGoalsScored;
	}

	public void setWinnerGoalsScored(int winnerGoalsScored) {
		this.winnerGoalsScored = winnerGoalsScored;
	}

	public int getDefeatedScoredGoals() {
		return defeatedScoredGoals;
	}

	public void setDefeatedScoredGoals(int defeatedScoredGoals) {
		this.defeatedScoredGoals = defeatedScoredGoals;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	// toString implementation.
	@Override
	public String toString() {
		if (draw==true){
			// for draw match
			return "Match{ " +
				"club1=" + club1 +
				", club2=" + club2 +
				", draw=" + draw +
				", Goals Scored=" + club1GoalsScored +
				", Date = "+ date+
				"}";
		}
		else {
			// for match with winner and loser
			return "Match{ " +
				"club1=" + club1 +
				", club2=" + club2 +
				", winnerClub=" + winnerClub +
				", defeatedClub=" + defeatedClub +
				", winnerGoalsScored=" + winnerGoalsScored +
				", defeatedScoredGoals=" + defeatedScoredGoals +
				", date=" + date +
				"}";
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Match match = (Match) o;
		return club1 == match.club1 &&
				club2 == match.club2 &&
				club1GoalsScored == match.club1GoalsScored &&
				club2GoalsScored == match.club2GoalsScored &&
				winnerClub == match.winnerClub &&
				defeatedClub == match.defeatedClub &&
				draw == match.draw &&
				winnerGoalsScored == match.winnerGoalsScored &&
				defeatedScoredGoals == match.defeatedScoredGoals &&
				Objects.equals(date, match.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(club1, club2, club1GoalsScored, club2GoalsScored, winnerClub, defeatedClub, draw, winnerGoalsScored, defeatedScoredGoals, date);
	}
}
