package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import bean.KidPhoto;
import form.KidPublishForm;
import service.UserManager;

public class PublishAction extends ActionSupport{

	private static final long serialVersionUID = -5451733703488146831L;
	
	private List<File> file;
	private List<String> fileFileName;
	private List<String> fileContentType;
	private KidPublishForm kidPublishForm;
	private UserManager	userManager;
	private int resultMessage;
	
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
		try {
			int kidID = userManager.getKidID()+1;
			String savePath = ServletActionContext.getServletContext().getRealPath("/photo");
			File folder = new File(savePath);
			if(!folder.exists() && !folder.isDirectory())
				folder.mkdir();
			savePath = ServletActionContext.getServletContext().getRealPath("/photo/"+ kidID);
			folder = new File(savePath);
			if(!folder.exists() && !folder.isDirectory())
				folder.mkdir();
			for(int i=0;i<file.size();i++){
				FileInputStream fis = new FileInputStream(file.get(i));
	            
			    //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)
			    File destFile = new File(savePath, kidPublishForm.getUserID() + "-" +getFileFileName().get(i));
			            
			    //把图片写入到上面设置的路径里
			    FileOutputStream fos = new FileOutputStream(destFile);
			    byte[] buffer = new byte[1024];
			    int length  = 0 ;
			    while((length = fis.read(buffer))>0){
			         fos.write(buffer, 0, length);
			    }
			    fis.close();
			    fos.close();	
			}
		    
			Set<KidPhoto> photos = new HashSet<KidPhoto>();
			for(int i=0;i<file.size();i++){
				KidPhoto photo = new KidPhoto();
				photo.setPhotoPath("/photo/"+ kidID + "/" + kidPublishForm.getUserID() + "-" +getFileFileName().get(i));
				photos.add(photo);
			}
			kidPublishForm.setPhotos(photos);
			userManager.publish(kidPublishForm);
			setResultMessage(0);
		} catch (Exception e) {
			e.printStackTrace();
			setResultMessage(1);
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
