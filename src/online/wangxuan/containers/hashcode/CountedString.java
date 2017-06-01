package online.wangxuan.containers.hashcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mindview.util.Print;

public class CountedString {
	private static List<String> created = new ArrayList<String>();
	private String s;
	// 此id表示包含相同String的CountedString对象的编号
	private int id = 0;
	
	public CountedString(String str) {
		s = str;
		created.add(str);
		for (String s2 : created) {
			if(s2.equals(s))
				id++;
		}
	}
	
	public String toString() {
		return "String: " + s + " id: " + id + " hashCode(): " + hashCode();
	}
	/* hashCode() 和 equals() 都基于CountedString的这两个域来生成结果，如果它们只基于String
	 * 或者只基于id，不同的对象就可能产生相同的值 */
	public int hashCode() {
		int result = 17;
		result = 37 * 17 + s.hashCode();
		result = 37 * result + id;
		return result;
	}
	
	public boolean equals(Object o) {
		return o instanceof CountedString && s.equals(((CountedString)o).s) && id == ((CountedString)o).id;
	}
	
	public static void main(String[] args) {
		Map<CountedString, Integer> map = new HashMap<CountedString, Integer>();
		CountedString[] cs = new CountedString[5];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = new CountedString("hi");
			map.put(cs[i], i);
		}
		Print.print(map);
		for (CountedString cstring : cs) {
			Print.print("Looking up " + cstring);
			Print.print(map.get(cstring));
		}
	}
}
