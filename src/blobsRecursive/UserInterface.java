package blobsRecursive;

public class UserInterface {

	public static void main(String[] args) {
		
		Character character = new Character('X');
		
		//can't figure out how define a grid of cell objects
		//each cell can store an object (among other things)
		GridStack<Character> theGrid = new GridStack<Character>(10,10);
		theGrid.setGrid(30, 'X');
		System.out.println(theGrid.toString());
		System.out.println(theGrid.countBlobs(character));
	
	}
}