package common;

import java.util.HashMap;
import java.util.Map;

public class MappingTable<K, V> {

	protected Map<K, V> map;
	
	public MappingTable() {
		map = new HashMap<K, V>();
	}
	
	public V getValue(K key) {
		return map.get(key);
	}
	
	public K getKey(V value) {
		for (K k : map.keySet()) {
			if (value.equals(map.get(k))) {
				return k;
			}
		}
		return null;
	}
	
}
