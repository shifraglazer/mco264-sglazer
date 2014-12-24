package course;
import java.io.Serializable;
import java.util.ArrayList;


public class CourseList implements Serializable {

		private ArrayList<Course> list;
		private static final long serialVersionUID=1;
		

		public CourseList() {
			
			list = new ArrayList<Course>();
		}

		public void addStudent(Course course) throws DuplicateDataException{
			
			 if (this.list.contains(course)) {
				throw new DuplicateDataException();
			 }
		
				list.add(course);
		}
		public void addCourse(Course c)throws DuplicateDataException{
			if(!this.list.contains(c)){
				list.add(c);
			}
			else{
				throw new DuplicateDataException();
			}
		}
		public void addCourse(String courseID, String courseName,
			 Integer numCredits, String deptID)
				throws DuplicateDataException, MissingDataException,InvalidDataException {
			if (courseID == null||courseName==null||numCredits==null||deptID==null) {
				throw new MissingDataException();
			}
			Course newCourse = new Course(courseID, courseName,numCredits,deptID);
			if (!this.list.contains(newCourse)){
				list.add(newCourse);
			} else {
				throw new DuplicateDataException();
			} 
		}

		public void remove(String courseID) throws NotFoundException {
			boolean found=false;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getCourseID().equals(courseID)) {
					list.remove(list.get(i));
					found=true;
				}
			}
			if(!found){
			throw new NotFoundException();
			}
		}

		public int findCourse(String courseID) throws NotFoundException {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getCourseID().equals(courseID)) {
					return i;
				}
			}
			throw new NotFoundException();
		}

		public void modifyCourseName(String CourseID,
				String newName) throws NotFoundException {
			boolean found=false;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getCourseID().equals(CourseID)) {
					list.get(i).setCourseName(newName);
					found=true;
				}
			}
			if(!found){
			throw new NotFoundException();
			}
			
		}

		public void modifyCourseCredits(String courseID,Integer numCredits)
				throws NotFoundException, InvalidDataException{
			boolean found=false;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getCourseID().equals(courseID)) {
					list.get(i).setNumberOfCredits(numCredits);
					found=true;
				}
			}
			if(!found){
			throw new NotFoundException();
			}
			
		}

		public void modifyCourseDepartment(String courseID, String deptID)
				throws NotFoundException {
			boolean found=false;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getCourseID().equals(courseID)) {
					list.get(i).setDepartmentID(deptID);
					found=true;
				}
			}
			if(!found){
			throw new NotFoundException();
			}
		
		}

	

		public String findCoursesByDept(String dept) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i <list.size(); i++) {
				if (list.get(i).getDeptID().toUpperCase().equals(dept.toUpperCase())) {
					builder.append("\n");
					builder.append(list.get(i).toString());
				}
			}
			return builder.toString();
		}


		public Course getCourse(String id) throws NotFoundException {
			return list.get(findCourse(id));
		}

		public String toString() {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i <list.size(); i++) {
				builder.append("\n");
				builder.append(list.get(i).toString());
			}
			return builder.toString();
		}

	

}
