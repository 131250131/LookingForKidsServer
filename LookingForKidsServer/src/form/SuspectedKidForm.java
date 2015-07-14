package form;

import java.util.HashSet;
import java.util.Set;

import bean.SuspectedKidPhoto;

public class SuspectedKidForm {
	private int userID;
	private String time;
	private String place;
	private String description;
	private Set<SuspectedKidPhoto> photos = new HashSet<SuspectedKidPhoto>();
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<SuspectedKidPhoto> getPhotos() {
		return photos;
	}
	
	public void setPhotos(Set<SuspectedKidPhoto> photos) {
		this.photos = photos;
	}
	
}
