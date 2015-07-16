package facerec;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class APIKeySecret implements Serializable {
	private static final long serialVersionUID = 1182737596604128193L;
	private int count;
	private ArrayList<Map<String, String>> keyList;
	
	public APIKeySecret() {
		count = 0;
		keyList = new ArrayList<Map<String, String>>();
	}
	
	public void add(String key, String secret) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("secret", secret);
		keyList.add(map);
	}
	
	public Map<String, String> get() {
		if (count == keyList.size()) {
			count = 0;
		}
		Map<String, String> map = keyList.get(count);
		count++;
		return map;
	}
	
	public int getLength() {
		return keyList.size();
	}

	public String toString() {
		String result = "";
		for (Map<String, String> map : keyList) {
			result += map.get("key") + "\t" + map.get("secret") + "\n";
		}
		return result;
	}
	
	
}
