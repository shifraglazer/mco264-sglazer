package inventory;

public class DataException extends Exception {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public DataException(String message){
	super(message);
}
public DataException(){
	super("data exception");
}
}
