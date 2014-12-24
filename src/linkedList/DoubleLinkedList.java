package linkedList;

import java.io.Serializable;

public class DoubleLinkedList<T extends Serializable & Comparable<T>>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected DoubleLinkNode<T> tail;
	protected DoubleLinkNode<T> head;
	private int count;

	public DoubleLinkedList() {
		head = null;
		tail = head;
		count = 0;
	}

	public void insert(T value) {

		DoubleLinkNode<T> add = new DoubleLinkNode<T>(value);
		DoubleLinkNode<T> thisNode = head;
		if (thisNode == null) {
			head = add;
			tail = add;
			count++;
			return;
		}
		// add belongs in first place
		else if (add.getData().compareTo(thisNode.getData()) <= 0) {
			head.setPrev(add);
			add.setNext(head);
			head = add;
			head.setPrev(null);
			count++;
			return;
		}
		// belongs after first place
		else {
			if (thisNode.getNext() != null) {
				thisNode = thisNode.getNext();
				while (thisNode != null) {
					if (add.getData().compareTo(thisNode.getData()) <= 0) {
						add.setPrev(thisNode.getPrev());
						thisNode.getPrev().setNext(add);
						add.setNext(thisNode);
						thisNode.setPrev(add);
						count++;
						return;
					}
					thisNode = thisNode.getNext();
				}
			}

		}
		// node is null, reached end of list and data is greater: place
		// data at end
		tail.setNext(add);
		add.setPrev(tail);
		add.setNext(null);
		tail = add;
		tail.setPrev(add.getPrev());
		tail.setNext(null);
		count++;
		return;
	}

	public void remove(T value) throws ListEmptyException, NotFoundException {
		DoubleLinkNode<T> aNode = new DoubleLinkNode<T>(value);
		if (head == null) {
			throw new ListEmptyException();
		}
		DoubleLinkNode<T> thisNode = head;
		// if only has one node and is equal to it
		if (count == 1 && head.getData().compareTo(aNode.getData()) == 0) {
			head = null;
			tail = head;
			count--;
		} else {
			// while there is a next node
			while (thisNode.getNext() != null) {
				// if it is the first node
				if (aNode.getData().compareTo(thisNode.getData()) == 0) {
					if (thisNode == head) {
						head.getNext().setPrev(null);
						head = head.getNext();

					} else {
						thisNode.getPrev().setNext(thisNode.getNext());
						thisNode.getNext().setPrev(thisNode.getPrev());
					}
					count--;
					return;
				}
				// didn't find it yet..
				thisNode = thisNode.getNext();
			}
			// if there is no next node, maybe it is equal to the last node
			if (thisNode.getData().compareTo(aNode.getData()) == 0) {
				tail.getPrev().setNext(null);
				tail = tail.getPrev();
				count--;
				return;
			} else {
				throw new NotFoundException();
			}
		}
	}

	public void removeAll() {
		this.head = null;
	}

	public DoubleLinkNode<T> getFirst() {
		return head;
	}

	public boolean isEmpty() {
		return (head == null);
	}

	public int size() {
		return count;
	}

	public DoubleLinkNode<T> findNode(T value) throws NotFoundException {
		DoubleLinkNode<T> node = head;
		while (node != null) {
			if (node.getData().compareTo(value) == 0) {
				return node;
			}
			node = node.getNext();
		}
		throw new NotFoundException();
	}

	public T peek() throws ListEmptyException {
		if (head == null) {
			throw new ListEmptyException();
		}
		return head.getData();
	}

	public DoubleLinkNode<T> getLast() {
		return tail;
	}

	public ReverseLinkedListIterator<T> iterator() {
		return new ReverseLinkedListIterator<T>(head);
	}

	public String toString() {
		DoubleLinkNode<T> node = head.getNext();
		StringBuilder builder = new StringBuilder();
		if (head == null) {
			return "";
		} else {
			builder.append(head.getData());
		}
		while (node != null) {
			builder.append(" ");
			builder.append(node.getData());
			node = node.getNext();
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		ReverseLinkedListIterator<Integer> reverseIter = list.iterator();
		try {
			System.out.println(list.peek());
		} catch (ListEmptyException e) {
			System.out.println(e.getMessage());
		}
		list.insert(5);
		list.insert(10);
		list.insert(25);
		list.insert(20);
		list.insert(15);
		reverseIter = list.iterator();
		System.out.println(list);
		while (reverseIter.hasNext()) {
			System.out.println(reverseIter.next());
		}
		try {
			list.remove(20);
			reverseIter.reset();
			while (reverseIter.hasNext()) {
				System.out.println(reverseIter.next());
			}
			list.insert(40);
			list.remove(5);
			list.insert(2);
			list.insert(1);
			list.insert(43);
			list.remove(43);
			list.remove(2);
			list.remove(25);
			// list.remove(3);
			reverseIter = list.iterator();
			while (reverseIter.hasNext()) {
				System.out.println(reverseIter.next());
			}
		} catch (ListEmptyException e) {
			System.out.println(e.getMessage());
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		}

	}
}
