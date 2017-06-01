package online.wangxuan.holding.set;

import java.util.Set;
import java.util.TreeSet;

import net.mindview.util.TextFile;

/**
 * TextFile继承自List<String>，其构造器将打开文件，并根据正则表达式"\\W+" <br>
 * 将其断开为单词，这个正则表达式表示"一个或多个字母"。所产生的结果传递给了TreeSet <br>
 * 的构造器，它将把List中的内容添加到自身中。由于它是TreeSet，因此其结果是排序的。
 * @author wx
 *
 */
public class UniqueWords {
	public static void main(String[] args) {
		Set<String> words = new TreeSet<String>(
				new TextFile("src/online/wangxuan/holding/set/SetOperations.java", "\\W+"));
		System.out.println(words);
		
		/**
		 * 如果你想按照字母排序，那么可以向TreeSet的构造器传入String.CASE_INSENSITIVE_ORDER
		 * 比较器。
		 */
		Set<String> words2 = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		words2.addAll(new TextFile("src/online/wangxuan/holding/set/SetOperations.java", "\\W+"));
		System.out.println(words2);
	}
}
