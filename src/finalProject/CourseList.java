package finalProject;

import java.io.Serializable;


public class CourseList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SortedLinkedList<Course> courses;

	public CourseList() {
		courses = new SortedLinkedList<Course>();
	}

	public void addCourse(Course c) throws DuplicateDataException {
		if (!courses.contains(c)) {
			courses.insert(c);
		} else {
			throw new DuplicateDataException(
					"Course with this ID already exists");
		}
	}

	public void addCourse(String courseID, String courseName,
			Integer numCredits, String deptID) throws InvalidDataException,
			DuplicateDataException {
		Course course = new Course(courseID, courseName, numCredits, deptID);
		if (!courses.contains(course)) {
			courses.insert(course);
		} else {
			throw new DuplicateDataException(
					"Course with this ID already exists");
		}
	}

	public void modifyCourseName(String courseID, String courseName)
			throws NotFoundException {
		Course course = find(courseID);
		course.setCourseName(courseName);
	}

	public void modifyNumCredits(String courseID, int numCredits)
			throws NotFoundException, InvalidDataException {
		Course course = find(courseID);
		course.setNumberOfCredits(numCredits);
	}

	public void setDeptID(String courseID, String deptID)
			throws NotFoundException {
		Course course = find(courseID);
		course.setDepartmentID(deptID);
	}

	public Course getCourse(String courseID) throws NotFoundException {
		return find(courseID);
	}

	public String getCourseName(String courseID) throws NotFoundException {
		Course course = find(courseID);
		return course.getCourseName();
	}

	public int getNumCredits(String courseID) throws NotFoundException {
		Course course = find(courseID);
		return course.getnumCredits();
	}

	public Course find(String courseID) throws NotFoundException {
		LinkedListIterator<Course> iter = courses.iterator();
		while (iter.hasNext()) {
			Course next = iter.next();
			if (next.getCourseID().equals(courseID)) {
				return next;
			}
		}
		throw new NotFoundException("Course not found");
	}
	public LinkedListIterator<Course> iterator(){
		return courses.iterator();
	}
	public boolean hasCourse(Course course) {
		LinkedListIterator<Course> iter = courses.iterator();
		while (iter.hasNext()) {
			Course next = iter.next();
			if (next.equals(course)) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		if (courses.isEmpty()) {
			return "There are no courses recorded";
		}
		StringBuilder builder = new StringBuilder();
		LinkedListIterator<Course> iter = courses.iterator();
		while (iter.hasNext()) {
			Course course = iter.next();
			builder.append("\n");
			builder.append(course.toString());
		}
		return builder.toString();
	}
}
