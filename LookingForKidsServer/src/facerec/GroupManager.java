package facerec;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class GroupManager {
	private static GroupManager instance = null;
	private static final String TRAIN_PATH = "D:/ee_workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/LookingForKidsServer/photo";
	private static final String KEY_SECRET_PATH = "D:/doc/LookingForKidsServer/LookingForKidsServer/key.obj";
	APIKeySecret apiKeySecretMap = null;
	HttpRequests httpRequests =null;
	int length = 0;
	int count = 0;
	
	private GroupManager(){
		this.apiKeySecretMap = new APIKeySecret();
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(KEY_SECRET_PATH)));
			apiKeySecretMap =  (APIKeySecret)in.readObject();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.length = this.apiKeySecretMap.getLength();
		
	}
	public static GroupManager getInstance(){
		if(instance == null){
			instance = new GroupManager();
		}
		return instance;
	}
	
	//
	public void groupInit() throws FaceppParseException, JSONException{
		this.count = 0;
		File files = new File(TRAIN_PATH);
		
		for (File imageFile : files.listFiles()) {
			
			Map<String, String> map = apiKeySecretMap.get();
			httpRequests = new HttpRequests(map.get("key"), map.get("secret"));
			//create person
			httpRequests.personCreate(
					new PostParameters().setPersonName(imageFile.getName()));
			
			for(File imagePath:imageFile.listFiles()){
				//detect face
				JSONObject result = httpRequests.detectionDetect(
						new PostParameters().setImg(imagePath));
				for (int i = 0; i < result.getJSONArray("face").length(); ++i) {				
					//add face
					httpRequests.personAddFace(new PostParameters().setPersonName(imageFile.getName()).setFaceId(
							result.getJSONArray("face").getJSONObject(i).getString("face_id")));
				}
			}
			
			//add person
			httpRequests.groupAddPerson(
					new PostParameters().setGroupName("group").setPersonName(imageFile.getName()));
			
			this.count++;
			
		}
		
	}
		
	//家长上传走丢儿童的一系列照片,传入的参数是子文件夹的绝对/相对地址
	public void addSomePhoto(String trainPath) throws FaceppParseException, JSONException{
			Map<String, String> map = apiKeySecretMap.get();
			httpRequests = new HttpRequests(map.get("key"), map.get("secret"));
			File files = new File(trainPath);

			//create person
			httpRequests.personCreate(
					new PostParameters().setPersonName(files.getName()));
			
			for(File imagePath:files.listFiles()){
				//detect face
				JSONObject result = httpRequests.detectionDetect(
						new PostParameters().setImg(imagePath));
				for (int i = 0; i < result.getJSONArray("face").length(); ++i) {				
					//add face
					httpRequests.personAddFace(new PostParameters().setPersonName(files.getName()).setFaceId(
							result.getJSONArray("face").getJSONObject(i).getString("face_id")));
				}
				
			}
			
			//add person
			httpRequests.groupAddPerson(
					new PostParameters().setGroupName("group").setPersonName(files.getName()));
			
			this.count++;
	}

	//已找回儿童的数据从训练队列中删除
	public void deleteSomePhot0(){
		
	}
	
	public static void main(String[] args){
		GroupManager manager = GroupManager.getInstance();
		String trainPath =  "D:/ee_workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/LookingForKidsServer/photo/3";
		try {
			manager.addSomePhoto(trainPath);
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
