package action;

import java.util.LinkedList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bean.SuspectedKid;
import bean.SuspectedKidPhoto;
import service.UserManager;

public class FindSuspectedKidsAction extends ActionSupport{

	private static final long serialVersionUID = 969911471773947031L;
	
	private int kidID;
	private List<SuspectedKidPhoto> photosPath;
	private List<SuspectedKid> kids;
	private UserManager userManager;

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public int getKidID() {
		return kidID;
	}

	public void setKidID(int kidID) {
		this.kidID = kidID;
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

	public String execute(){
		try {
			setKids(userManager.getSuspectedKids(kidID));
			List<Integer> kidsID = new LinkedList<Integer>();
			for(int i=0;i<kids.size();i++){
				kidsID.add(kids.get(i).getKidID());
			}
			setPhotosPath(userManager.getSuspectedPhotos(kidsID));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
