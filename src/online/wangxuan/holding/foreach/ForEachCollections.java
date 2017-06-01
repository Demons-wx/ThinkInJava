package online.wangxuan.holding.foreach;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * foreach可以应用于任何Collection对象。
 * @author wx
 *
 */
public class ForEachCollections {
	public static void main(String[] args) {
		Collection<String> cs = new LinkedList<String>();
		Collections.addAll(cs, "Take the long way home".split(" "));
		for (String s : cs) {
			System.out.print("'" + s + "' ");
		}
	}
}
