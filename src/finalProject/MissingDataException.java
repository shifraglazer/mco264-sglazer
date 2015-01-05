package finalProject;

public class MissingDataException extends Exception {
	private static final long serialVersionUID = 1;
public MissingDataException(){
	super("missing data");
}
public MissingDataException(String message){
	super(message);
}
}