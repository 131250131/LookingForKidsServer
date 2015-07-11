package bean;

import java.io.Serializable;

public class KidPhoto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9142331724961845444L;
	private int photoID;
	private int kidID;
	private String photoPath;
	
	public KidPhoto(){
	}
	
	public int getPhotoID() {
		return photoID;
	}
	
	public void setPhotoID(int photoID) {
		this.photoID = photoID;
	}
	
	public int getKidID() {
		return kidID;
	}
	
	public void setKidID(int kidID) {
		this.kidID = kidID;
	}
	
	public String getPhotoPath() {
		return photoPath;
	}
	
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
}
