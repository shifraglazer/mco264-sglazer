package finalProject;

import java.io.Serializable;

public class StudentList implements Serializable {

	private SortedLinkedList<Student> students;
	private static final long serialVersionUID = 1;

	public StudentList() {

		students = new SortedLinkedList<Student>();
	}

	public void addStudent(Student student) throws DuplicateDataException {

		if (hasStudent(student)) {
			throw new DuplicateDataException();
		}

		students.insert(student);
	}

	public void addStudent(Integer StudentID, String LastName,
			String FirstName, Character gender, String major)
			throws DuplicateDataException, MissingDataException,
			InvalidDataException {
		Student newStudent = new Student(StudentID, LastName, FirstName,
				gender, major);
		if (!hasStudent(newStudent)) {
			students.insert(newStudent);
		} else if (hasStudent(newStudent)) {
			throw new DuplicateDataException(
					"Student already exists with this id");
		}
	}

	public void addStudent(Integer StudentID, String LastName,
			String FirstName, String address, String city, String state,
			String zipcode, Character gender, String major)
			throws DuplicateDataException, MissingDataException,
			InvalidDataException {
		Student newStudent = new Student(StudentID, LastName, FirstName,
				address, city, state, zipcode, gender, major);
		if (!hasStudent(newStudent)) {
			students.insert(newStudent);
		} else if (hasStudent(newStudent)) {
			throw new DuplicateDataException(
					"Student already exists with this id");
		}
	}

	public void addCompletedCourse(Integer StudentID, CompletedCourse course)
			throws NotFoundException, DuplicateDataException {
		findStudent(StudentID).addCompletedCourse(course);
	}

	public Double getGPA(Integer studentID) throws NotFoundException {
		return findStudent(studentID).getGpa();
	}

	public int getTotalCredits(Integer studentID) throws NotFoundException {
		return findStudent(studentID).getNumberOfCredits();
	}

	public String getStudentCompletedCourses(Integer id)
			throws NotFoundException {

		return findStudent(id).getCompletedCourses();
	}

	public void remove(Integer StudentID) throws NotFoundException {

		students.remove(findStudent(StudentID));
	}

	public Student findStudent(Integer StudentID) throws NotFoundException {
		LinkedListIterator<Student> iter = students.iterator();
		Student next;
		while (iter.hasNext()) {
			next = iter.next();
			if (next.getStudentID().equals(StudentID) ){
				return next;
			}
		}

		throw new NotFoundException("Student is not found");
	}

	public void modifyStudentLastName(Integer StudentID, String newName)
			throws NotFoundException {
		findStudent(StudentID).setLastName(newName);
	}

	public void modifyStudentFirstName(Integer StudentID, String newName)
			throws NotFoundException {
		findStudent(StudentID).setFirstName(newName);
	}

	public void modifyStudentAddress(Integer StudentID, String address,
			String city, String state, String zipcode) throws NotFoundException {
		Student student = findStudent(StudentID);
		student.setAddress(address);
		student.setCity(city);
		student.setState(state);
		student.setZipcode(zipcode);
	}

	public void modifyStudentGPA(Integer StudentID, Double gpa)
			throws NotFoundException, InvalidDataException {
		findStudent(StudentID).setGpa(gpa);
	}

	public void modifyStudentMajor(Integer StudentID, String major)
			throws NotFoundException {
		findStudent(StudentID).setMajor(major);
	}

	public void modifyStudentCredits(Integer StudentID, Integer credits)
			throws NotFoundException, InvalidDataException {
		findStudent(StudentID).setNumberOfCredits(credits);
	}

	public String findStudentsByMajor(String major) {
		StringBuilder builder = new StringBuilder();
		LinkedListIterator<Student> iter = students.iterator();
		boolean found=false;
		while (iter.hasNext()) {
			Student student = iter.next();
			if (student.getMajor().toUpperCase().equals(major.toUpperCase())) {
				builder.append("\n");
				builder.append(student.toString());
				found=true;
			}
		}
		if(!found){
			builder.append("No students found with specified major");
		}
		return builder.toString();
	}

	public String findStudentsByCredit(int number) {
		StringBuilder builder = new StringBuilder();
		LinkedListIterator<Student> iter = students.iterator();
		boolean found=false;
		while (iter.hasNext()) {
			Student student = iter.next();
			if (student.getNumberOfCredits() == number) {
				builder.append("\n");
				builder.append(student.toString());
				found=true;
			}
		}
		if(!found){
			builder.append("No students found with specified amount of credits");
		}
		return builder.toString();
	}

	public boolean hasStudent(Student s) {
		LinkedListIterator<Student> iter = students.iterator();
		while (iter.hasNext()) {
			Student student = iter.next();
			if (student.equals(s)) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		if (students.isEmpty()) {
			return "There are no students recorded";
		}
		LinkedListIterator<Student> iter = students.iterator();
		StringBuilder a = new StringBuilder();
		while (iter.hasNext()) {
			a.append("\n");
			a.append(iter.next().toString());
		}
		return a.toString();
	}

}
