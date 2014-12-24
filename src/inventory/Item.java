package inventory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
/**
 * 
 * @author mp
 *
 */
public class Item implements Serializable, Comparable<Item>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param   barCode  
	 * @return  true if barcode is valid
	 *          false if barcode isn't valid
	 *          
	 */
	 public static boolean isValidBarCode(String barCode){
     	int totalEven=0;
     	int totalOdd =0;
     	System.out.println("checking bar code validity");
     	if (barCode.length() != 12)
     		return false;
     	else{  //add the value of all digits that appear in the odd positions of the barcode
     			for (int i=0;i<11;i=i+2){
     			totalOdd +=Integer.parseInt(String.valueOf(barCode.charAt(i)));
     			
	        		}
     		  //add the value of all digits that appear in the even positions of the barcode
     		for (int i=1;i<11;i=i+2){
     			totalEven += Integer.parseInt(String.valueOf(barCode.charAt(i)));
     			
     		}
     		//apply the algorithm . multiply odd total by 3 , add to even total
     		
     		int total = totalEven + totalOdd *3;
     		//if total is evenly divisible by 10 then check digit == zero
     		
     	    int remainder = total % 10;;
     	    int checkdigit=0;
     	    //otherwise keep adding to the total until you get to a value that is 
     	    //evenly divisible by zero. The amount you have to add to total till it is
     	    //evenly divisible by zero is the value of the checkdigit
     	    while (remainder != 0){
     	      	total++;
     	    	checkdigit ++;
     	    	remainder = total % 10;
     	    	System.out.print("  remainder= " + remainder + "checkdigit = " + checkdigit);
     	    }
     	    //now check if the checkdigit appears in the last position of the barcode
     	    if (checkdigit ==Integer.parseInt(String.valueOf(barCode.charAt(11))))
     	    	return true;
     	    else 
     	    	return false;
     	}
     	
     }
     
	
	//byte size calculated as follows
	//upc 12 chars  = 24 bytes,
	//description 30 characters = 60 bytes
	//price = 8 bytes
	//quantity =  4 bytes
	 //itemtype = 12 characters = 24 bytes
	public static final int RECORD_SIZE = 120;
	private String upc;
	private String description;
	private Double price;
	private Integer quantity;
	private ItemType itemType;

	/**
	 *  @param       upc - product code
	 *  @param       description
	 *  @param       price 
	 *  @param       quantity 
	 *  @param       item type
	 *  @throws      DataException if data is not reasonable, valid or is missing       
	 * 
	 */
	public Item(String upc, String description, Double price,	Integer quantity,ItemType itemType)
	throws DataException
	   {
		//store the data in this Item instance's fields
		this.upc = upc;
		this.description = description;		
		this.price = price;
		this.quantity =quantity;		
		this.itemType = itemType;
		
		//input validation - check if data is being provided
	    isValid();
	    
		//for debugging
		System.out.println("Item valid");
	}
	
	public Item(String upc, String description, Double price, Integer quantity)throws DataException{
		 this(upc,description,price,quantity,ItemType.NOTAVAIL);
	}
	/**
	 * @param file - reference to RandomAccessFile where record with Item data was stored
	 * @throws IOException if problem reading the file
	 */
	public Item(RandomAccessFile file)throws IOException{
		    //set up Item based on data stored in data disk file
		    //no need to validate since the assumption is that the data was validated 
		    //before it was stored in the file
		try{
		   this.upc = readCharacters(file,12);
		   this.description = readCharacters(file,30);
		   this.price = file.readDouble();
		   this.quantity = file.readInt();	
		   //read in an integer value, convert it to a String format 
		   //convert the String value to enumerated value
		   this.itemType = ItemType.valueOf(String.valueOf(readCharacters(file,12)));
		
		   
		}
		catch(IOException io){
			throw io;
		}		
	}
	
	/**
	 * 
	 * @param dataFile  RandomAccessFile from which text data should be read
	 * @param numChars  how many characters of data should be read
	 * @return String   contains the characters that were read from the file
	 * @throws IOException
	 */
	private String readCharacters (RandomAccessFile dataFile,int numChars)
	  throws IOException{
		  //reads a specific number of characters and places text in String field
		char[] data = new char[numChars];
		String field;
		try{
			for (int i=0;i<numChars;i++)
				  data[i]= dataFile.readChar();
			//now place data into the field
			field = new String (data);
			field = field.trim(); //get rid of trailing spaces
		}
		catch(IOException io){
			throw io;
		}
		return field;
	}
	
	/**
	 * 
	 * @return boolean    true if data is valid 
	 * @throws DataException
	 */
	private boolean isValid()throws DataException{
		if (upc==null || description ==null || price ==null || quantity == null )
			throw new DataException();  //instantiate and throw an Exception
		//for debugging
		System.out.println("Item data was provided");
		// check if data values are reasonable, first check if the barcode is valid
		if (!isValidBarCode(upc))
					throw new DataException("barcode invalid");
		System.out.println("bar code is valid");
		if (quantity < 0 || price < 0.0) throw new DataException();
		return true;
		
	}
	
	/**
	 *  @return description
	 */
	
	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 * @return price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * 
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * 
	 * @return upc
	 */
	public String getUPC() {
		return upc;
	}
	
	/**
	 * 
	 * @return itemType
	 */
	
	public ItemType getType(){
		return itemType;
	}
	/**
	 * 
	 * @param description
	 */	
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	/**
	 * change current price of Item
	 * @param price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 *  change quantity of item in stock
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 *   add additional units to quantity in stock
	 * @param quantity
	 */
	public void replenishInventory(int quantity){
		this.quantity += quantity;
	}
	
	/**
	 *  modify the quantity of Item in stock after one or more units have been sold
	 *  can’t sell an item if insufficient items in stock
	 * @param quantity
	 * @throws Exception  if insufficient inventory to complete the sale
	 */
	public void sell(int quantity)throws InsufficientInventoryException{
		if(this.quantity >= quantity){
			this.quantity-= quantity;}
		else
			throw new InsufficientInventoryException();		
		}
	
	
	/**
	 *  store the Item data in RandomAccessFile record
	 * @param fileName
	 * @param location  - location of this particular Item record
	 * @throws Exception
	 */
	public void rewriteFileRecord(String fileName,long location)throws Exception{
		try{
		RandomAccessFile invFile = 
			new RandomAccessFile(fileName,"rw");
		//seek to correct record in file		  
		   invFile.seek(location);
		//now write out the  record
		 //write out the data in fixed length fields
		   //String fields must be truncated if too large
		   //or padded with blanks if too small
		   writeCharacters(invFile,upc,12);
		   writeCharacters(invFile,description,30);
		   invFile.writeDouble(price);
		   invFile.writeInt(quantity);
		   //convert the enumerated type to a String value and write out up
		   //till 12 characters' worth
		   writeCharacters(invFile,itemType.toString(),12);
		   //close connection to the file
		   invFile.close();
		}
		catch(FileNotFoundException notFound){
			throw new FileNotFoundException();
		}
		catch(IOException io){
			throw io;
		}
	}
	
	/**
	 * store Item data in RandomAccessFile at the end of the file
	 * 				  
	 * @param fileName
	 * @return  location at which this Item's data record has been stored
	 * @throws Exception
	 */
	public long saveNewItem(String fileName)throws Exception{
		long fileLocation;
		long numRecords;
		try{
		RandomAccessFile invFile = new
		   RandomAccessFile(fileName,"rw");
		 //calculate file location where this new record belongs
		   numRecords = invFile.length() / RECORD_SIZE;
		   fileLocation = numRecords * RECORD_SIZE;
		   //move file pointer to that location
		   invFile.seek(fileLocation);
		   //write out the data in fixed length fields
		   //String fields must be truncated if too large
		   //or padded with blanks if too small
		   writeCharacters(invFile,upc,12);  //fixed length field, 12 characters
		   writeCharacters(invFile,description,30);  //fixed length field, 30 characters
		   invFile.writeDouble(price);
		   invFile.writeInt(quantity);
		   //convert the enumerated type to a String value and write out up
		   //till 12 characters' worth
		   writeCharacters(invFile,itemType.toString(),12);
		   //close connection to the file
		   invFile.close();
		   //for debugging
		   System.out.println("wrote record at location" + fileLocation);
		}
		catch(FileNotFoundException notFound){
			throw notFound;
		}
		return fileLocation;
	}
	
	/**
	 * write out a given number of characters to a RandomAccessFile at a given location in the file
	 * @param invFile
	 * @param data
	 * @param numChars
	 * @throws IOException
	 */
	private void writeCharacters(RandomAccessFile invFile,
			String data, int numChars)throws IOException{
		String dataChars;
		try{
			//assume data has the complete number of characters
			dataChars = data.substring(0,numChars); 
			invFile.writeChars(dataChars);
		}
		catch(IndexOutOfBoundsException e){
			//data has less number of characters, must be padded with blanks
			dataChars = data.substring(0,data.length());
			invFile.writeChars(dataChars);
			//now add extra blanks
			for (int i=0; i<numChars-dataChars.length();i++)
				invFile.writeChar(' ');			
		}
		catch(IOException io){
			throw io;
		} 
		
	}
	
	/**
	 * 
	 * @return data that describes a particular Item   
	 */
	public String toString(){
		String info = "\nItem:";
		info += upc + " " + description +
		  " price: " + price + " Quantity: " + quantity + " Type " + itemType.toString();
		return info;
	}
	
	/**
	 * 
	 */
	public int compareTo(Item other){
		//compare the two different Items' bar codes
		return upc.compareTo(other.upc);
	}
	
	/**
	 * 
	 */
	
	public boolean equals(Object other){
		
		if (other instanceof Item){
	   
			
			
			return this.upc.equals(((Item) other).getUPC());
			
		
			
		}
		else{
			return false;
		}
		
	}
	
	
	
}
