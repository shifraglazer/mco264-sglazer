package finalProject;

public class NotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

	public NotFoundException(){
		super("not found");
	}
	public NotFoundException(String message){
		super(message);
	}
}
