package online.wangxuan.holding.foreach;

import java.util.Iterator;

/**
 * Java SE5引入了新的被称为Iterable的接口，这个接口能够产生Iterator <br>
 * 的iterator()方法，并且Iterable接口被foreach用来在序列中移动。<br><br>
 * 
 * iterator()方法返回的是实现了Iterator<String>的匿名内部类的实例， <br>
 * 该匿名内部类可以遍历数组中的所有单词。在main()中，你可以看到ItarableClass <br>
 * 确实可以用于foreach语句中。<br><br>
 * 
 * 如果你创建了任何实现Iterable的类，都可以将它用于foreach语句中：
 * @author wx
 *
 */
public class IterableClass implements Iterable<String> {
	protected String[] words = ("And that is how "
			+ "we know the earth to be banana-shaped.").split(" ");
	public Iterator<String> iterator() {
		return new Iterator<String>() {
			private int index = 0;
			public boolean hasNext() {
				return index < words.length;
			}
			public String next() {
				return words[index++];
			}
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	public static void main(String[] args) {
		for (String s : new IterableClass()) {
			System.out.print(s + " ");
		}
	}
}
