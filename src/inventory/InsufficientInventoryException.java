package inventory;

public class InsufficientInventoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public InsufficientInventoryException(){
	super("insuficient inventory");
}
}
