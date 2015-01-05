package finalProject;

import java.io.Serializable;

public class CompletedCourse extends Course implements Serializable,Comparable<CompletedCourse> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Semester semesterCompleted;
	private int yearCompleted;
	private Grade grade;
	private int studentID;
	public CompletedCourse(String courseID, String courseName,
			Integer numCredits, String deptID, Semester semesterCompleted,
			int yearCompleted, Grade grade, int studentID)
			throws InvalidDataException {
		super(courseID, courseName, numCredits, deptID);
		this.semesterCompleted = semesterCompleted;
		this.yearCompleted = yearCompleted;
		this.grade = grade;
		this.studentID = studentID;
	}
	public Semester getSemesterCompleted() {
		return semesterCompleted;
	}
	public int getYearCompleted() {
		return yearCompleted;
	}
	public Grade getGrade() {
		return grade;
	}
	public int getStudentID() {
		return studentID;
	}
	public int compareTo(CompletedCourse course){
		return Integer.valueOf(yearCompleted).compareTo(Integer.valueOf(course.yearCompleted));
	}
	public String toString(){
		String string="StudentID: "+studentID+" "+super.toString()+ " Semester: "+ semesterCompleted+" Year: "+ yearCompleted+ " Grade: "+ grade;
				return string;
	}

}
