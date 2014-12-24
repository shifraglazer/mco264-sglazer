package student;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ManagingStudents {

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		System.out.println("What is the max students that you want to enter?");
		int numStudents = input.nextInt();
		StudentList list = new StudentList(numStudents);
		int option = 0;

		try {

			do {
				System.out
						.println("\n1. Add a student"
								+ "\n2. Remove a student\n3. Modify student data"
								+ "\n4. Display Student information"
								+ "\n5. List all students' information"
								+ "\n6. List students of a specific major"
								+ "\n7. List students that have earned certain number of credits"
								+ "\n8. Store StudentList to file using Object Serialization"
								+ "\n9. Restore StudentList from a disk file using Object Serialization"
								+ "\n10.Exit application");

				option = input.nextInt();
				input.nextLine();

				switch (option) {
				case 1:{
					System.out.println("Please enter StudentID number");
					Integer studentId = input.nextInt();
					input.nextLine();
					System.out.println("Please enter Last name");

					String Last = input.nextLine();
					System.out.println("Please enter First name");
					String first = input.nextLine();
					Character gender;
					do{
					System.out.println("Please enter gender f/m");
					gender = input.next().charAt(0);
					}
					while(gender!='f'&& gender!='m' && gender!='M'&&gender!='F');

					System.out.println("Please enter major");
					input.nextLine();
					String major = input.nextLine();
				
					list.addStudent(studentId, Last, first, gender, major);
					System.out.println("Student added.");
					
					break;
				}
				case 2:{
					System.out
							.println("Please enter studentID of student you would like to remove.");
					int remove = input.nextInt();
					list.remove(remove);
					System.out.println("Student removed.");
					break;
				}
				case 3:{

					System.out
							.println("Enter student id number you want to modify");
					int number;
					int id = input.nextInt();

					do {
						System.out
								.println("What do you want to modify?\n1.Modify last name"
										+ "\n2.Modify address\n3.Modify major\n"
										+ "4.Modify GPA\n5.Modify number of student's credits");
						number = input.nextInt();
					} while (number < 0 || number > 5);
					switch (number) {
					case 1:{
						input.nextLine();
						System.out.println("Enter new last name");
						String lastName = input.nextLine();
						list.getStudent(id).setLastName(lastName);
						break;
					}
					case 2:{
						System.out.println("Enter new address");
						input.nextLine();
						String address = input.nextLine();
						list.getStudent(id).setAddress(address);
						break;
					}
					case 3:{
						System.out.println("Enter new major");
						input.nextLine();
						String major = input.nextLine();
						list.getStudent(id).setMajor(major);
						break;
					}
					case 4:{
						System.out.println("Enter new GPA (double)");
						double gpa = input.nextDouble();
						list.getStudent(id).setGpa(gpa);
						break;
					}
					case 5:{
						System.out.println("Enter modified student credits");
						Integer credits = input.nextInt();
						list.getStudent(id).setNumberOfCredits(credits);
						break;
					}
					}
					System.out.println("Student modified.");
					break;
				}
				case 4:{
					System.out
							.println("Please enter StudentID you wish to display.");
					Integer id = input.nextInt();
					System.out.println(list.getStudent(id).toString());
					break;
				}
				case 5:{
					System.out.println(list.toString());
					break;
				}
				case 6:{
					System.out
							.println("Which major would you like to see list of students for?");
					String majorCo = input.nextLine();

					System.out.println(list.findStudentsByMajor(majorCo));
					break;}
				case 7:{
					System.out
							.println("Please enter the number of credits you want to see the students info for?");
					int credit = input.nextInt();
					System.out.println(list.findStudentsByCredit(credit));
					break;
				}
				case 8:
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
				case 9:{
					System.out.println("Please choose the name of file to read data from");
					JFileChooser fileChooser=new JFileChooser();
					JOptionPane.showMessageDialog (null,"choose data file");
					 fileChooser.showOpenDialog(null);
					String filename=fileChooser.getSelectedFile().getPath();
					
					if(filename!=null){
						try{
						
							ObjectInputStream read=new ObjectInputStream(new FileInputStream(filename));
							list=(StudentList)read.readObject();
							read.close();
						}
						catch(FileNotFoundException e){
							System.out.println("File not found");
						}
						catch(IOException e){
							System.out.println("Input output exception.");
						} catch (ClassNotFoundException e) {
							System.out.println("class not found");
						}
					}
					System.out.println("Data read in.");
					break;
				}
				case 10:{
					System.out.println("thank you, Exiting.");
					System.exit(0);
				}
				}
			} while (option!=10);
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
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Array is full, no more students can be added.");
		} 
		while (option!=10);

	}
}
