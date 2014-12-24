package inventory;

import java.io.FileNotFoundException;
import java.io.Serializable;

public class ItemIndex implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String upc;  //primary key of an Item
	private long fileLocation;  //file location of a specific Item 
	private boolean active;  //indicator that specifies whether referenced Item is still valid

	public ItemIndex(String upc,long fileLocation)throws FileNotFoundException{
		this.upc = upc;
		this.fileLocation = fileLocation;
		this.active = true;
		
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUpc() {
		return upc;
	}

	public long getFileLocation() {
		return fileLocation;
	}
	
	
	
	
	
}
