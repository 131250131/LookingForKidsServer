package action;



import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.User;
import form.UserLoginForm;
import service.UserManager;

public class LoginAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7330737697739867196L;
	public static UserLoginForm userLoginForm;
	private UserManager userManager;
	
	public UserLoginForm getUserLoginForm() {
		return userLoginForm;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public String execute() {
		ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> session = actionContext.getSession();
        
		try {
			if(userManager.getUserByEmail(userLoginForm.getAccount()).size()==0 && 
					userManager.getUserByPhoneNumber(userLoginForm.getAccount()).size()==0) 
				return ERROR;
			else {
				for(User user: userManager.getUserByEmail(userLoginForm.getAccount()))
					if(user.getPassword().equals(userLoginForm.getPassword())) {
						session.put("userID", user.getUserID());
						return SUCCESS;
					}
				
				for(User user: userManager.getUserByPhoneNumber(userLoginForm.getAccount()))
					if(user.getPassword().equals(userLoginForm.getPassword())) {
						session.put("userID", user.getUserID());
						return SUCCESS;
					}
				return ERROR;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

}
