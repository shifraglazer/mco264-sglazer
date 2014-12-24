package books2014;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;


public class CollegeTextBooks implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1979209798854874635L;
	private String[] isbns;
	private Long[] filePositions;
	private Integer numBooks;  //how many books have been entered
	
	public CollegeTextBooks(int maxQuantity){
		isbns = new String[maxQuantity];  //will store each book's isbn
		filePositions = new Long[maxQuantity];  //will store the file position of the book with a specific isbn
	    numBooks = 0;
	}
	
	public CollegeTextBooks(String bookTextFileName, String randomFileName,int maxQuantity)throws FileNotFoundException, DuplicateDataException,InvalidDataException,IOException{
		String isbn, courseID;
		Double  cost;
		isbns = new String[maxQuantity];  //will store each book's isbn
		filePositions = new Long[maxQuantity];  //will store the file position of the book with a specific isbn
	    numBooks = 0;
	    
	    Scanner bookFile = new Scanner (new File(bookTextFileName));
		while (bookFile.hasNext()){
			
			isbn = bookFile.next();
			courseID  = bookFile.next();
			cost = bookFile.nextDouble();
			if (hasBook(isbn)){
				throw new DuplicateDataException();
			}
			else{
				//add the book to the collection
				CollegeText aBook = new CollegeText(isbn,courseID,cost);
				Long filePosition = aBook.writetoFile(randomFileName);
				isbns[numBooks] = isbn;
				filePositions[numBooks]= filePosition;
				numBooks++;
				
			}
			
		
	    }
		bookFile.close();
	    
		
		
	}
	
	private boolean hasBook(String isbn){
		for (int i =0;i< numBooks;i++){
			if (isbn.equals(isbns[i])){
				return true;
			}
		}
		return false;
	}
	
	
	
	Long getLocation(String isbn)throws NotFoundException{
		for (int i =0; i< numBooks;i++){
			if (isbn.equals(isbns[i])){
				return filePositions[i];
			}
		}
		throw new NotFoundException();
	}

	
	

}
