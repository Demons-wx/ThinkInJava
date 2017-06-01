package online.wangxuan.containers.hashcode;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import online.wangxuan.containers.util.Countries;

public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
	static final int SIZE = 997;
	@SuppressWarnings("unchecked")
	LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];
	
	/* hashCode()针对键被调用，并且其结果被强制转换为正数。为了使产生的数字适合buckets数组的大小，
	 * 取模操作将按照该数组尺寸取模。如果数组的某个位置是null,表示还没有元素被散列至此，所以，为了
	 * 保存刚散列到该定位的对象，需要创建一个新的LinkedList。一般的过程是，查看当前位置的list是否
	 * 有相同的元素，如果有，则将旧的值赋给oldValue，然后用新的值取代旧的值。 */
	public V put(K key, V value) {
		V oldValue = null;
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null) {
			buckets[index] = new LinkedList<MapEntry<K, V>>();
		}
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
		boolean found = false;
		ListIterator<MapEntry<K, V>> it = bucket.listIterator();
		while(it.hasNext()) {
			MapEntry<K, V> iPair = it.next();
			if(iPair.getKey().equals(key)) {
				oldValue = iPair.getValue();
				it.set(pair); // 用新的替换掉旧的
				found = true;
				break;
			}
		}
		if(!found) {
			buckets[index].add(pair);
		}
		return oldValue;
	}
	
	public V get(Object key) {
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null)
			return null;
		for (MapEntry<K, V> iPair : buckets[index]) {
			if(iPair.getKey().equals(key))
				return iPair.getValue();
		}
		return null;
	}
	
	public Set<Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
		for (LinkedList<MapEntry<K, V>> bucket : buckets) {
			if(bucket == null)
				continue;
			for(MapEntry<K, V> mPair : bucket) {
				set.add(mPair);
			}
		}
		return set;
	}
	
	public static void main(String[] args) {
		SimpleHashMap<String, String> m = new SimpleHashMap<String, String>();
		m.putAll(Countries.capitals(25));
		System.out.println(m);
		System.out.println(m.get("ERITREA"));
		System.out.println(m.entrySet());
	}
}
