package finalProject;

public class StudentRecord {
	StudentList students;
	CourseList courses;
	
	public StudentRecord(){
		students=new StudentList();
		courses=new CourseList();
	}
	
	public void addStudent(Student s) throws DuplicateDataException{
		students.addStudent(s);
	}
	public void removeStudent(Integer studentID) throws NotFoundException{
		students.remove(studentID);
	}
	public void modifyStudentLastName(Integer StudentID,String name) throws NotFoundException{
		students.modifyStudentLastName(StudentID, name);
	}
	public void modifyStudentMajor(Integer StudentID,String major) throws NotFoundException{
		students.modifyStudentMajor(StudentID, major);
	}
	public double getStudentGPA(Integer studentID) throws NotFoundException{
		return students.getGPA(studentID);
	}
	public int getStudentNumberOfCredits(Integer studentID) throws NotFoundException{
		return students.getTotalCredits(studentID);
	}
	public void addCourse(Course c) throws DuplicateDataException{
		courses.addCourse(c);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
