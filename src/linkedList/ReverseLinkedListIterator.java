package linkedList;

import java.io.Serializable;

public class ReverseLinkedListIterator<T extends Serializable & Comparable<T>> {
	private DoubleLinkNode<T> head;
	private DoubleLinkNode<T> ip;
	private DoubleLinkNode<T> tail;

	public ReverseLinkedListIterator(DoubleLinkNode<T> head) {
		this.head = head;
		this.ip = head;
		if (head != null) {
			while (ip.getNext() != null) {
				this.ip = ip.getNext();
			}
		}
		this.tail = ip;
	}

	public void reset() {
		ip = tail;
	}

	public boolean hasNext() {
		return ip != null;
	}

	public T next() {
		DoubleLinkNode<T> temp = new DoubleLinkNode<T>(ip.getData());
		ip = ip.getPrev();
		return temp.getData();
	}

	public void set(T value) {
		ip.setData(value);

	}

	public String toString() {
		String value = "";
		if (ip != null) {
			value = ip.getData().toString();
		}
		return value;
	}
}
