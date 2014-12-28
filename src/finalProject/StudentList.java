package finalProject;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentList implements Serializable {

	private ArrayList<Student> students;
	private static final long serialVersionUID=1;
	

	public StudentList() {
		
		students = new ArrayList<Student>();
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
		int index=findStudent(StudentID);
		students.get(index).addCompletedCourse(course);
	}
	public Double getGPA(Integer studentID) throws NotFoundException{
		int index=findStudent(studentID);
		return students.get(index).getGpa();
	}
	public int getTotalCredits(Integer studentID) throws NotFoundException{
		int index=findStudent(studentID);
		return students.get(index).getNumberOfCredits();
	}
	public void remove(Integer StudentID) throws NotFoundException {
		int index = findStudent(StudentID);
		students.remove(index);

	}

	public int findStudent(Integer StudentID) throws NotFoundException {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getStudentID().equals(StudentID)) {
				return i;
			}
		}
		throw new NotFoundException();
	}

	public void modifyStudentLastName(Integer StudentID,
			String newName) throws NotFoundException {
		int index = findStudent(StudentID);
		students.get(index).setLastName(newName);
	}

	public void modifyStudentAddress(Integer StudentID, String address,
			String city, String state, String zipcode) throws NotFoundException {
		int index = findStudent(StudentID);
		students.get(index).setAddress(address);
		students.get(index).setCity(city);
		students.get(index).setState(state);
		students.get(index).setZipcode(zipcode);
	}

	public void modifyStudentGPA(Integer StudentID, Double gpa)
			throws NotFoundException, InvalidDataException {
		int index = findStudent(StudentID);
		students.get(index).setGpa(gpa);
	}

	public void modifyStudentMajor(Integer StudentID, String major)
			throws NotFoundException {
		int index = findStudent(StudentID);
		students.get(index).setMajor(major);
	}

	public void modifyStudentCredits(Integer StudentID, Integer credits)
			throws NotFoundException, InvalidDataException {
		int index = findStudent(StudentID);
		students.get(index).setNumberOfCredits(credits);
	}

	public String findStudentsByMajor(String major) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i <students.size(); i++) {
			if (students.get(i).getMajor().toUpperCase().equals(major.toUpperCase())) {
				builder.append("\n");
				builder.append(students.get(i).toString());
			}
		}
		return builder.toString();
	}

	public String findStudentsByCredit(int number) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getNumberOfCredits() >= number) {
				builder.append("\n");
				builder.append(students.get(i).toString());
			}
		}
		return builder.toString();
	}

	public boolean hasStudent(Student s) {
		for (int i = 0; i < students.size(); i++) {
			if (s.equals(students.get(i))) {
				return true;
			}
		}
		return false;
	}

	public Student getStudent(Integer id) throws NotFoundException {
		return students.get(findStudent(id));
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
