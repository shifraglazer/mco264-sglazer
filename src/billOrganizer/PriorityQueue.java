package billOrganizer;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class PriorityQueue<T extends Serializable & Comparable<T>> {
	SortedLinkedList<T> list;
	Comparator<T> comparator;

	public PriorityQueue(Comparator<T> comparator) {
		list = new SortedLinkedList<T>();
		this.comparator = comparator;
	}

	public void enqueue(T data) throws DuplicateDataException {
		list.insert(data, comparator);
	}
	public Node<T> dequeue() throws NotFoundException {
		Node<T> head=list.getHead();
		list.remove(head.getData());
		return head;
	}
	public Node<T> peek() throws NotFoundException {
		return list.getHead();
		
	}
	public Node<T> remove(T data) throws NotFoundException{
		Node<T> remove=list.find(data);
		list.remove(data);
		return remove;
	}
	public String toString(){
		return list.toString();
	}
	public static void main(String[] args) {
		PriorityQueue<Bill> queue = new PriorityQueue<Bill>(
				new BillAmountComparator());
		SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
		try {
			queue.enqueue(new Bill("Shifra", 345.00, formater
					.parse("01/01/2014"), BillType.CLOTHING));
		queue.enqueue(new Bill("Chayala", 34.30,formater.parse("12/24/2014"),BillType.EDUCATION));

			queue.enqueue(new Bill("Etti",100.40,formater.parse("01/10/2014"),BillType.CLOTHING));
			Bill bill=new Bill("Sarah",40.20,formater.parse("08/25/2014"),BillType.GROCERIES);
			queue.enqueue(bill);
			System.out.println("add bills to queue based on bill amount");
			System.out.println(queue.toString());
			System.out.println("remove bill with id of 4");
			System.out.println(queue.remove(bill).getData().toString());
			System.out.println("bills left on queue");
			System.out.println(queue.toString());
			System.out.println("Fist on queue is : "+ queue.peek().getData().toString());
			queue.dequeue();
			System.out.println("Take first customer... dequeue.. now queue has \n"+ queue.toString());
		
		} catch (DuplicateDataException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
			
		}
	}

}
