package action;
import com.opensymphony.xwork2.ActionSupport;

import form.UserRegisterForm;
import service.user.UserManager;

public class RegisterAction extends ActionSupport {

	private static final long serialVersionUID = -5451733703488146831L;
	
	private UserRegisterForm userRegisterForm;
	private UserManager userManager;

	public UserRegisterForm getUserRegisterForm() {
		return userRegisterForm;
	}

	public void setUserRegisterForm(UserRegisterForm userRegisterForm) {
		this.userRegisterForm = userRegisterForm;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public String execute() {
		try {
			userManager.register(userRegisterForm);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}
