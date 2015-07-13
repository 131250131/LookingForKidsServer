package form;

import bean.KidPhoto;

public class KidPhotoUploadForm {
	private int kidID;
	private KidPhoto kidPhoto;

	public int getKidID() {
		return kidID;
	}
	public void setKidID(int kidID) {
		this.kidID = kidID;
	}
	public KidPhoto getKidPhoto() {
		return kidPhoto;
	}
	public void setKidPhoto(KidPhoto kidPhoto) {
		this.kidPhoto = kidPhoto;
	}
}
