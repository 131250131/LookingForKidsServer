package form;

import java.util.HashSet;
import java.util.Set;

import bean.KidPhoto;

public class KidPublishForm {
	private int userID;
	private String kidName;
	private String gender;
	private String birthday;
	private int height;
	private String lostTime;
	private String lostPlace;
	private String homeTown;
	private String description;
	private Set<KidPhoto> photos = new HashSet<KidPhoto>();
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getKidName() {
		return kidName;
	}

	public void setKidName(String kidName) {
		this.kidName = kidName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getLostTime() {
		return lostTime;
	}

	public void setLostTime(String lostTime) {
		this.lostTime = lostTime;
	}

	public String getLostPlace() {
		return lostPlace;
	}

	public void setLostPlace(String lostPlace) {
		this.lostPlace = lostPlace;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<KidPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<KidPhoto> photos) {
		this.photos = photos;
	}
	


}
