package linkedList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class UseLinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
		LinkedList<Student> studentList = new LinkedList<Student>();
		Scanner studentDataFile = new Scanner (new File("./studentData.txt"));
		Student aStudent=null;
		while (studentDataFile.hasNext()){
			//read one Student record at a time			
			 aStudent = new Student(studentDataFile);
			//place reference into LinkedList
			studentList.insert(aStudent);
			
			
		}
		studentDataFile.close();
		//use internal iterator to display LinkedList contents
		studentList.iter.reset();
		while (studentList.iter.hasNext()){
			System.out.println("Student " + studentList.iter.next());
		}
		studentList.remove(aStudent);
		//use external iterator to display LinkedList contents
		LinkedListIterator<Student> iterator = studentList.iterator();
		while (iterator.hasNext()){
			System.out.println("Student " + iterator.next());
		}
		}
		catch(FileNotFoundException ex){
			System.out.println("cant find the data file");
			System.exit(1);
		}
		catch(IOException ex2){
			System.out.println("problem reading data from file");
		}
		catch(Exception ex3){
			System.out.println(ex3.getMessage());
		}

	}

}
