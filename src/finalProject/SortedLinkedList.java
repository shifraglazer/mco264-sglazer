package finalProject;

import java.io.Serializable;
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

	public T  getFirst() {
		return head.getData();
	}

	public T find(T data) throws NotFoundException {
		Node<T> current = head;
		while (current != null) {
			if (data.compareTo(current.getData()) == 0) {
				return current.getData();
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
	public LinkedListIterator<T> iterator(){
		return new LinkedListIterator<T>(head);
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Node<T> current = head;
		while (current.getNext() != null) {
			builder.append(current.getData().toString());
			builder.append("\n");
			current = current.getNext();
		}
		builder.append(current.getData().toString());
		return builder.toString();
	}

}
