package online.wangxuan.containers.fill;

import java.util.LinkedHashMap;

import net.mindview.util.Generator;
import online.wangxuan.containers.util.Pair;

/**
 * 用各种不同的Generator、Iterator和常量值的组合来填充Map初始化对象
 * @author wx
 *
 */
public class MapData<K, V> extends LinkedHashMap<K, V> {
	/* 单独的键值对生成器 */
	public MapData(Generator<Pair<K, V>> gen, int quantity) {
		for (int i = 0; i < quantity; i++) {
			Pair<K, V> p = gen.next();
			put(p.key, p.value);
		}
	}
	/* 两个键值分离的生成器 */
	public MapData(Generator<K> genK, Generator<V> genV, int quantity) {
		for (int i = 0; i < quantity; i++) {
			put(genK.next(), genV.next());
		}
	}
	/* 一个键生成器和一个单值 */
	public MapData(Generator<K> genK, V value, int quantity) {
		for (int i = 0; i < quantity; i++) {
			put(genK.next(), value);
		}
	}
	/* 一个Iterable和一个值生成器 */
	public MapData(Iterable<K> genK, Generator<V> genV) {
		for (K k : genK) {
			put(k, genV.next());
		}
	}
	/* 一个Iterable和一个单值 */
	public MapData(Iterable<K> genK, V value) {
		for (K k : genK) {
			put(k, value);
		}
	}
	
	public static <K,V> MapData<K, V> map(Generator<Pair<K, V>> gen, int quantity) {
		return new MapData<K, V>(gen, quantity);
	}
	public static <K, V> MapData<K, V> map(Generator<K> genK, Generator<V> genV, int quantity) {
		return new MapData<K, V>(genK, genV, quantity);
	}
	public static <K, V> MapData<K, V> map(Generator<K> genK, V value, int quantity) {
		return new MapData<K, V>(genK, value, quantity);
	}
	public static <K, V> MapData<K, V> map(Iterable<K> genK, Generator<V> genV) {
		return new MapData<K, V>(genK, genV);
	}
	public static <K, V> MapData<K, V> map(Iterable<K> genK, V value) {
		return new MapData<K, V>(genK, value);
	}
}
