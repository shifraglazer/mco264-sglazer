package finalProject;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class StudentList implements Serializable {

	private LinkedList<Student> students;
	private static final long serialVersionUID=1;
	

	public StudentList() {
		
		students = new LinkedList<Student>();
	}

	public void addStudent(Student student) throws DuplicateDataException {
		
		if (hasStudent(student)) {
			throw new DuplicateDataException();
		}
	
			students.add(student); 
	}

	public void addStudent(Integer StudentID, String LastName,
			String FirstName, Character gender, String major)
			throws DuplicateDataException, MissingDataException,
			InvalidDataException{
		if (StudentID == null) {
			throw new MissingDataException();
		}
		Student newStudent = new Student(StudentID, LastName, FirstName,
				gender, major);
		if (!hasStudent(newStudent)) {
			students.add(newStudent);
		} else if (hasStudent(newStudent)) {
			throw new DuplicateDataException();
		} 
	}
	public void addCompletedCourse(Integer StudentID, CompletedCourse course) throws NotFoundException, DuplicateDataException{
		Student student=findStudent(StudentID);
		student.addCompletedCourse(course);
	}
	public Double getGPA(Integer studentID) throws NotFoundException{
		Student student=findStudent(studentID);
		return student.getGpa();
	}
	public int getTotalCredits(Integer studentID) throws NotFoundException{
		Student student=findStudent(studentID);
		return student.getNumberOfCredits();
	}
	public void remove(Integer StudentID) throws NotFoundException {

		Student student = findStudent(StudentID);
		students.remove(student);
	}

	public Student findStudent(Integer StudentID) throws NotFoundException {
		Iterator<Student> iter=students.iterator();
		Student next;
		while(iter.hasNext()){
			next=iter.next();
			if(next.getStudentID()==StudentID){
				return next;
			}
		}
		
		throw new NotFoundException();
	}

	public void modifyStudentLastName(Integer StudentID,
			String newName) throws NotFoundException {
		Student student = findStudent(StudentID);
		student.setLastName(newName);
	}

	public void modifyStudentAddress(Integer StudentID, String address,
			String city, String state, String zipcode) throws NotFoundException {
		Student student= findStudent(StudentID);
		student.setAddress(address);
		student.setCity(city);
		student.setState(state);
		student.setZipcode(zipcode);
	}

	public void modifyStudentGPA(Integer StudentID, Double gpa)
			throws NotFoundException, InvalidDataException {
		Student student = findStudent(StudentID);
		student.setGpa(gpa);
	}

	public void modifyStudentMajor(Integer StudentID, String major)
			throws NotFoundException {
		Student student = findStudent(StudentID);
		student.setMajor(major);
	}

	public void modifyStudentCredits(Integer StudentID, Integer credits)
			throws NotFoundException, InvalidDataException {
		Student student = findStudent(StudentID);
		student.setNumberOfCredits(credits);
	}

	public String findStudentsByMajor(String major) {
		StringBuilder builder = new StringBuilder();
		Iterator<Student> iter=students.iterator();
		while(iter.hasNext()){
			Student student=iter.next();
			if (student.getMajor().toUpperCase().equals(major.toUpperCase())) {
				builder.append("\n");
				builder.append(student.toString());
			}
		}
		return builder.toString();
	}

	public String findStudentsByCredit(int number) {
		StringBuilder builder = new StringBuilder();
		Iterator<Student> iter=students.iterator();
		while(iter.hasNext()){
			Student student=iter.next();
			if(student.getNumberOfCredits()==number){
				builder.append("\n");
				builder.append(student.toString());
			}
		}
		return builder.toString();
	}

	public boolean hasStudent(Student s) {
		Iterator<Student> iter=students.iterator();
		while(iter.hasNext()){
			Student student=iter.next();
			if (student.equals(s)) {
				return true;
			}
		}
		return false;
	}


	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < students.size(); i++) {
			builder.append("\n");
			builder.append(students.get(i).toString());
		}
		return builder.toString();
	}

}
