package finalProject;

import java.io.Serializable;
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
	public T dequeue() throws NotFoundException {
		T head=list.getHead();
		list.remove(head);
		return head;
	}
	public LinkedListIterator<T> iterator(){
		return list.iterator();
	}
	public T peek() throws NotFoundException {
		return list.getHead();
		
	}
	public T remove(T data) throws NotFoundException{
		T remove=list.find(data);
		list.remove(data);
		return remove;
	}
	public String toString(){
		return list.toString();
	}
	

}
