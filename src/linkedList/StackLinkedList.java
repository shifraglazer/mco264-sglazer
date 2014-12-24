package linkedList;

import java.io.Serializable;

public class StackLinkedList<T extends Serializable & Comparable<T>> implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<T> list;

	public StackLinkedList() {
		list = new LinkedList<T>();

	}
	public boolean isEmpty(){
		return list.isEmpty();
	}
	public void push(T value) throws Exception {
		list.insert(value);
	}

	public void pop() throws Exception {
		list.remove(list.getHead().getData());
	}

	public T top() {
		return list.getHead().getData();
	}
	public static void main(String[] args) {
		StackLinkedList<String> list = new StackLinkedList<String>();

		try {
			list.push("New York");
			list.push("New Jersey");
			list.push("Vermont");
			list.push("Maine");
			list.push("New Hampshire");
			
			
			System.out.println(list.top());
			list.pop();
			System.out.println(list.top());
			list.pop();
			System.out.println(list.top());
			list.pop();
			System.out.println(list.top());
			list.pop();
			System.out.println(list.top());
			list.pop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
