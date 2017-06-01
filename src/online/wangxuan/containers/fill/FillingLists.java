package online.wangxuan.containers.fill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 这个示例展示了两种用于对单个对象的引用来填充Collection的方式，第一种是用Collections.nCopies()
 * 创建传递给构造器的List，这里填充的是ArrayList。<br><br>
 * 
 * 从输出中可以看出，所有的引用都被设置为指向相同的对象，在第二种方法的Collection.fill()
 * 被调用也是如此。fill()方法用处更有限，因为它只能替换已经在List中存在的元素，而不能添加新的元素。
 * @author WX
 *
 */
public class FillingLists {
	public static void main(String[] args) {
		List<StringAdress> list = new ArrayList<StringAdress>(
				Collections.nCopies(4, new StringAdress("Hello")));
		System.out.println(list);
		Collections.fill(list, new StringAdress("World"));
		System.out.println(list);
	}
}

class StringAdress {
	private String s;
	public StringAdress(String s) {
		this.s = s;
	}
	public String toString() {
		return super.toString() + " " + s;
	}
}