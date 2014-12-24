package billOrganizer;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class SortedLinkedList<T extends Serializable & Comparable<T>> extends
		LinkedList<T> {

	private static final long serialVersionUID = 1L;

	public SortedLinkedList() {
	}

	public void insert(T data) throws DuplicateDataException {
		if (contains(data)) {
			throw new DuplicateDataException();
		}
		Node<T> insert = new Node<T>(data);
		if (head == null) {
			head = insert;
			return;
		}

		else {
			Node<T> current = head;

			Node<T> prev = head;

			while (current != null) {
				if (data.compareTo(current.getData()) < 0) {
					if (current.getData().compareTo(head.getData()) == 0) {
						insert.setNext(head);
						head = insert;
					} else {
						insert.setNext(current);
						prev.setNext(insert);
					}
					return;
				}
				prev = current;
				current = current.getNext();
			}
			current = insert;
			prev.setNext(insert);
		}

	}

	public void insert(T value, Comparator<T> comparator)
			throws DuplicateDataException {
		if (contains(value)) {
			throw new DuplicateDataException();
		}
		Node<T> node = new Node<T>(value);
		if (head == null) {
			head = node;
			return;
		}
		Node<T> current = head;
		Node<T> prev = head;
		while (current != null) {
			if (comparator.compare(value, current.getData()) < 0) {
				if (current.getData().compareTo(head.getData()) == 0) {
					node.setNext(head);
					head = node;
				

				} else {
					node.setNext(current);
					prev.setNext(node);
				}
				return;
			}
			prev = current;
			current = current.getNext();
		}
		current = node;
		prev.setNext(node);
	}
	public Node<T> getHead(){
		return head;
	}

	public Node<T> find(T data) throws NotFoundException {
		Node<T> current = head;
		while (current != null) {
			if (data.compareTo(current.getData()) == 0) {
				return current;
			}
			current = current.getNext();
		}
		throw new NotFoundException();
	}

	public boolean contains(T value) {
		Node<T> current = head;
		while (current != null) {
			if (value.compareTo(current.getData()) == 0) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}
	public String toString(){
		StringBuilder builder=new StringBuilder();
		Node<T> current=head;
		while(current.getNext()!=null){
			builder.append(current.getData().toString());
			builder.append("\n");
			current=current.getNext();
		}
		builder.append(current.getData().toString());
		return builder.toString();
	}
	public static void main(String[] args) {
		SortedLinkedList<Integer> list = new SortedLinkedList<Integer>();

		try {
			list.insert(10);
			list.insert(25);
			list.insert(15);
			list.insert(5);
			list.insert(20);
			list.remove(10);
			list.insert(17);
			System.out.println(list);
			System.out.println(list.find(5).getData());
			System.out.println(list.contains(17));
			SortedLinkedList<Bill> bills = new SortedLinkedList<Bill>();
			SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");

			

			Bill bill = new Bill("Shifra", 34.30, formater.parse("12/24/2014"),
					BillType.EDUCATION);

			Bill bill2 = new Bill("Etti", 100.40, formater.parse("01/01/2014"),
					BillType.CLOTHING);

			Bill bill3 = new Bill("Chayala", 10.10, formater.parse("02/25/2014"),
					BillType.CLOTHING);
			BillAmountComparator amount=new BillAmountComparator();
			bills.insert(bill2, amount);
			bills.insert(bill,amount);
			bills.insert(bill3, amount);
			System.out.println(bills);

		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(list);

	}

}
