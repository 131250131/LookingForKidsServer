package action;

import com.opensymphony.xwork2.ActionSupport;

import bean.User;
import service.UserManager;

public class GetInfoAction extends ActionSupport{
	
	private static final long serialVersionUID = 5970231857679159310L;
	private int userID;
	private UserManager userManager;
	private User user;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute(){
		try {
			setUser(userManager.getInfo(userID));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
