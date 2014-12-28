package billOrganizer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;



public class BillOrganizer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SortedLinkedList<Bill> list;
	private PriorityQueue<Bill> dateQueue;
	private PriorityQueue<Bill> amountQueue;
	private PriorityQueue<Bill> billTypeQueue;

	public BillOrganizer() {
		list = new SortedLinkedList<Bill>();
		dateQueue = new PriorityQueue<Bill>(new BillDateComparator());
		amountQueue = new PriorityQueue<Bill>(new BillAmountComparator());
		billTypeQueue = new PriorityQueue<Bill>(new BillTypeComparator());
	}
	public BillOrganizer(Scanner input) throws FileNotFoundException, ParseException, DuplicateDataException{
		list=new SortedLinkedList<Bill>();
		dateQueue = new PriorityQueue<Bill>(new BillDateComparator());
		amountQueue = new PriorityQueue<Bill>(new BillAmountComparator());
		billTypeQueue = new PriorityQueue<Bill>(new BillTypeComparator());
		while(input.hasNextLine()){
				String line=input.nextLine();
				Bill bill= new Bill(line);
				list.insert(bill);
				dateQueue.enqueue(bill);
				amountQueue.enqueue(bill);
				billTypeQueue.enqueue(bill);
				
		}
	}
	public BillOrganizer(String filename) throws FileNotFoundException,
			IOException, ClassNotFoundException, DuplicateDataException {
		dateQueue = new PriorityQueue<Bill>(new BillDateComparator());
		amountQueue = new PriorityQueue<Bill>(new BillAmountComparator());
		billTypeQueue = new PriorityQueue<Bill>(new BillTypeComparator());
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename));
		Object object=input.readObject();
		if (object instanceof SortedLinkedList) {
			list = (SortedLinkedList<Bill>)object;
			//System.out.println("read from file");
			//System.out.println(list);
		}
		Node<Bill> node = list.getHead();
		
		Bill bill;
		while (node != null) {
			bill = node.getData();
			dateQueue.enqueue(bill);
			amountQueue.enqueue(bill);
			billTypeQueue.enqueue(bill);
			node = node.getNext();
		}

		input.close();

	}

	public double totalBills() {
		double total = 0;
		Node<Bill> node = list.getHead();
		while (node != null) {
			total += node.getData().getAmountDue();
			node = node.getNext();
		}
		return total;
	}

	public LinkedListIterator<Bill> iteratorByDate() throws NotFoundException{
		LinkedListIterator<Bill> iter=null;
		if(dateQueue==null){
			throw new NotFoundException();
		}
			iter=new LinkedListIterator<Bill>(dateQueue.peek());
		return iter;
		
	}

	public LinkedListIterator<Bill> iteratorByAmount() throws NotFoundException {
		if(amountQueue==null){
			throw new NotFoundException();
		}
		LinkedListIterator<Bill> iter = new LinkedListIterator<Bill>(
				amountQueue.peek());
		return iter;
	}
	public LinkedListIterator<Bill> iteratorByType() throws NotFoundException {
		if(billTypeQueue==null){
			throw new NotFoundException();
		}
		LinkedListIterator<Bill> iter = new LinkedListIterator<Bill>(
				billTypeQueue.peek());
		return iter;
	}

	public void insert(Bill bill) throws DuplicateDataException {
		list.insert(bill);
		dateQueue.enqueue(bill);
		amountQueue.enqueue(bill);
		billTypeQueue.enqueue(bill);
	}
	public void payNextBill(Bill bill) throws NotFoundException{
		list.remove(bill);
		dateQueue.remove(bill);
		amountQueue.remove(bill);
		billTypeQueue.remove(bill);
	}
	public void payNextBill( BillCriteria criteria)
			throws NotFoundException {
		Node<Bill> node = null;
		if (criteria == BillCriteria.BILLDUEDATE) {
			node = dateQueue.peek();
			dateQueue.dequeue();
			amountQueue.remove(node.getData());
			billTypeQueue.remove(node.getData());
		} else if (criteria == BillCriteria.BILLAMOUNT) {
			node = amountQueue.peek();
			amountQueue.dequeue();
			dateQueue.remove(node.getData());
			billTypeQueue.remove(node.getData());
		} else if (criteria == BillCriteria.BILLTYPE) {
			node = billTypeQueue.peek();
			billTypeQueue.dequeue();
			amountQueue.remove(node.getData());
			dateQueue.remove(node.getData());
		}

		list.remove(node.getData());
	}
	public PriorityQueue<Bill> viewByDate(){
		return dateQueue;
	}
	public PriorityQueue<Bill> viewByAmount(){
		return amountQueue;
	}
	public PriorityQueue<Bill> viewByType(){
		return billTypeQueue;
	}


	public void closeOrganizer() throws FileNotFoundException, IOException {
		ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(
				"organizer.dat"));
		write.writeObject(list);
		write.close();
		//System.out.println("written to file");
	}

	public String toString() {
		return list.toString();
	}

	public static void main(String args[]) {
		BillOrganizer organizer = new BillOrganizer();
		SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
		Bill bill;
		try {
			bill = new Bill("Sarah", 40.20, formater.parse("08/25/2014"),
					BillType.GROCERIES);
			organizer.insert(bill);
			Bill bill2 = new Bill("Esther", 210.20,
					formater.parse("04/18/2014"), BillType.FOOD);
			Bill bill3 = new Bill("Etti", 100.40, formater.parse("01/10/2014"),
					BillType.CLOTHING);
			Bill bill4 = new Bill("Shifra", 345.00,
					formater.parse("01/01/2014"), BillType.PHONE);
			organizer.insert(bill2);
			organizer.insert(bill3);
			organizer.insert(bill4);
			System.out.println(organizer);
			System.out.println("Total bills :"+organizer.totalBills());
			System.out.println("View bills by date: \n"+ organizer.viewByDate());
			System.out.println("View bills by amount: \n"+ organizer.viewByAmount());
			System.out.println("View bills by type: \n"+ organizer.viewByType());
			System.out.println("Pay next bill by bill amount");
			organizer.payNextBill(BillCriteria.BILLAMOUNT);
			System.out.println("View bills by amount: \n"+ organizer.viewByAmount());
			System.out.println("Pay bill 3");
			organizer.payNextBill(bill3);
			System.out.println("View bills by amount: \n"+ organizer.viewByAmount());
			organizer.closeOrganizer();
			System.out.println("Write out to file");
			System.out.println("print list: "+ organizer);
			JFileChooser file = new JFileChooser();
			System.out.println("now read in");
			JOptionPane.showMessageDialog(null, "choose data file");
			file.showOpenDialog(null);
			String filename = file.getSelectedFile().getPath();
			BillOrganizer organ = new BillOrganizer(filename);
			System.out.println(organ);

		} catch (DuplicateDataException e) {
		
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (NotFoundException e) {
			
			e.printStackTrace();
		}
	}
}
