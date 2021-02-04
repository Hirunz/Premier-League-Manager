import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Random;

public class Date implements Comparable<Date>,Serializable{
	public static final long  SerialVersionUID= 50L;
	private String day;
	private String month;
	private String year;

	private boolean isLeapYear=false;

//regex to check year. Year range is from 2020-2099.
// First two digits must be 20 and 3rd is from 2-9 and last can be 0-9.

	private static final String regexYear="20[2-9][0-9]";
	private static final String invalidYearMessage ="Invalid Year.\n"
		+"    Year format: YYYY\n "
		+"   Year range: 2020-2099";

	// two regex to check month.
	private static final String regexMonth1="0[0-9]";
	private static final String regexMonth2="1[0-2]";
	private static final String invalidMonthMessage ="Invalid Month.\n"
		+"    Month format: MM\n "
		+"   Month range: 1-12";

	// two regular expressions to check the day.
	private static final String regexDay1="[0-2][0-9]";
	private static final String regexDay2="3[0-1]";
	private static final String invalidDayMessage ="Invalid Day.\n"
		+"    Day format: DD\n "
		+"   Day range:\n"
		+"          for January, March, May, July, August, October, December: 31 days.\n"
		+"          for April, June, September, November: 30 days.\n"
		+"          for February 28 days in common year and 29 days in leap year.";

	// constructor
	public Date(String day, String month, String year){
		setYear(year);
		setMonth(month);
		setDay(day);
	}

	public String getDay() {
		return day;
	}

	// setter to set a day
	public void setDay(String day) {
		boolean isValid;
		// checking for date pattern using two regex.
		if (day.matches(regexDay1) || day.matches(regexDay2)){
			int intDay= Integer.parseInt(day);
			// if the day is 31st, then checks whether it belongs to a respective onth.
			if (intDay==31 && (month.equals("04") || month.equals("06")
				|| month.equals("09") || month.equals("11")) ){
					isValid=false;
			}
			// if month is february, the day must be less than 28 unless it's a leap yer which has 29.
			else if (intDay>28 && month.equals("02")){
				isValid = (intDay==29  && isLeapYear) ? true: false;
			}
			else {
				isValid=true;
			}
			if (isValid){
				this.day=day;
			}
			else {
				throw new InputMismatchException(invalidDayMessage);
			}

		}
	}

	public String getMonth() {
		return month;
	}

	// setter to check the month
	public void setMonth(String month) {
		// simply validated using two regex
		if (month.matches(regexMonth1)|| month.matches(regexMonth2)){
			this.month=month;
		}
		else {
			throw new InputMismatchException(invalidMonthMessage);
		}
	}

	public String getYear() {
		return year;
	}

	// setter to set the year.
	public void setYear(String year) {
		// check for year pattern using a regex.
		if (year.matches(regexYear)){
			//convert the year to integer
			int intYear =Integer.parseInt(year);
			// check whether it's a leap year.
			if (intYear % 4 ==0 && (intYear %100 != 0 | intYear %400==0)){
				isLeapYear=true;
			}
			this.year=year;
		}
		else{
			// invalid input error for wrong inputs.
			throw new InputMismatchException(invalidYearMessage);
		}
	}

	@Override
	public String toString() {
		return this.day+"/"+this.month+"/"+this.year;

 	}

 	// a method to check whether a given date is valid.
	// logic in above 3 setters are used with addition flags.
 	public static Boolean isAValidDate(String day, String month, String year){

		// flags to identify whether all three attributes are correct.
		boolean isLeapYear = false;
		boolean dateValid = false;
		boolean monthValid= false;
		boolean yearValid= false;

		// year check as previous setter.
		if (year.matches(regexYear)){
			int intYear =Integer.parseInt(year);
			if (intYear % 4 ==0 && (intYear %100 != 0 | intYear %400==0)){
				isLeapYear=true;
			}
			yearValid=true;
		}
		// month check
		if (month.matches(regexMonth1)|| month.matches(regexMonth2)){
			monthValid=true;
		}
		// day check
		if (day.matches(regexDay1) || day.matches(regexDay2)){
			int intDay= Integer.parseInt(day);
			if (intDay==31 && (month.equals("04") || month.equals("06")
					|| month.equals("09") || month.equals("11")) ){
				dateValid=false;
			}
			else if (intDay>28 && month.equals("02")){
				dateValid = (intDay==29  && isLeapYear) ? true: false;
			}
			else {
				dateValid=true;
			}
		}

		// if the date is valid, all 3 attributes must be valid.
		if (dateValid && monthValid && yearValid){
			return true;
		}
		else {
			return false;
		}

	}

	// a method to generate a random date which is to be used in the GUI menu.


 	public static Date randomDate(){
		Date date;
		Random random=new Random();

		// generating 3 integers for day, month year.
		int day=random.nextInt(31) +1;
		int month= random.nextInt(12)+1;
		int year=random.nextInt(80)+2020;


		// convert the integers to string
		String d=String.valueOf(day);
		String m=String.valueOf(month);
		String y=String.valueOf(year);

		// checks whether the generated date is a valid date.
		if (isAValidDate(d,m,y)){
			// if the 0  is missing in day and month it wil be added to the begining.
			if (d.length()==1){
				d=0+d;
			}
			if (m.length()==1){
				m=0+m;
			}
			return new Date(d,m,y);
		}
		else {
			// if the date is not valid, recurse untill it gets a valid date
			date=randomDate();
		}

		return date;

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Date date = (Date) o;
		return isLeapYear == date.isLeapYear &&
				Objects.equals(day, date.day) &&
				Objects.equals(month, date.month) &&
				Objects.equals(year, date.year);
	}

	@Override
	public int hashCode() {
		return Objects.hash(day, month, year, isLeapYear);
	}

	// overrided comparable interface to implement the default comparing mehtod for a date
	// For two dates to be equal in compharison,
	// 1- year
	// 2- month
	// 3- day
	// must be equal. if it's equal, compareTo will return 0 and else it will return the difference.
	@Override
	public int compareTo(Date d) {
		if (this.year.equals(d.year)){
			if (this.getMonth().equals(d.getMonth())){
				if (this.day.equals(d.day)){
					return 0;
				}
				else {
					return Integer.parseInt(this.getDay())-Integer.parseInt(d.getDay());
				}
			}
			else {
				return Integer.parseInt(this.getMonth())-Integer.parseInt(d.getMonth());
			}
		}
		else {
			return Integer.parseInt(this.getYear())-Integer.parseInt(d.getYear());
		}
	}
}
