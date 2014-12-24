package blobsRecursive;

import java.util.Random;
import java.util.Stack;
import java.util.Vector;

public class GridStack<E> {
	// a Vector of Vectors of Cells
	private Vector<Vector<Cell<E>>> cells;
	private Stack<Cell<E>> stack;

	public GridStack(int rows, int columns) {
		cells = new Vector<Vector<Cell<E>>>();
		stack = new Stack<Cell<E>>();
		// initialize each cell of the grid
		for (int row = 0; row < rows; row++) {
			// set up a Vector that will represent a new row in a two
			// dimensional grid
			Vector<Cell<E>> tempVector = new Vector<Cell<E>>();

			for (int col = 0; col < columns; col++) {
				tempVector.add(new Cell<E>(row, col)); // add a Cell to this
														// Vector
			}
			// add this new vector to the Vector of Vectors
			cells.add(tempVector);
		}

	}

	public void setGrid(int percentage, E value) {
		Random randomGenerator = new Random(System.currentTimeMillis());
		int randomNum;

		for (int row = 0; row < cells.size(); row++) {
			for (int col = 0; col < cells.get(row).size(); col++) {
				randomNum = randomGenerator.nextInt(100);
				if (randomNum < percentage) {
					// first get() , gets access to the Vector on a particular
					// row
					// second get() , gets access to a particular Cell in the
					// Vector on a particular row
					cells.get(row).get(col).setData(value);
				}
			}
		}
	}

	public int countBlobs(E value) {

		int count = 0;

		for (int row = 0; row < cells.size(); row++) {
			for (int col = 0; col < cells.get(row).size(); col++) {
				Cell<E> startCell = cells.get(row).get(col);
				System.out.println("starting at cell [" + row + "][" + col
						+ "]");
				if (!startCell.isVisited() && startCell.hasData()) {
					count++;
					// mark the blob connected to starting cell
					markBlob(startCell);
				}
			}
		}
		return count;
	}

	public void markBlob(Cell<E> currentCell) {
		// mark the current cell as visited
		System.out.println("current cell " + currentCell.getRow() + " "
				+ currentCell.getCol());
		// went this route already ,no need to explore it again
		if (currentCell.isVisited()) {
			return;
		}
		currentCell.setVisited();
		// base case / anchor case
		if (!currentCell.hasData()) {
			return; // end of connecting blob - hit empty space
		}
		// traverse up
		if (currentCell.getRow() > 0) { // end of grid
			// move up one row and mark blob starting there
			System.out.println("moved up from cell " + currentCell + " "
					+ currentCell.getRow() + " " + currentCell.getCol());
			markBlob(cells.get(currentCell.getRow() - 1).get(
					currentCell.getCol()));
		}

		// traverse down
		if (currentCell.getRow() < cells.size() - 1) { // move down one row
			System.out.println("moved down from cell " + currentCell + " "
					+ currentCell.getRow() + " " + currentCell.getCol());
			markBlob(cells.get(currentCell.getRow() + 1).get(
					currentCell.getCol()));
		}
		// traverse left
		if (currentCell.getCol() > 0) { // end of grid
			// move over left one column
			System.out.println("moved left from cell " + currentCell + " "
					+ currentCell.getRow() + " " + currentCell.getCol());
			markBlob(cells.get(currentCell.getRow()).get(
					currentCell.getCol() - 1));
		}

		// traverse right

		if (currentCell.getCol() < cells.get(currentCell.getRow()).size() - 1) {

			// move over right one column
			System.out.println("moved right from cell " + currentCell + " "
					+ currentCell.getRow() + " " + currentCell.getCol());
			markBlob(cells.get(currentCell.getRow()).get(
					currentCell.getCol() + 1));
		}

	}

	public int countBlobsStack(E value) {

		int count = 0;

		for (int row = 0; row < cells.size(); row++) {
			for (int col = 0; col < cells.get(row).size(); col++) {
				Cell<E> startCell = cells.get(row).get(col);
				System.out.println("starting at cell [" + row + "][" + col
						+ "]");
				if (!startCell.isVisited() && startCell.hasData()) {
					count++;
					System.out.println("this is blob # " + count);
					// mark the blob connected to starting cell
					startCell.setVisited();
					stack.push(startCell);
					System.out
					.println(stack.peek().getRow()+" x "+ stack.peek().getCol());
					while (!stack.isEmpty()) {
						boolean change;
						do{
							change=false;
						while (getNextRight()) {
							change=true;
							System.out
									.println(stack.peek().getRow()+" x "+ stack.peek().getCol());
						}

						while (getNextUp()) {
							change=true;
							System.out
							.println(stack.peek().getRow()+" x "+ stack.peek().getCol());
						}

						while (getNextDown()) {
							change=true;
							System.out
							.println( stack.peek().getRow() +" x "+ stack.peek().getCol());
						}
						while (getNextLeft()) {
							change=true;
							System.out
							.println(stack.peek().getRow()+" x "+ stack.peek().getCol());
						}
						}
						while(change);
						stack.pop();
					}
				}
			}
		}
		return count;
	}

	public boolean getNextRight() {
		int row = stack.peek().getRow();
		int col = stack.peek().getCol();
		if (col + 1 < cells.get(row).size()) {
			Cell<E> cell = cells.get(row).get(col + 1);
			if (!cell.isVisited() && cell.hasData()) {
				cell.setVisited();
				stack.add(cell);
				return true;
			}
			
		}
		return false;
	}

	public boolean getNextLeft() {
		int row = stack.peek().getRow();
		int col = stack.peek().getCol();

		if (col - 1 >= 0) {
			Cell<E> cell = cells.get(row).get(col - 1);
			if (!cell.isVisited() && cell.hasData()) {
				cell.setVisited();
				stack.add(cell);
				return true;
			}
	
		}
		return false;
	}

	public boolean getNextUp() {
		int row = stack.peek().getRow();
		int col = stack.peek().getCol();
		if (row - 1 >= 0) {
			Cell<E> cell = cells.get(row - 1).get(col);
			if (!cell.isVisited() && cell.hasData()) {
				cell.setVisited();
				stack.add(cell);
				return true;
			}
			
		}
		return false;
	}

	public boolean getNextDown() {
		int row = stack.peek().getRow();
		int col = stack.peek().getCol();
		if (row + 1 < cells.size()) {
			Cell<E> cell = cells.get(row + 1).get(col);
			if (!cell.isVisited() && cell.hasData()) {
				cell.setVisited();
				stack.add(cell);
				return true;
			}
	
		}
		return false;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int row = 0; row < cells.size(); row++) {
			buffer.append("\n");
			for (int col = 0; col < cells.get(row).size(); col++) {

				buffer.append(" " + cells.get(row).get(col).toString());
			}
		}
		return buffer.toString();
	}

	static public void main(String[] args) {
		Character character = new Character('X');
		GridStack<Character> theGrid = new GridStack<Character>(5, 5);
		theGrid.setGrid(40, 'X');
		System.out.println(theGrid.toString());
		// System.out.println(theGrid.countBlobs(character));
		System.out.println(theGrid.countBlobsStack(character));
		System.out.println(theGrid);

	}
}
