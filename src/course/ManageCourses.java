package course;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;



public class ManageCourses {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
	
		CourseList list = new CourseList();
		int option = 0;


			do {

				try {
				System.out
						.println("\n1. Add a course"
								+ "\n2. Remove a course\n3. Modify course data"
								+ "\n4. Display course information"
								+ "\n5. List all courses' information"
								+ "\n6. List courses offered by a specific department"
								+ "\n7. Store CourseList to file using Object Serialization"
								+ "\n8. Restore CourseList from a disk file using Object Serialization (needs to be written to disk already)"
								+ "\n9.Exit application");

				option = input.nextInt();
				input.nextLine();

				switch (option) {
				case 1:{
					System.out.println("Please enter CourseID");
					String courseId = input.nextLine();
					
					System.out.println("Please enter Course name");

					String name = input.nextLine();
					System.out.println("Please enter DepartmentID");
					String dept = input.nextLine();
				
					System.out.println("Please enter number of credits");
					
					Integer credits = input.nextInt();
			
					list.addCourse(courseId, name, credits, dept);
					System.out.println("Course added.");
					
					break;
				}
				case 2:{
					System.out
							.println("Please enter courseID of student you would like to remove.");
					String remove = input.nextLine();
					list.remove(remove);
					System.out.println("Course removed.");
					break;
				}
				case 3:{

					System.out
							.println("Enter Course id number you want to modify. Only courses that were added to the course list can be modified.");
					int number;
					String id = input.nextLine();

					do {
						System.out
								.println("What do you want to modify?\n1.Modify Course name"
										+ "\n2.Modify number of student's credits\n3.Modify department");
						number = input.nextInt();
					} while (number < 0 || number > 3);
					switch (number) {
					case 1:{
						input.nextLine();
						System.out.println("Enter new course name");
						String Name = input.nextLine();
						list.getCourse(id).setCourseName(Name);
						break;
					}
					case 2:{
						System.out.println("Enter modified course credits");
						Integer credits = input.nextInt();
						list.getCourse(id).setNumberOfCredits(credits);
						break;
					}
					case 3:{
						System.out.println("Enter new departmentID");
						input.nextLine();
						String dept = input.nextLine();
						list.getCourse(id).setDepartmentID(dept);
						break;
					}
		
					}
					System.out.println("Course modified.");
					break;
				}
				case 4:{
					System.out
							.println("Please enter CourseID you wish to display.");
					String id = input.nextLine();
					System.out.println(list.getCourse(id).toString());
					break;
				}
				case 5:{
					System.out.println(list.toString());
					break;
				}
				case 6:{
					System.out
							.println("Which department would you like to see list of students for?");
					String dept = input.nextLine();

					System.out.println(list.findCoursesByDept(dept));
					break;}
			
				case 7:
				{
					System.out.println("Choose the name of the file to store the data");
					JFileChooser fileChooser=new JFileChooser();
					JOptionPane.showMessageDialog (null,"choose data file");
					 fileChooser.showOpenDialog(null);
					String filename=fileChooser.getSelectedFile().getPath();
					try{
						ObjectOutputStream file=new ObjectOutputStream(new FileOutputStream(filename));
						file.writeObject(list);
						file.close();
					}
					catch(IOException e){
						System.out.println("Input output exception.");
					}
					System.out.println("Data written out to file");
					break;
				}
				case 8:{
					System.out.println("Please choose the name of file to read data from NOTE: can only read data from a file that contains the right class");
					JFileChooser fileChooser=new JFileChooser();
					JOptionPane.showMessageDialog (null,"choose data file");
					 fileChooser.showOpenDialog(null);
					String filename=fileChooser.getSelectedFile().getPath();
					
					if(filename!=null){
						try{
						
							ObjectInputStream read=new ObjectInputStream(new FileInputStream(filename));
							list=(CourseList)read.readObject();
							read.close();
							System.out.println("Data read in.");
						}
						catch(FileNotFoundException e){
							System.out.println("File not found");
						}
						catch(IOException e){
							System.out.println("Input output exception.");
						} catch (ClassNotFoundException e) {
							System.out.println("class not found");
						}
						catch (ClassCastException e) {
							System.out.println("can't read file");
						}
						
					}
					
					break;
				}
				case 9:{
					System.out.println("thank you, Exiting.");
					System.exit(0);
				}
				}
			
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		}
				
		 catch (InvalidDataException e) {
			System.out.println(e.getMessage());
		}

		 catch (DuplicateDataException e) {
			System.out.println(e.getMessage());
		} catch (MissingDataException e) {
			System.out.println(e.getMessage());
		} 
	
			}
			 while (option!=9);
			input.close();
	
	}
}
