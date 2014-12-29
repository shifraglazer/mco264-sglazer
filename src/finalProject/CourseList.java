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
	}
	else{
		throw new DuplicateDataException();
	}
}
public void addCourse(String courseID, String courseName, Integer numCredits, String deptID) throws InvalidDataException, DuplicateDataException{
	Course course=new Course(courseID,courseName,numCredits,deptID);
	if(!courses.contains(course)){
		courses.add(course);
	}
	else{
		throw new DuplicateDataException();
	}
}
public void modifyCourseName(String courseID,String courseName){
	int index=courses.find(courseID);
	courses.get(index).setCourseName(courseName);
}
public void modifyNumCredits(String courseID, int numCredits){
	int index=courses.find(courseID);
	courses.get(index).setNumberOfCredits(numCredits);
}
public void setDeptID(String courseID,String deptID){
int index=courses.find(courseID);
courses.get(index).setDepartmentID(deptID);
}
public Course getCourse(String courseID){
	int index=courses.find(courseID);
	return courses.get(index);
}
public String getCourseName(String courseID){
	int index=courses.find(courseID);
	return courses.get(index).getCourseName();
}
public int getNumCredits(String courseID){
	int index=courses.find(courseID);
	return courses.get(index).getnumCredits();
}
public int find(String courseID){
	
	Collections.sort(courses);
	return Collections.binarySearch(courses, courseID);
}
}
