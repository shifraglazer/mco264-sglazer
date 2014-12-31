package finalProject;

import java.util.Iterator;
import java.util.LinkedList;

public class CourseList {
LinkedList<Course> courses;

public CourseList(){
	courses=new LinkedList<Course>();
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
public void modifyCourseName(String courseID,String courseName)throws NotFoundException{
	Course course=find(courseID);
	course.setCourseName(courseName);
}
public void modifyNumCredits(String courseID, int numCredits)throws NotFoundException, InvalidDataException{
	Course course=find(courseID);
	course.setNumberOfCredits(numCredits);
}
public void setDeptID(String courseID,String deptID)throws NotFoundException{
Course course=find(courseID);
course.setDepartmentID(deptID);
}
public Course getCourse(String courseID)throws  NotFoundException{
	return find(courseID);
}
public String getCourseName(String courseID) throws NotFoundException{
	Course course=find(courseID);
	return course.getCourseName();
}
public int getNumCredits(String courseID) throws NotFoundException{
	Course course=find(courseID);
	return course.getnumCredits();
}
public Course find(String courseID)throws NotFoundException{
	Iterator<Course> iter=courses.iterator();
	while(iter.hasNext()){
		Course next=iter.next();
		if(next.getCourseID()==courseID){
			return next;
		}
	}
	throw new NotFoundException();
}
public boolean hasCourse(Course course){
	Iterator<Course> iter=courses.iterator();
	while(iter.hasNext()){
		Course next=iter.next();
		if(next.equals(course)){
			return true;
		}
	}
	return false;
}
}
