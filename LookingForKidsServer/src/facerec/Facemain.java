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
import org.json.JSONObject;

import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

public class Facemain {
	private static final String TRAIN_PATH = "./res/train/";
	private static final String TEST_PATH = "./res/test/";
	private static final String KEY_SECRET_PATH = "./key.obj";
	//api key & secret
	APIKeySecret apiKeySecretMap = new APIKeySecret();
	HttpRequests httpRequests;
	int length;
	
	public static void main(String[] args) throws Exception {
		Facemain fm = new Facemain();
		long time = System.currentTimeMillis();
		//main.init();
		//main.train();
		fm.recognize();
		System.out.println(System.currentTimeMillis()-time);
	}
	
	private void recognize() throws Exception {
		File files = new File(TEST_PATH);
		JSONObject result;
		JSONArray[] personsList = new JSONArray[length];
		
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
		
		for (File imageFile : files.listFiles()) {
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
			for(Map.Entry<String,Double> mapping : mappingList){
				System.out.print(personNameToTag.get(mapping.getKey())+":"+mapping.getValue()+"\t");
			}
			System.out.println();
		}		
	}

	public Facemain() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(KEY_SECRET_PATH)));
			apiKeySecretMap =  (APIKeySecret)in.readObject();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		length = apiKeySecretMap.getLength();
	}
	
	public void init() throws Exception {
		for (int i = 0; i < length; i++) {
			Map<String, String> map = apiKeySecretMap.get();
			httpRequests = new HttpRequests(map.get("key"), map.get("secret"));
			//create group
			httpRequests.groupCreate(new PostParameters().setGroupName("group"));
		}
	}
	
	public void train() throws Exception {
		int count = 0;
		File files = new File(TRAIN_PATH);
		
		for (File imageFile : files.listFiles()) {
			Map<String, String> map = apiKeySecretMap.get();
			httpRequests = new HttpRequests(map.get("key"), map.get("secret"));
			
			//detect face
			JSONObject result = httpRequests.detectionDetect(
					new PostParameters().setImg(imageFile));

			for (int i = 0; i < result.getJSONArray("face").length(); ++i) {			
				//create person
				httpRequests.personCreate(
						new PostParameters().setPersonName("person_"+count));
				
				//add face
				httpRequests.personAddFace(new PostParameters().setPersonName("person_" + count).setFaceId(
						result.getJSONArray("face").getJSONObject(i).getString("face_id")));
				
				//set person info
				httpRequests.personSetInfo(new PostParameters().setPersonName(
						"person_" + count).setTag(imageFile.getName().split("\\.")[0]));
				
				//add person
				httpRequests.groupAddPerson(
						new PostParameters().setGroupName("group").setPersonName("person_" + count));
				
				count++;
			}
		}
		
		int length = apiKeySecretMap.getLength();
		for (int i = 0; i < length; i++) {
			Map<String, String> map = apiKeySecretMap.get();
			httpRequests = new HttpRequests(map.get("key"), map.get("secret"));
			//train
			httpRequests.trainIdentify(new PostParameters().setGroupName("group"));
		}
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
					String faceId= result.getJSONArray("face").getJSONObject(0).getString("face_id");
					result = httpRequests.recognitionCompare(
							new PostParameters().setFaceId1(testFaceId).setFaceId2(faceId));
					personNameToSimilarity.put(personName, result.getDouble("similarity"));
				}
					
				//delete person
				result = httpRequests.personDelete(new PostParameters().setPersonName("person_test"));
		
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
}
