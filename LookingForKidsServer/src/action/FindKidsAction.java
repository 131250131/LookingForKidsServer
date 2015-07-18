package action;

import java.util.LinkedList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bean.Kid;
import bean.KidPhoto;
import service.UserManager;

public class FindKidsAction extends ActionSupport{

	private static final long serialVersionUID = 969911471773947031L;
	
	private int kidID;
	private List<KidPhoto> photosPath;
	private List<Kid> kids;
	private UserManager userManager;

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
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
			setKids(userManager.getKids(kidID));
			List<Integer> kidsID = new LinkedList<Integer>();
			for(int i=0;i<kids.size();i++){
				kidsID.add(kids.get(i).getKidID());
			}
			setPhotosPath(userManager.getPhotos(kidsID));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
