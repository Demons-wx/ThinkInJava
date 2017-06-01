package online.wangxuan.containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 未获支持的操作
 * @author wx
 *
 */
public class UnSupported {
	static void test(String msg, List<String> list) {
		System.out.println("--- " + msg + " ---");
		Collection<String> c = list;
		Collection<String> subList = list.subList(1, 8);
		// subList的副本
		Collection<String> c2 = new ArrayList<String>(subList);
		try {
			c.retainAll(c2);
		} catch(Exception e) {
			System.out.println("retainAll(): " + e);
		}
		try {
			c.removeAll(c2);
		} catch(Exception e) {
			System.out.println("removeAll(): " + e);
		}
		try {
			c.clear();
		} catch(Exception e) {
			System.out.println("clear(): " + e);
		}
		try {
			c.add("X");
		} catch(Exception e) {
			System.out.println("add(): " + e);
		}
		try {
			c.addAll(c2);
		} catch(Exception e) {
			System.out.println("addAll(): " + e);
		}
		try {
			c.remove("C");
		} catch(Exception e) {
			System.out.println("remove(): " + e);
		}
		try {
			list.set(0, "X");
		} catch(Exception e) {
			System.out.println("List.set(): " + e);
		}
	}
	public static void main(String[] args) {
		List<String> list = Arrays.asList("A B C D E F G H I J K L".split(" "));
		/* 把Arrays.asList()的结果作为构造器的参数传递给任何Collection (或者使用addAll()方法，或Collections.addAll()静态方法)，
		 * 这样可以生成允许使用所有的方法的普通容器。 */
		test("Modifiable Copy", new ArrayList<String>(list));
		/* 因为Arrays.asList()会生成一个List，它基于一个固定大小的数组，仅支持那些不会改变数组大小的操作，
		 * 对它而言是有道理的。任何会引起对底层数据结构的尺寸进行修改的方法都会产生一个UnsupportedOperationException异常，
		 * 以表示对未获支持的操作的调用。 */
		test("Arrays.asList()", list);
		/* Arrays.asList()返回的是固定尺寸的List，而Collections.unmodifiableList()产生不可修改的列表。
		 * 正如从输出中看到的，修改Arrays.asList()返回的List中的元素是可以的，因为这没有违反该List尺寸固定的特性。
		 * unmodifiableList()的结果在任何情况下都应该不是可修改的。 */ 
		test("unmodifiableList()", Collections.unmodifiableList(new ArrayList<String>(list)));
	}
}
