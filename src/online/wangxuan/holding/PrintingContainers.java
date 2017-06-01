package online.wangxuan.holding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import net.mindview.util.Print;

public class PrintingContainers {
	static Collection fill(Collection<String> collection) {
		collection.add("rat");
		collection.add("cat");
		collection.add("dog");
		collection.add("dog");
		return collection;
	}
	static Map fill(Map<String, String> map) {
		map.put("rat", "Fuzzy");
		map.put("cat", "Rags");
		map.put("dog", "Bosco");
		map.put("dog", "spot");
		return map;
	}
	public static void main(String[] args) {
		/* List 以特定的顺序保存一组元素 
		 * ArrayList和LinkedList都是按照被插入的顺序保存元素
		 * */
		Print.print(fill(new ArrayList<String>()));
		Print.print(fill(new LinkedList<String>()));
		/* Set 元素不能重复 
		 * HashSet使用的是相当复杂的方式来存储元素，此时只需知道这种计数是最快获取元素的方式。
		 * TreeSet按照比较结果的升序保存对象
		 * LinkedHashSet按照被添加的顺序保存对象
		 * */
		Print.print(fill(new HashSet<String>()));
		Print.print(fill(new TreeSet<String>()));
		Print.print(fill(new LinkedHashSet<String>()));
		
		/* HashMap提供了最快的查找技术，也没有按照任何明显的顺序来保存其元素 */
		Print.print(fill(new HashMap<String, String>()));
		/* TreeMap按照比较结果的升序保存键 */
		Print.print(fill(new TreeMap<String, String>()));
		/* 按照插入的顺序保存键，同时保留了HashMap的查询速度 */
		Print.print(fill(new LinkedHashMap<String, String>()));
	}
}









