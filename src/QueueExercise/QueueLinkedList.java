package QueueExercise;

import java.io.Serializable;

public class QueueLinkedList<T extends Serializable & Comparable<T> >implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<T> list;
	public QueueLinkedList(){
		list=new LinkedList<T>();
	}
	public void enqueue(T value){
		list.insertLast(value);
	}
	public void dequeue() throws ListEmptyException{
		list.removeFirst();
	}
	public T peek() throws ListEmptyException{
		return list.peek();
	}
}
