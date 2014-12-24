package student;

public class MissingDataException extends Exception {
	private static final long serialVersionUID = 1;
public MissingDataException(){
	super("missing data");
}
}
