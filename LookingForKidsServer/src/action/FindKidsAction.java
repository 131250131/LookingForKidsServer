package action;

import java.util.LinkedList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bean.Kid;
import bean.KidPhoto;
import service.lostkid.LostKidManager;

public class FindKidsAction extends ActionSupport{

	private static final long serialVersionUID = 969911471773947031L;
	
	private int kidID;
	private List<KidPhoto> photosPath;
	private List<Kid> kids;
	private LostKidManager lostKidManager;

	public LostKidManager getLostKidManager() {
		return lostKidManager;
	}

	public void setLostKidManager(LostKidManager lostKidManager) {
		this.lostKidManager = lostKidManager;
	}

	public List<Kid> getKids() {
		return kids;
	}

	public void setKids(List<Kid> kids) {
		this.kids = kids;
	}

	public List<KidPhoto> getPhotosPath() {
		return photosPath;
	}

	public void setPhotosPath(List<KidPhoto> photosPath) {
		this.photosPath = photosPath;
	}

	public int getKidID() {
		return kidID;
	}

	public void setKidID(int kidID) {
		this.kidID = kidID;
	}

	public String execute(){
		try {
			setKids(lostKidManager.getKids(kidID));
			List<Integer> kidsID = new LinkedList<Integer>();
			for(int i=0;i<kids.size();i++){
				kidsID.add(kids.get(i).getKidID());
			}
			setPhotosPath(lostKidManager.getPhotos(kidsID));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
