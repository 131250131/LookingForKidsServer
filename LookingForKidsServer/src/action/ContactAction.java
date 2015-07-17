package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.SuspectedKidPhoto;
import form.SuspectedKidForm;
import service.UserManager;

public class ContactAction extends ActionSupport{

	private static final long serialVersionUID = 3890638474294601343L;
	
	private List<File> file;
	private List<String> fileFileName;
	private List<String> fileContentType;
	private SuspectedKidForm suspectedKidForm;
	private UserManager userManager;
	
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
	
	public SuspectedKidForm getSuspectedKidForm() {
		return suspectedKidForm;
	}
	
	public void setSuspectedKidForm(SuspectedKidForm suspectedKidForm) {
		this.suspectedKidForm = suspectedKidForm;
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
			suspectedKidForm.setUserID((Integer)session.get("userID"));

			String savePath = ServletActionContext.getServletContext().getRealPath("/suspectedPhoto");
			File folder = new File(savePath);
			if(!folder.exists() && !folder.isDirectory())
				folder.mkdir();
			for(int i=0;i<file.size();i++){
				FileInputStream fis = new FileInputStream(file.get(i));
	            
			    //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)
			    File destFile = new File(savePath, suspectedKidForm.getUserID() + "-" +getFileFileName().get(i));
			            
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
		    
			Set<SuspectedKidPhoto> photos = new HashSet<SuspectedKidPhoto>();
			for(int i=0;i<file.size();i++){
				SuspectedKidPhoto photo = new SuspectedKidPhoto();
				photo.setPhotoPath("/suspectedPhoto"+"/"+ suspectedKidForm.getUserID() + "-" +getFileFileName().get(i));
				photos.add(photo);
			}
			
			suspectedKidForm.setPhotos(photos);
			userManager.contact(suspectedKidForm);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}
