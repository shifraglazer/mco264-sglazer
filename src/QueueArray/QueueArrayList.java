package QueueArray;

import java.util.ArrayList;

public class QueueArrayList <T>{
	ArrayList<T> list;
	
	public QueueArrayList(){
		list=new ArrayList<T>();
	}
	public void enqueue(T value){
		list.add(value);
		
	}
	public void dequeue()throws QueueEmptyException{
		if(!isEmpty()){
		list.remove(list.get(0));
		}
		throw new QueueEmptyException();
	}
	public boolean isEmpty(){
		return list.isEmpty();
	}
	public T peek()throws QueueEmptyException{
		if(!isEmpty()){
			return list.get(0);
		}
		throw new QueueEmptyException();
	}
	public static void main(String[] args) {
		QueueArrayList<String> myList;
		myList = new QueueArrayList<String>();
		
		myList.enqueue("Rachel");
		myList.enqueue("Gabi");
		myList.enqueue("Tzippy");
		myList.enqueue("Rivki");
		myList.enqueue("Avigayl");
		System.out.println(myList.peek());
		myList.dequeue();
		System.out.println(myList.peek());
		myList.dequeue();
	}
	
}
