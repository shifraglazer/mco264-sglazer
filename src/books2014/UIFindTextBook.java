package books2014;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class UIFindTextBook {

	public static void main(String[] args) {
		// get index file read into ram
		// get file name
		JFileChooser fileChooser = new JFileChooser();
		// null means anywhere in screen
		
		JOptionPane.showMessageDialog(null, "select random access file");
		fileChooser.showOpenDialog(null);
		String randomFileName = fileChooser.getSelectedFile().getPath();

		JOptionPane.showMessageDialog(null, "select index file name");
		fileChooser.showOpenDialog(null);
		String indexFileName = fileChooser.getSelectedFile().getPath();

		System.out.println("random file name " + randomFileName);
		System.out.println(" index file name " + indexFileName);
	
		
		try{
		RandomAccessFile index= new RandomAccessFile(new File(indexFileName),"rw");
			ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(indexFileName));
			CollegeTextBooks indexs=(CollegeTextBooks)inStream.readObject();
			System.out.println("index file was read in successfully");
			System.out.println("What isbn are you looking for?");
			Scanner keyboard= new Scanner(System.in);
			String isbn=keyboard.next();
			Long location = indexs.getLocation(isbn);
			CollegeText coltext = new CollegeText(randomFileName, location);
			System.out.println(coltext.toString());
			keyboard.close();
			index.close();
			inStream.close();
		} catch (FileNotFoundException notFound) {
			System.out.println("file not found exiting application");
		} catch (IOException io) {
			System.out.println("io exception exiting application");
		}
		catch(ClassNotFoundException ex){
			System.out.println("class inconsistancy, exiting application");
		} catch (NotFoundException e) {
			System.out.println("Not found, exiting application");
		}
	

	}

}
