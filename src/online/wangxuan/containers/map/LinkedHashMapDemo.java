package online.wangxuan.containers.map;

import java.util.LinkedHashMap;

import net.mindview.util.Print;

import online.wangxuan.containers.util.CountingMapData;

public class LinkedHashMapDemo {
	public static void main(String[] args) {
		LinkedHashMap<Integer, String> linkedMap = new LinkedHashMap<Integer, String>(new CountingMapData(9));
		Print.print(linkedMap);
		// Least-recently-used order
		linkedMap = new LinkedHashMap<Integer, String>(16, 0.75f, true);
		linkedMap.putAll(new CountingMapData(9));
		Print.print(linkedMap);
		for (int i = 0; i < 6; i++) {
			linkedMap.get(i);
		}
		Print.print(linkedMap);
		linkedMap.get(0);
		Print.print(linkedMap);
	}
}
