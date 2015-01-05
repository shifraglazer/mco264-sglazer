package finalProject;

public class DuplicateDataException extends Exception{
	private static final long serialVersionUID = 1;
	public DuplicateDataException (){
		super ("duplicate data");
	}
	public DuplicateDataException (String message){
		super (message);
	}
}
