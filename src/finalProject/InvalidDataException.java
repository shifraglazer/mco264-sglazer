package finalProject;

public class InvalidDataException extends Exception{
	private static final long serialVersionUID = 1;
public InvalidDataException(){
	super("Invalid data.");
}
public InvalidDataException(String message){
	super(message);
}
}
