package action;

import java.util.LinkedList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bean.SimilarityRecord;
import bean.SuspectedKid;
import bean.SuspectedKidPhoto;
import service.suspectedlostkid.SuspectedLostKidManager;

public class FindSimilarKidsAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3246865578284302112L;
	private int userID;
	private List<SuspectedKidPhoto> photosPath;
	private List<SuspectedKid> kids;
	private SuspectedLostKidManager suspectedLostKidManager;

	public SuspectedLostKidManager getSuspectedLostKidManager() {
		return suspectedLostKidManager;
	}

	public void setSuspectedLostKidManager(SuspectedLostKidManager suspectedLostKidManager) {
		this.suspectedLostKidManager = suspectedLostKidManager;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public List<SuspectedKidPhoto> getPhotosPath() {
		return photosPath;
	}

	public void setPhotosPath(List<SuspectedKidPhoto> photosPath) {
		this.photosPath = photosPath;
	}

	public List<SuspectedKid> getKids() {
		return kids;
	}

	public void setKids(List<SuspectedKid> kids) {
		this.kids = kids;
	}
	
	
	public String execute() {
		List<SimilarityRecord> kidsRecords = suspectedLostKidManager.getSimilarityRecords(userID);
		
		List<Integer> kidsList = new LinkedList<Integer>();
		for(SimilarityRecord loopRecord: kidsRecords)
			kidsList.add(loopRecord.getSimilarity());
		setKids(suspectedLostKidManager.getSuspectedKidsByID(kidsList));
		
		List<Integer> kidsID = new LinkedList<Integer>();
		for(int i=0;i<kids.size();i++){
			kidsID.add(kids.get(i).getKidID());
		}
		setPhotosPath(suspectedLostKidManager.getSuspectedPhotos(kidsID));
		return SUCCESS;
		
	}

}
