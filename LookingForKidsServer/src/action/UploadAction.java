package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import bean.KidPhoto;
import form.KidPhotoUploadForm;
import service.UserManager;

public class UploadAction extends ActionSupport{
	
	private static final long serialVersionUID = -8920466592471253212L;

	private File file;
	private String fileFileName;
	private String fileContentType;
	private KidPhotoUploadForm kidPhotoUploadForm;
	private UserManager userManager;
	
	public KidPhotoUploadForm getKidPhotoUploadForm() {
		return kidPhotoUploadForm;
	}
	public void setKidPhotoUploadForm(KidPhotoUploadForm kidPhotoUploadForm) {
		this.kidPhotoUploadForm = kidPhotoUploadForm;
	}
	public UserManager getUserManager() {
		return userManager;
	}
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String execute(){
		try {
			String savePath = ServletActionContext.getServletContext().getRealPath("/photo");
			File folder = new File(savePath);
			if(!folder.exists() && !folder.isDirectory())
				folder.mkdir();
			FileInputStream fis = new FileInputStream(file);
		            
		    //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)
		    File destFile = new File(savePath, getFileFileName());
		            
		    //把图片写入到上面设置的路径里
		    FileOutputStream fos = new FileOutputStream(destFile);
		    byte[] buffer = new byte[1024];
		    int length  = 0 ;
		    while((length = fis.read(buffer))>0){
		         fos.write(buffer, 0, length);
		    }
		    fis.close();
		    fos.close();	
		    
			KidPhoto kidPhoto = new KidPhoto();
			kidPhoto.setKidID(kidPhotoUploadForm.getKidID());
			kidPhoto.setPhotoPath(savePath+"/"+ getFileFileName());
			kidPhotoUploadForm.setKidPhoto(kidPhoto);
			userManager.upload(kidPhotoUploadForm);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}
