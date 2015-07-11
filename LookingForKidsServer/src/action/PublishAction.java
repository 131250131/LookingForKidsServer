package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
			//�������̱����ļ���·��
			String root = ServletActionContext.getServletContext().getRealPath("/photos");
			File folder = new File(root);
			if(!folder.exists() && !folder.isDirectory())
				folder.mkdir();
			for(int i=0;i<file.size();i++){
				InputStream is = new FileInputStream(file.get(i));
		            
		        //�õ�ͼƬ�����λ��(����root���õ�ͼƬ�����·����tomcat�µĸù�����)
		        File destFile = new File(root,this.getFileFileName().get(i));
		            
		        //��ͼƬд�뵽�������õ�·����
		        OutputStream os = new FileOutputStream(destFile);
		        byte[] buffer = new byte[400];
		        int length  = 0 ;
		        while((length = is.read(buffer))>0){
		            os.write(buffer, 0, length);
		        }
		        is.close();
		        os.close();	
			}
			Set<KidPhoto> kidPhotos = new HashSet<KidPhoto>();
			for(int i=0;i<file.size();i++){
				KidPhoto kidPhoto = new KidPhoto();
				kidPhoto.setKidID(1);
				kidPhoto.setPhotoID(1);
				kidPhoto.setPhotoPath(root+"\\"+this.getFileFileName().get(i));
				kidPhotos.add(kidPhoto);
			}
			kidPublishForm.setKidPhotos(kidPhotos);
			userManager.publish(kidPublishForm);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}
