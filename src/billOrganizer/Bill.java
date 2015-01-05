package billOrganizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class Bill implements Serializable , Comparable<Bill>{ /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private BillType type;
	private String vendor;
	private double amountDue;
	private Date dateDue;
	private Integer billID;
	private static int billNum;
	private final static SimpleDateFormat FORMAT= new SimpleDateFormat("MM/dd/yyyy"); 
	public Bill(String info) throws FileNotFoundException, ParseException {
		String[] array = info.split("\\s+");
		this.billID = ++billNum;
		this.vendor = array[0];
		this.amountDue = Double.valueOf(array[1]);
		
		this.dateDue = FORMAT.parse(array[2]);
		this.type = BillType.valueOf(array[3]);

	}

	public Bill(String vendor, double amountDue, Date dateDue, BillType billType) {
		this.billID = ++billNum;
		this.vendor = vendor;
		this.amountDue = amountDue;
		this.dateDue = dateDue;
		this.type = billType;
	}

	public Double getAmountDue() {
		return this.amountDue;
	}

	public BillType getBillType() {
		return this.type;
	}

	public Date getBillDate() {
		return this.dateDue;
	}

	public int compareTo(Bill bill) {
		return this.billID.compareTo(bill.billID);
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bill ID: ");
		builder.append(billID);
		builder.append(" Vendor: ");
		builder.append(vendor);
		builder.append(" Bill Type: ");
		builder.append(type);
		builder.append(" Date due: ");
		builder.append(FORMAT.format(dateDue));
		builder.append(" Amount Due: ");
		builder.append(amountDue);
		return builder.toString();
	}

	public static void main(String args[]) {
		try {

			String vendor;
			double amountDue;
			Date dateDue;
			BillType type;
			Bill bill = null;
			//SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
			Scanner inputFile = new Scanner(new File("people.txt"));
			ArrayList<Bill> bills=new ArrayList<Bill>();
			while (inputFile.hasNextLine()) {
				vendor = inputFile.next();
				amountDue = inputFile.nextDouble();

				dateDue = FORMAT.parse(inputFile.next());
				type = BillType.valueOf(inputFile.next());
				inputFile.nextLine();
				bill = new Bill(vendor, amountDue, dateDue, type);
				bills.add(bill);
			

			}
			Collections.sort(bills);
			System.out.println("Sort based on ID: "+bills);
			Collections.sort(bills,new BillAmountComparator());
			System.out.println("Sort based on Bill amount: "+bills);
			Collections.sort(bills,new BillTypeComparator());
			System.out.println("Sort based on Bill type: "+bills);
			Collections.sort(bills,new BillDateComparator());
			System.out.println("Sort based on Bill date: "+bills);
			Bill bill2 = new Bill("Shifra", 34.30,
					FORMAT.parse("01/24/2015"), BillType.CLOTHING);
			System.out.println("Amount due: " + bill.getAmountDue());
			//test compareTo
			System.out.println(bill.compareTo(bill2));

			inputFile.close();
			//or send it in one line at a time
			Scanner input=new Scanner(new File("people.txt"));
			while(input.hasNextLine()){
				String line=input.nextLine();
				Bill bill3= new Bill(line);
				System.out.println(bill3);
				
			}
			input.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (ParseException e) {

			e.printStackTrace();
		}
	}
}
