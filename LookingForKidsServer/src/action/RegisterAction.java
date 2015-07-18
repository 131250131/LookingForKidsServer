package action;
import com.opensymphony.xwork2.ActionSupport;

import form.UserRegisterForm;
import service.user.UserManager;

public class RegisterAction extends ActionSupport {

	private static final long serialVersionUID = -5451733703488146831L;
	
	private UserRegisterForm userRegisterForm;
	private UserManager userManager;
	private int resultMessage;

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
			if(userManager.getUserByEmail(userRegisterForm.getEmail()).size() != 0)
				setResultMessage(1);
			else {
				userManager.register(userRegisterForm);
				setResultMessage(0);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			setResultMessage(2);
		}
		return SUCCESS;
	}

	public int getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(int resultMessage) {
		this.resultMessage = resultMessage;
	}
}
