package finalProject;

import java.util.ArrayList;
import java.util.Collections;

public class CourseList {
ArrayList<Course> courses;

public CourseList(){
	courses=new ArrayList<Course>();
}
public void addCourse(Course c) throws DuplicateDataException{
	if(!courses.contains(c)){
		courses.add(c);
		Collections.sort(courses);
	}
	else{
		throw new DuplicateDataException();
	}
}
public void addCourse(String courseID, String courseName, Integer numCredits, String deptID) throws InvalidDataException, DuplicateDataException{
	Course course=new Course(courseID,courseName,numCredits,deptID);
	if(!courses.contains(course)){
		courses.add(course);
		Collections.sort(courses);
	}
	else{
		throw new DuplicateDataException();
	}
}
public void modifyCourseName(String courseID,String courseName)throws InvalidDataException{
	int index=find(courseID);
	courses.get(index).setCourseName(courseName);
}
public void modifyNumCredits(String courseID, int numCredits)throws InvalidDataException{
	int index=find(courseID);
	courses.get(index).setNumberOfCredits(numCredits);
}
public void setDeptID(String courseID,String deptID)throws InvalidDataException{
int index=find(courseID);
courses.get(index).setDepartmentID(deptID);
}
public Course getCourse(String courseID)throws InvalidDataException{
	int index=find(courseID);
	return courses.get(index);
}
public String getCourseName(String courseID) throws InvalidDataException{
	int index=find(courseID);
	return courses.get(index).getCourseName();
}
public int getNumCredits(String courseID) throws InvalidDataException{
	int index=find(courseID);
	return courses.get(index).getnumCredits();
}
public int find(String courseID) throws InvalidDataException{
	Course course=new Course(courseID,null,3,null);
	return Collections.binarySearch(courses, course);
}
}
