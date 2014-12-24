package student;

import java.io.Serializable;

public class StudentList implements Serializable {

	private Student[] students;
	private int numStudents;
	private static final long serialVersionUID=1;
	

	public StudentList(int length) {
		
		students = new Student[length];
		numStudents = 0;
	}

	public void addStudent(Student student) throws DuplicateDataException,
			ArrayIndexOutOfBoundsException {
		if(numStudents==students.length){
			throw new ArrayIndexOutOfBoundsException();
		}
		else if (hasStudent(student)) {
			throw new DuplicateDataException();
		}
	
			students[numStudents++] = student;
	}

	public void addStudent(Integer StudentID, String LastName,
			String FirstName, Character gender, String major)
			throws DuplicateDataException, MissingDataException,
			InvalidDataException, ArrayIndexOutOfBoundsException {
		if (StudentID == null) {
			throw new MissingDataException();
		}
		Student newStudent = new Student(StudentID, LastName, FirstName,
				gender, major);
		if (!hasStudent(newStudent) && numStudents < students.length) {
			students[numStudents++] = newStudent;
		} else if (hasStudent(newStudent)) {
			throw new DuplicateDataException();
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	public void remove(Integer StudentID) throws NotFoundException {
		int index = findStudent(StudentID);
		int last = numStudents - 1;
		students[index] = students[last];
		numStudents--;

	}

	public int findStudent(Integer StudentID) throws NotFoundException {
		for (int i = 0; i < numStudents; i++) {
			if (students[i].getStudentID().equals(StudentID)) {
				return i;
			}
		}
		throw new NotFoundException();
	}

	public void modifyStudentLastName(Integer StudentID,
			String newName) throws NotFoundException {
		int index = findStudent(StudentID);
		students[index].setLastName(newName);
	}

	public void modifyStudentAddress(Integer StudentID, String address,
			String city, String state, String zipcode) throws NotFoundException {
		int index = findStudent(StudentID);
		students[index].setAddress(address);
		students[index].setCity(city);
		students[index].setState(state);
		students[index].setZipcode(zipcode);
	}

	public void modifyStudentGPA(Integer StudentID, Double gpa)
			throws NotFoundException, InvalidDataException {
		int index = findStudent(StudentID);
		students[index].setGpa(gpa);
	}

	public void modifyStudentMajor(Integer StudentID, String major)
			throws NotFoundException {
		int index = findStudent(StudentID);
		students[index].setMajor(major);
	}

	public void modifyStudentCredits(Integer StudentID, Integer credits)
			throws NotFoundException, InvalidDataException {
		int index = findStudent(StudentID);
		students[index].setNumberOfCredits(credits);
	}

	public String findStudentsByMajor(String major) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < numStudents; i++) {
			if (students[i].getMajor().toUpperCase().equals(major.toUpperCase())) {
				builder.append("\n");
				builder.append(students[i].toString());
			}
		}
		return builder.toString();
	}

	public String findStudentsByCredit(int number) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < numStudents; i++) {
			if (students[i].getNumberOfCredits() >= number) {
				builder.append("\n");
				builder.append(students[i].toString());
			}
		}
		return builder.toString();
	}

	public boolean hasStudent(Student s) {
		for (int i = 0; i < numStudents; i++) {
			if (s.equals(students[i])) {
				return true;
			}
		}
		return false;
	}

	public Student getStudent(Integer id) throws NotFoundException {
		return students[findStudent(id)];
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < numStudents; i++) {
			builder.append("\n");
			builder.append(students[i].toString());
		}
		return builder.toString();
	}

}
