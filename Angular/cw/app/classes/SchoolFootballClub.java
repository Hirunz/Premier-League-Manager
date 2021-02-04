package classes;

import java.util.Objects;

public class SchoolFootballClub extends FootballClub {

	private String schoolName;

	public SchoolFootballClub(int clubId, String clubName, String clubLocation, String schoolName){
		super(clubId,clubName,clubLocation);
		this.schoolName=schoolName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@Override
	public String toString() {
		return super.toString()	
		+"\nSchool Name = '" + schoolName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		SchoolFootballClub that = (SchoolFootballClub) o;
		return Objects.equals(schoolName, that.schoolName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), schoolName);
	}
}