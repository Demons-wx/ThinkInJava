package online.wangxuan.containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.mindview.util.Print;
import online.wangxuan.containers.util.Countries;

public class CollectionMethods {
	public static void main(String[] args) {
		Collection<String> c = new ArrayList<String>();
		c.addAll(Countries.names(6));
		c.add("ten");
		c.add("eleven");
		Print.print(c);
		// list --> array
		Object[] array = c.toArray();
		String[] str = c.toArray(new String[0]);
		// 找出最大最小的元素
		Print.print("Collections.max(c) = " + Collections.max(c));
		Print.print("Collections.min(c) = " + Collections.min(c));
		// 将一个Collection添加到另一个Collection上
		Collection<String> c2 = new ArrayList<String>();
		c2.addAll(Countries.names(6));
		c.addAll(c2);
		Print.print(c);
		c.remove(Countries.DATA[0][0]);
		Print.print(c);
		c.remove(Countries.DATA[0][1]);
		Print.print(c);
		c.removeAll(c2);
		Print.print(c);
		c.addAll(c2);
		Print.print(c);
		// 确定元素是否在集合中
		String val = Countries.DATA[3][0];
		Print.print("c.contains(" + val + ") = " + c.contains(val));
		// 确定集合是否在集合中
		Print.print("c.contains(c2) = " + c.containsAll(c2));
		Collection<String> c3 = ((List<String>)c).subList(3, 5);
		// c2和c3的交集
		c2.retainAll(c3);
		Print.print(c2);
		// 将在c2和c3中同时出现的元素从c2中移除
		c2.removeAll(c3);
		Print.print("c2.isEmpty() = " + c2.isEmpty());
		c = new ArrayList<String>();
		c.addAll(Countries.names(6));
		Print.print(c);
		c.clear();
		Print.print("after c.clear(): " + c);
	}
}
