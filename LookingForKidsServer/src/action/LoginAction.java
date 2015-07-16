package action;

import com.opensymphony.xwork2.ActionSupport;
import bean.User;
import form.UserLoginForm;
import service.UserManager;

public class LoginAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7330737697739867196L;
	private UserLoginForm userLoginForm;
	private UserManager userManager;
	private int userID;
	private int loginStatus;
	
	
	
	public UserLoginForm getUserLoginForm() {
		return userLoginForm;
	}
	
	public void setUserLoginForm(UserLoginForm userLoginForm) {
		this.userLoginForm = userLoginForm;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}
	
	public String execute() {
		try {
			if(userManager.getUserByEmail(userLoginForm.getAccount()).size()==0 && 
					userManager.getUserByPhoneNumber(userLoginForm.getAccount()).size()==0) {
				setLoginStatus(1);
				return SUCCESS;
			}
			else {
				for(User user: userManager.getUserByEmail(userLoginForm.getAccount()))
					if(user.getPassword().equals(userLoginForm.getPassword())) {
						setUserID(user.getUserID());
						setLoginStatus(0);
						return SUCCESS;
					}
				
				for(User user: userManager.getUserByPhoneNumber(userLoginForm.getAccount()))
					if(user.getPassword().equals(userLoginForm.getPassword())) {
						setUserID(user.getUserID());
						setLoginStatus(0);
						return SUCCESS;
					}
				
				setLoginStatus(2);
				return SUCCESS;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

}
