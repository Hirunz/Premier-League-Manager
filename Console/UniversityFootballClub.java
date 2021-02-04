import java.util.Objects;

public class UniversityFootballClub extends FootballClub {

	private String universityName;

	public UniversityFootballClub(int clubId, String clubName, String clubLocation, String universityName){
		super(clubId, clubName, clubLocation);
		this.universityName=universityName;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	@Override
	public String toString() {
		return super.toString()
			+"\nUniversity Name =" + universityName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		UniversityFootballClub that = (UniversityFootballClub) o;
		return Objects.equals(universityName, that.universityName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), universityName);
	}
}
