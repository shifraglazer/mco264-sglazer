package finalProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class StudentRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StudentList students;
	CourseList courses;

	public StudentRecord() {
		students = new StudentList();
		courses = new CourseList();
	}

	public void addStudent(Student s) throws DuplicateDataException {
		students.addStudent(s);
	}

	private void addStudent(Integer id, String firstName, String lastname,
			String address, String city, String state, String zipcode,
			Character gender, String major) throws DuplicateDataException,
			MissingDataException, InvalidDataException {
		students.addStudent(id, lastname, firstName, address, city, state,
				zipcode, gender, major);

	}

	private void addCourse(String courseID, String name, Integer credit,
			String deptID) throws InvalidDataException, DuplicateDataException {
		courses.addCourse(courseID, name, credit, deptID);
	}

	public void removeStudent(Integer studentID) throws NotFoundException {
		students.remove(studentID);
	}

	public void modifyStudentLastName(Integer StudentID, String name)
			throws NotFoundException {
		students.modifyStudentLastName(StudentID, name);
	}

	public void modifyStudentFirstName(Integer StudentID, String name)
			throws NotFoundException {
		students.modifyStudentFirstName(StudentID, name);
	}

	public void modifyStudentAddress(Integer StudentID, String address,
			String city, String zipcode, String state) throws NotFoundException {
		students.modifyStudentAddress(StudentID, address, city, state, zipcode);
	}

	public void modifyStudentMajor(Integer StudentID, String major)
			throws NotFoundException {
		students.modifyStudentMajor(StudentID, major);
	}

	public double getStudentGPA(Integer studentID) throws NotFoundException {
		return students.getGPA(studentID);
	}

	public int getStudentNumberOfCredits(Integer studentID)
			throws NotFoundException {
		return students.getTotalCredits(studentID);
	}

	public String getStudentInformation(Integer studentID)
			throws NotFoundException {
		return students.findStudent(studentID).toString();
	}

	public void addCourse(Course c) throws DuplicateDataException {
		courses.addCourse(c);
	}

	public String listStudents() {
		return students.toString();
	}

	public String findStudentsByCredit(int credit) {
		return students.findStudentsByCredit(credit);
	}

	public String findStudentsByMajor(String id) {
		return students.findStudentsByMajor(id);
	}

	public String listCourses() {
		return courses.toString();
	}

	private String getStudentCompleteCourses(Integer id)
			throws NotFoundException {
		return students.getStudentCompletedCourses(id);
	}

	public void addCompletedCourse(Integer StudentID, String courseID,
			Semester semesterCompleted, int yearCompleted, Grade grade)
			throws NotFoundException, InvalidDataException,
			DuplicateDataException {
		Course course = courses.find(courseID);
		CompletedCourse completed = new CompletedCourse(course.getCourseID(),
				course.getCourseName(), course.getnumCredits(),
				course.getDeptID(), semesterCompleted, yearCompleted, grade,
				StudentID);
		students.addCompletedCourse(StudentID, completed);
	}

	public String getCourseInformation(String id) throws NotFoundException {
		return courses.getCourse(id).toString();
	}

	public void modifyStudentGPA(Integer id, Double gpa)
			throws NotFoundException, InvalidDataException {
		students.modifyStudentGPA(id, gpa);
	}

	public void modifyCourseName(String id, String name)
			throws NotFoundException {
		courses.modifyCourseName(id, name);
	}

	public void modifyCourseCredit(String id, int num)
			throws NotFoundException, InvalidDataException {
		courses.modifyNumCredits(id, num);
	}

	public static void main(String[] args) {
		StudentRecord records = new StudentRecord();
		int choice = 0;
		Scanner keyboard = new Scanner(System.in);
		do {
			do {
				System.out.println("Pick choice from menu: "
						+ "\n1. Add Student" + "\n2. Add Course"
						+ "\n3. Remove Student"
						+ "\n4. Modify Student Information"
						+ "\n5. Lookup Students' Information"
						+ "\n6. List Courses"
						+ "\n7. Lookup Student Information"
						+ "\n8. Lookup Course Information"
						+ "\n9. Add Completed Course"
						+ "\n10. Modify course information"
						+ "\n11. Read Data from file"
						+ "\n12. Write records to file" + "\n13. Exit");

				choice = keyboard.nextInt();
			} while (choice < 1 | choice > 13);
			switch (choice) {

			case 1: {
				System.out.println("Enter student ID");
				Integer id = keyboard.nextInt();
				keyboard.nextLine();
				System.out.println("Enter First name");
				String FirstName = keyboard.nextLine();
				System.out.println("Enter Last name");
				String Lastname = keyboard.nextLine();
				System.out.println("Enter address");
				String address = keyboard.nextLine();
				System.out.println("Enter city");
				String city = keyboard.nextLine();
				System.out.println("Enter state");
				String state = keyboard.nextLine();
				System.out.println("Enter zipcode");
				String zipcode = keyboard.nextLine();
				Character gender = null;
				do {
					System.out.println("Enter gender (f/m)");
					String g = keyboard.nextLine();
					gender = g.charAt(0);
				} while (gender != 'f' & gender != 'F' & gender != 'M'
						& gender != 'm');
				System.out.println("Enter major");
				String major = keyboard.nextLine();
				try {
					records.addStudent(id, FirstName, Lastname, address, city,
							state, zipcode, gender, major);
				} catch (MissingDataException e) {
					System.out.println(e.getMessage());
				} catch (InvalidDataException e) {
					System.out.println(e.getMessage());
				} catch (DuplicateDataException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("Student added");
				break;
			}
			case 2: {
				System.out.println("Enter course ID");
				String courseID = keyboard.next();
				keyboard.nextLine();
				System.out.println("Enter course title");
				String name = keyboard.nextLine();
				System.out.println("Enter credit value");
				Integer credit = 0;
				do {
					credit = keyboard.nextInt();
				} while (credit < 0);
				System.out.println("Enter DeptID");
				String deptID = keyboard.next();
				try {
					records.addCourse(courseID, name, credit, deptID);
					System.out.println("Course added");
				} catch (InvalidDataException e) {
					System.out.println(e.getMessage());
				} catch (DuplicateDataException e) {
					System.out.println(e.getMessage());
				}

				break;
			}
			case 3: {
				System.out.println("Enter StudentID to remove student");
				Integer studentID = keyboard.nextInt();

				try {
					records.removeStudent(studentID);
					System.out.println("Student removed");
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 4: {
				System.out.println("Enter student ID to modify");
				Integer id = keyboard.nextInt();
				int num = 0;
				do {
					System.out.println("Enter choice:"
							+ "\n1. Modify First name" + "\n2. Modify LastName"
							+ "\n3. Modify address"
							+ "\n4. Modify student major");
					num = keyboard.nextInt();
				} while (num < 1 | num > 4);
				switch (num) {
				case 1: {
					System.out.println("Enter First name");
					keyboard.nextLine();
					String first = keyboard.nextLine();
					try {
						records.modifyStudentFirstName(id, first);
						System.out.println("Last name modified");

					} catch (NotFoundException e) {
						System.out.println(e.getMessage());

					}
					break;
				}
				case 2: {
					keyboard.nextLine();
					System.out.println("Enter Last name");
					String last = keyboard.nextLine();
					try {
						records.modifyStudentLastName(id, last);
						System.out.println("Last name modified");

					} catch (NotFoundException e) {
						System.out.println(e.getMessage());

					}
					break;
				}
				case 3: {
					keyboard.nextLine();
					System.out.println("Enter address");
					String address = keyboard.nextLine();
					System.out.println("Enter city");
					String city = keyboard.nextLine();
					System.out.println("Enter state");
					String state = keyboard.nextLine();
					System.out.println("Enter zipcode");
					String zipcode = keyboard.next();
					try {
						records.modifyStudentAddress(id, address, city,
								zipcode, state);
						System.out.println("Address modified");

					} catch (NotFoundException e) {
						System.out.println(e.getMessage());

					}
					break;

				}
				case 4: {
					keyboard.nextLine();
					System.out.println("Enter new Major");
					String major = keyboard.nextLine();
					try {
						records.modifyStudentMajor(id, major);
						System.out.println("major modified");

					} catch (NotFoundException e) {
						System.out.println(e.getMessage());

					}
					break;
				}
				}
				break;
			}
			case 5: {
				int num = 0;
				do {
					System.out.println("Enter choice"
							+ "\n1. List all students and their information"
							+ "\n2. List students by major"
							+ "\n3. List students by credit");
					num = keyboard.nextInt();
				} while (num < 1 | num > 3);
				switch (num) {
				case 1: {
					System.out.println(records.listStudents());
					break;
				}
				case 2: {
					keyboard.nextLine();
					System.out.println("Enter major");
					String major = keyboard.nextLine();
					System.out.println(records.findStudentsByMajor(major));
					break;
				}
				case 3: {
					System.out.println("Enter number credits");
					int credits = keyboard.nextInt();
					System.out.println(records.findStudentsByCredit(credits));
					break;
				}
				}

				break;
			}
			case 6: {
				System.out.println(records.listCourses());
				break;
			}
			case 7: {
				int num = 0;
				do {
					System.out.println("Enter choice"
							+ "\n 1. List student information"
							+ "\n 2. List student completed courses");
					num = keyboard.nextInt();
				} while (num < 1 | num > 2);
				System.out.println("Enter student ID");
				Integer id = keyboard.nextInt();
				try {
					switch (num) {
					case 1: {
						System.out.println(records.getStudentInformation(id));
						break;
					}
					case 2: {
						System.out.println(records
								.getStudentCompleteCourses(id));
					}
					}

				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 8: {
				keyboard.nextLine();
				System.out.println("Enter course ID");
				String id = keyboard.nextLine();
				try {
					System.out.println(records.getCourseInformation(id));
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 9: {
				System.out.println("Enter student ID");
				Integer id = keyboard.nextInt();
				keyboard.nextLine();
				System.out.println("Enter course ID");
				String courseID = keyboard.nextLine();
				String semester;
				do {
					System.out
							.println("Enter semester (FALL, SPRING, SUMMER1, SUMMER2)");
					semester = keyboard.nextLine();
					semester = semester.toUpperCase();

				} while (!semester.equals("FALL") & !semester.equals("SPRING")
						& !semester.equals("SUMMER1")
						& !semester.equals("SUMMER2"));
				Semester sem = Semester.valueOf(semester.toUpperCase());
				Calendar cal = Calendar.getInstance();
				int y = cal.get(Calendar.YEAR);
				int year = 3020;
				do {
					System.out.println("Enter year completed");
					year = keyboard.nextInt();
				} while (year > y);
				keyboard.nextLine();
				System.out.println("Enter Grade (A,B,C,D,F)");
				String grade = keyboard.nextLine();
				Grade newGrade = Grade.valueOf(grade.substring(0, 1)
						.toUpperCase());
				try {
					records.addCompletedCourse(id, courseID, sem, year,
							newGrade);
					System.out.println("Completed course added to student");
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());

				} catch (InvalidDataException e) {
					System.out.println(e.getMessage());

				} catch (DuplicateDataException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 10: {
				int num = 0;
				do {

					System.out.println("Enter choice:"
							+ "\n1. Modify course name"
							+ "\n2. Moidfy course credit");
					num = keyboard.nextInt();
					System.out.println("Enter courseID");
					keyboard.nextLine();
					String id = keyboard.nextLine();
					switch (num) {
					case 1: {
						keyboard.nextLine();
						System.out.println("Enter course name");
						String name = keyboard.nextLine();
						try {
							records.modifyCourseName(id, name);
							System.out.println("Course name modified");
						} catch (NotFoundException e) {
							System.out.println(e.getMessage());
						}

						break;
					}
					case 2: {
						System.out.println("Enter course credit");
						int credit = keyboard.nextInt();
						try {
							records.modifyCourseCredit(id, credit);
							System.out.println("Course credit modified");
						} catch (NotFoundException | InvalidDataException e) {
							System.out.println(e.getMessage());
						}

						break;
					}
					}
				} while (choice < 1 | choice > 2);

				break;
			}
			case 11: {
				System.out
						.println("Please choose the name of file to read data from NOTE: can only read data from a file that contains the right class");
				JFileChooser fileChooser = new JFileChooser();
				JOptionPane.showMessageDialog(null, "choose data file");
				fileChooser.showOpenDialog(null);
				String filename = fileChooser.getSelectedFile().getPath();

				if (filename != null) {
					try {

						ObjectInputStream read = new ObjectInputStream(
								new FileInputStream(filename));
						records = (StudentRecord) read.readObject();
						read.close();
						System.out.println("Data read in.");
					} catch (FileNotFoundException e) {
						System.out.println("File not found");
					} catch (IOException e) {
						System.out.println("Input output exception.");
					} catch (ClassNotFoundException e) {
						System.out.println("class not found");
					} catch (ClassCastException e) {
						System.out.println("can't read file");
					}

				}
				break;
			}
			case 12: {
				System.out
						.println("Choose the name of the file to store the data");
				JFileChooser fileChooser = new JFileChooser();
				JOptionPane.showMessageDialog(null, "choose data file");
				fileChooser.showOpenDialog(null);
				String filename = fileChooser.getSelectedFile().getPath();
				try {
					ObjectOutputStream file = new ObjectOutputStream(
							new FileOutputStream(filename));
					file.writeObject(records);
					file.close();
					System.out.println("Data written out to file");
				} catch (IOException e) {
					System.out.println("Input output exception.");
				}

				break;
			}
			case 13: {
				System.out.println("Exiting application");
				break;
			}

			}

		} while (choice != 13);
		keyboard.close();

	}
}
