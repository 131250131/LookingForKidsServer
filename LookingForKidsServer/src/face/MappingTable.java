package face;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class MappingTable {

	private Map<Integer, String> map;
	
	public MappingTable() {
		map = new HashMap<Integer, String>();
	}
	
	public String getValue(Integer key) {
		return map.get(key);
	}
	
	public Integer getKey(String value) {
		for (Integer k : map.keySet()) {
			if (value.equals(map.get(k))) {
				return k;
			}
		}
		return null;
	}
	
	public void add(Integer key, String value) {
		map.put(key, value);
	}
	
	public void save(String path) throws Exception {
		FileWriter writer = new FileWriter(new File(path));
		for (Integer k : map.keySet()) {
			writer.write(k + ":" + map.get(k) + "\n");
		}
		writer.close();
	}
	
	public void load(String path) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
		String string = reader.readLine();
		while (string != null) {
			map.put(Integer.parseInt(string.split(":")[0]), string.split(":")[1]);
			string = reader.readLine();
		}
		reader.close();
	}
	
	public String toString() {
		String result = "";
		for (Integer key : map.keySet()) {
			result += key + ":" + map.get(key) + "\n";
		}
		return result;
	}
}
