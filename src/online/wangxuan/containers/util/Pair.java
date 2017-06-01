package online.wangxuan.containers.util;

/**
 * 对象对
 * @author wx
 *
 */
public class Pair<K, V> {
	public final K key;
	public final V value;
	public Pair(K k, V v) {
		key = k;
		value = v;
	}
}
