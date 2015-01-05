package finalProject;

import java.io.Serializable;
import java.text.DecimalFormat;



public class Student implements Serializable, Comparable<Student>{

	private static final long serialVersionUID = 1;
	private final Integer STUDENTID;
	private String FirstName;
	private String LastName;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private Character gender;
	private String major;
	private Integer numberOfCredits;
	private double gpa;
	private PriorityQueue<CompletedCourse> completedCourses;
	public Student(Integer StudentID, String FirstName, String LastName,
			String address, String city, String state, String zipcode,
			Character gender, String major)
			throws MissingDataException, InvalidDataException {
		if (StudentID == null) {
			throw new MissingDataException();
		}
		this.STUDENTID = StudentID;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.gender = gender;
		this.major = major;
		this.completedCourses=new PriorityQueue<CompletedCourse>(new YearComparator());
		this.numberOfCredits = 0;
		this.gpa = 0;

	}

	public Student(Integer StudentID, String FirstName, String LastName,
			Character gender, String major) throws MissingDataException, InvalidDataException {
		this(StudentID, FirstName, LastName, null, null, null, null, gender,
				major);
	}

	public String getFirstName() {
		return FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public String getAddress() {
		return address;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public char getGender() {
		return gender;
	}

	public String getMajor() {
		return major;
	}

	public Integer getNumberOfCredits() {
		LinkedListIterator<CompletedCourse> iter=completedCourses.iterator();
		int credits=0;
		while(iter.hasNext()){
			credits+=iter.next().getnumCredits();
		}
		this.numberOfCredits=credits;
		return numberOfCredits;
	}

	public double getGpa() {
		LinkedListIterator<CompletedCourse> iter=completedCourses.iterator();
		int grades=0;
		int credits=0;
		while(iter.hasNext()){
			CompletedCourse course=iter.next();
			grades+=course.getGrade().getValue();
			credits+=course.getnumCredits();
					}
		this.gpa=(grades/Double.valueOf(credits))*4;
		DecimalFormat formatter=new DecimalFormat("#.0");
		return Double.valueOf(formatter.format(gpa));
	}

	public Integer getStudentID() {
		return STUDENTID;
	}

	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String State) {
		this.state = State;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setNumberOfCredits(Integer credits) throws InvalidDataException {
		if (credits < 0) {
			throw new InvalidDataException();
		}
		this.numberOfCredits = credits;
	}

	public void setGpa(double gpa) throws InvalidDataException{
		if (gpa < 0.0 || gpa > 4.0) {
			throw new InvalidDataException();
		}
		this.gpa = gpa;
	}

	public int compareTo(Student student) {
		return this.STUDENTID.compareTo(student.STUDENTID);
	}

	public boolean equals(Object obj) {
		if(obj instanceof Student){
		return STUDENTID.equals(((Student) obj).getStudentID());
		}
		return false;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student ID: ");
		builder.append(this.STUDENTID);
		builder.append(" ");
		builder.append("First Name: ");
		builder.append(this.FirstName);
		builder.append(" ");
		builder.append("Last Name is: ");
		builder.append(this.LastName);
		builder.append(" ");
		if (address != null) {
			builder.append("Address is : ");
			builder.append(this.address);
			builder.append(" ");
		}
		if (city != null) {
			builder.append("City: ");
			builder.append(this.city);
			builder.append(" ");
		}
		if (state != null) {
			builder.append("State: ");
			builder.append(this.state);
			builder.append(" ");
		}
		if (zipcode != null) {
			builder.append("Zipcode is: ");
			builder.append(this.zipcode);
			builder.append(" ");
		}
		builder.append("Gender is: ");
		builder.append(this.gender);
		builder.append(" ");
		builder.append("Major is: ");
		builder.append(this.major);
		builder.append(" ");
		if (numberOfCredits != null) {
			builder.append("Number of credits is: ");
			builder.append(this.numberOfCredits);
			builder.append(" ");
		}
		if (gpa != 0.0) {
			builder.append("GPA is: ");
			DecimalFormat formatter=new DecimalFormat("#.0");
			builder.append(formatter.format(gpa));
		}
		return builder.toString();
	}
	public String getCompletedCourses(){
		if(completedCourses.isEmpty()){
			return "No completed courses recorded";
		}
		StringBuilder builder=new StringBuilder();
		LinkedListIterator<CompletedCourse> iter=completedCourses.iterator();
		while(iter.hasNext()){
			builder.append(iter.next().toString());
			builder.append("\n");
		}
		return builder.toString();
	}
	public void addCompletedCourse(CompletedCourse course) throws DuplicateDataException {
		if(completedCourses.contains(course)){
			throw new DuplicateDataException("Course can't be taken again");
		}
		completedCourses.insert(course);
		getGpa();
		getNumberOfCredits();
	}

}
