package bean;

import java.io.Serializable;

public class SuspectedKidPhoto implements Serializable{

	private static final long serialVersionUID = -6203119460208865646L;
	private int photoID;
	private int kidID;
	private String photoPath;
	
	public SuspectedKidPhoto(){}
	
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
