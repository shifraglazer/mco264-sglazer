package finalProject;

import java.io.Serializable;

public class Course implements Serializable {
	private String courseID;
	private String courseName;
	private Integer numCredits;
	private String deptID;
	private static final long serialVersionUID=1;
public Course(String courseID, String courseName, Integer numCredits, String deptID )throws InvalidDataException{
	this.courseID=courseID;
	this.courseName=courseName;
	if(numCredits<=0){
		throw new InvalidDataException();
	}
	this.numCredits=numCredits;
	this.deptID=deptID;
	
}
public void setCourseName(String courseName){
	this.courseName=courseName;
}
public void setNumberOfCredits(Integer numCredits)throws InvalidDataException{
	if(numCredits<=0){
		throw new InvalidDataException();
	}
	this.numCredits=numCredits;
}
public void setDepartmentID(String deptID){
	this.deptID=deptID;
}
public String getCourseName(){
return this.courseName;	
}
public String getCourseID(){
	return this.courseID;
}
public String getDeptID(){
return this.deptID;	
}
public int getnumCredits(){
return this.numCredits;	
}
public int compareTo(String courseID){
	return this.courseID.compareTo(courseID);
}
public boolean equals(Object o){
	return this.courseID.equals(o);
}
public String toString(){
	StringBuilder builder=new StringBuilder();
	builder.append("Course ID is: ");
	builder.append(courseID);
	builder.append(" Course Name is : ");
	builder.append(courseName);
	builder.append(" Number of credits is: ");
	builder.append(numCredits);
	builder.append(" Department ID is: ");
	builder.append(deptID);
return builder.toString();
}
}
