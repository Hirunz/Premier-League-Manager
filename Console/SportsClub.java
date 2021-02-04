import java.io.Serializable;
import java.util.Objects;

abstract public  class SportsClub implements Serializable {
	public static final long serialVersionUID= 50L;

	private int clubId;
	private String clubName;
	private String clubLocation;

	public SportsClub(int clubId, String clubName, String clubLocation){
		this.clubId = clubId;
		this.clubName=clubName;
		this.clubLocation=clubLocation;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getClubLocation() {
		return clubLocation;
	}

	public void setClubLocation(String clubLocation) {
		this.clubLocation = clubLocation;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	@Override
	public String toString() {
		return "Club Id = "+this.clubId
			+"\nClub Name = "+this.clubName
			+"\nClub Location = "+this.clubLocation;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SportsClub that = (SportsClub) o;
		return clubId == that.clubId &&
				Objects.equals(clubName, that.clubName) &&
				Objects.equals(clubLocation, that.clubLocation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(clubId, clubName, clubLocation);
	}
}
