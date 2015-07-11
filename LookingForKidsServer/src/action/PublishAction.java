package action;

import java.io.File;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import form.KidPublishForm;
import service.UserManager;

public class PublishAction extends ActionSupport{

	private static final long serialVersionUID = -5451733703488146831L;
	
	private List<File> file;
	private List<String> fileFileName;
	private List<String> fileContentType;
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

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String execute(){
		try {
//			String root = ServeletActionContext.
			userManager.publish(kidPublishForm);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}
