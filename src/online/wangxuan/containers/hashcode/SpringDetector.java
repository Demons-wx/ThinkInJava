package online.wangxuan.containers.hashcode;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;


import net.mindview.util.Print;

public class SpringDetector {
	/* detectSpring()方法使用反射机制来实例化及使用Groundhog类或任何从Groundhog派生出来的类。 */
	public static <T extends Groundhog> void detectSpring(Class<T> type) throws Exception {
		Constructor<T> ghog = type.getConstructor(int.class);
		Map<Groundhog, Prediction> map = new HashMap<Groundhog, Prediction>();
		for (int i = 0; i < 10; i++) {
			map.put(ghog.newInstance(i), new Prediction());
		}
		Print.print("map = " + map);
		Groundhog gh = ghog.newInstance(3);
		Print.print("Looking up prediction for " + gh);
		if(map.containsKey(gh)) {
			Print.print(map.get(gh));
		} else {
			Print.print("Key not found: " + gh);
		}
	}
	public static void main(String[] args) throws Exception {
		detectSpring(Groundhog2.class);
	}
}
