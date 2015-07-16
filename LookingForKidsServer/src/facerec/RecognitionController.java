package facerec;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class RecognitionController {
	//api key & secret
	APIKeySecret apiKeySecretMap = new APIKeySecret();
	HttpRequests httpRequests;
	int length ;
	private static final String KEY_SECRET_PATH = "./key.obj";
	
	private static  RecognitionController instance = null;
	private RecognitionController(){
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(KEY_SECRET_PATH)));
			apiKeySecretMap =  (APIKeySecret)in.readObject();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		length = apiKeySecretMap.getLength();
	}
	public static RecognitionController getInstacen(){
		if(instance == null){
			instance = new RecognitionController();
		}
		return instance;
	}
		
	public Map<String,Double> recognizeOneImage(File imageFile) throws FaceppParseException, JSONException, InterruptedException{
		
		JSONObject result;
		JSONArray[] personsList = new JSONArray[length];
		
		//
		Map<String, String> personNameToTag = new HashMap<String, String>();
		for (int i = 0; i < length; i++) {
			Map<String, String> map = apiKeySecretMap.get();
			httpRequests = new HttpRequests(map.get("key"), map.get("secret"));
			result = httpRequests.groupGetInfo(new PostParameters().setGroupName("group"));
			JSONArray persons = result.getJSONArray("person");
			personsList[i] = persons;
			for (int j = 0; j < persons.length(); j++) {
				String personName = persons.getJSONObject(j).getString("person_name");
				result = httpRequests.personGetInfo(new PostParameters().setPersonName(personName));
				personNameToTag.put(personName, result.getString("tag"));
			}
		}
		
		System.out.println(imageFile.getName());
		
		Map<String, Double> personNameToSimilarity = new HashMap<String, Double>();
		Thread[] threads = new Thread[length];
		for (int i = 0; i < length; i++) {
			Map<String, String> map = apiKeySecretMap.get();
			httpRequests = new HttpRequests(map.get("key"), map.get("secret"));
			threads[i] = new RecognizeThread(imageFile, httpRequests, 
					personsList[i], personNameToSimilarity);
			threads[i].start();
		}
		for (int i = 0; i < length; i++) {
			threads[i].join();
		}
		
		ArrayList<Map.Entry<String, Double>> mappingList = null;
		mappingList = new ArrayList<Map.Entry<String, Double>>(personNameToSimilarity.entrySet());
		Collections.sort(mappingList, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		Map<String,Double> recResult = new HashMap<String,Double>(); 
		for(Map.Entry<String,Double> mapping : mappingList){
//			System.out.print(personNameToTag.get(mapping.getKey())+":"+mapping.getValue()+"\t");
			recResult.put(personNameToTag.get(mapping.getKey()), mapping.getValue());
		}
		
		System.out.println();
		return recResult;
		
	}
	
	class RecognizeThread extends Thread {
		private File imageFile;
		private HttpRequests httpRequests;
		private JSONArray persons;
		private Map<String, Double> personNameToSimilarity;
		
		public RecognizeThread(File imageFile, HttpRequests httpRequests, 
				JSONArray persons, Map<String, Double> personNameToSimilarity) {
			super();
			this.imageFile = imageFile;
			this.httpRequests = httpRequests;
			this.persons = persons;
			this.personNameToSimilarity = personNameToSimilarity;
		}
		
		public void run() {
			JSONObject result;
			try{
				//detect face test
				result = httpRequests.detectionDetect(new PostParameters().setImg(imageFile));
				
				//create person test
				httpRequests.personCreate(
						new PostParameters().setPersonName("person_test"));
					
				//add face test
				httpRequests.personAddFace(new PostParameters().setPersonName("person_test").setFaceId(
						result.getJSONArray("face").getJSONObject(0).getString("face_id")));
					
				//get test image id
				result = httpRequests.personGetInfo(new PostParameters().setPersonName("person_test"));
				String testFaceId = result.getJSONArray("face").getJSONObject(0).getString("face_id");
					
				//iterate faces in group compare
				for (int j = 0; j < persons.length(); j++) {
					String personName = persons.getJSONObject(j).getString("person_name");
					result = httpRequests.personGetInfo(new PostParameters().setPersonName(personName));
					JSONArray jsonarray = result.getJSONArray("face");
					int num_photo = jsonarray.length();
					JSONObject[] result_photo = new JSONObject[num_photo];
					JSONObject temp = new JSONObject();
					//同一个人有多张照片时，选取匹配得分最高的那个一张
					for(int k = 0; k<num_photo ;k++){
						String faceId= jsonarray.getJSONObject(k).getString("face_id");
						result_photo[k] = httpRequests.recognitionCompare(
								new PostParameters().setFaceId1(testFaceId).setFaceId2(faceId));
						if(k==0){
//							faceId= result.getJSONArray("face").getJSONObject(0).getString("face_id");	
							temp =result_photo[0];
						}
						if((k!=0)&(result_photo[k].getDouble("similarity")>temp.getDouble("similarity"))){
							faceId= jsonarray.getJSONObject(k).getString("face_id");	
							temp =result_photo[k];
						}
					}
					personNameToSimilarity.put(personName, temp.getDouble("similarity"));
				}
					
				//delete person
				result = httpRequests.personDelete(new PostParameters().setPersonName("person_test"));
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args){
		RecognitionController rc = RecognitionController.getInstacen();
		long time = System.currentTimeMillis();
		File imageFile = new File("./res/test/photo(13).jpg");
		try {
			rc.recognizeOneImage(imageFile);
		} catch (FaceppParseException | JSONException | InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis()-time);
	}
	
}
