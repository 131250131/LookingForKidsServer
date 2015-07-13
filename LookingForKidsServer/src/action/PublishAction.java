package action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import form.KidPublishForm;
import service.UserManager;

public class PublishAction extends ActionSupport{

	private static final long serialVersionUID = -5451733703488146831L;
	
	private KidPublishForm kidPublishForm;
	private UserManager	userManager;
	
	public KidPublishForm getKidPublishForm() {
		return kidPublishForm;
	}
	
	public void setKidPublishForm(KidPublishForm kidPublishForm) {
		this.kidPublishForm = kidPublishForm;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public String execute(){
		ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> session = actionContext.getSession();
		try {
			kidPublishForm.setUserID((Integer)session.get("userID"));
			userManager.publish(kidPublishForm);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}
