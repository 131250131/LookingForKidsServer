package facerec;

import java.io.File;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class GroupManager {
	private static GroupManager instance = null;
	private static final String TRAIN_PATH = "./res/train/";
	APIKeySecret apiKeySecretMap = null;
	HttpRequests httpRequests =null;
	int length = 0;
	int count = 0;
	
	private GroupManager(){
		this.apiKeySecretMap = new APIKeySecret();
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
		
	//�ҳ��ϴ��߶���ͯ��һϵ����Ƭ,����Ĳ��������ļ��еľ���/��Ե�ַ
	public void addSomePhoto(String trainPath) throws FaceppParseException, JSONException{
			Map<String, String> map = apiKeySecretMap.get();
			httpRequests = new HttpRequests(map.get("key"), map.get("secret"));
			File files = new File(TRAIN_PATH);

			//create person
			httpRequests.personCreate(
					new PostParameters().setPersonName("person_"+count));
			
			for(File imagePath:files.listFiles()){
				//detect face
				JSONObject result = httpRequests.detectionDetect(
						new PostParameters().setImg(imagePath));
				for (int i = 0; i < result.getJSONArray("face").length(); ++i) {				
					//add face
					httpRequests.personAddFace(new PostParameters().setPersonName("person_" + count).setFaceId(
							result.getJSONArray("face").getJSONObject(i).getString("face_id")));
				}
				
			}
			
			//set person info
			httpRequests.personSetInfo(new PostParameters().setPersonName(
					"person_" + count).setTag(files.getName().split("\\.")[0]));
			
			//add person
			httpRequests.groupAddPerson(
					new PostParameters().setGroupName("group").setPersonName("person_" + count));
			
			this.count++;
	}
	
	//���һض�ͯ�����ݴ�ѵ��������ɾ��
	public void deleteSomePhot0(){
		
	}
	
	
}
