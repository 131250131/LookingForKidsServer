package bean;

import java.io.Serializable;

public class SimilarityRecord implements Serializable{
	
	private static final long serialVersionUID = 7930150516977445330L;
	private int userID;
	private int suspectedkidID;
	private int similarity;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getSuspectedkidID() {
		return suspectedkidID;
	}
	public void setSuspectedkidID(int suspectedkidID) {
		this.suspectedkidID = suspectedkidID;
	}
	public int getSimilarity() {
		return similarity;
	}
	public void setSimilarity(int similarity) {
		this.similarity = similarity;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
