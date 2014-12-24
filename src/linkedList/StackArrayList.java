package linkedList;
import java.util.ArrayList;

public class StackArrayList<T> {

	private ArrayList<T> list;
	public StackArrayList(){
		list = new ArrayList<T>();
	}
	public void push(T value){
		list.add(value);
	}
	public void pop(){
		list.remove(list.size()-1);
	}
	public T top(){
		return list.get(list.size()-1);
	}
	public boolean isEmpty(){
		return list.isEmpty();
	}

	public static void main(String[] args) {
		StackArrayList<String> list = new StackArrayList<String>();

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
	}

}