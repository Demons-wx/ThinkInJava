package online.wangxuan.holding.foreach.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import online.wangxuan.holding.foreach.IterableClass;

/**
 * 我们可以在IterableClass.java中添加两种适配器方法：
 * @author wx
 *
 */
public class MultiIterableClass extends IterableClass {
	public Iterable<String> reversed() {
		return new Iterable<String>() {
			public Iterator<String> iterator() {
				return new Iterator<String>() {
					int current = words.length - 1;
					public boolean hasNext() {
						return current > -1;
					}
					public String next() {
						return words[current--];
					}
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
	/* 没有创建自己的Iterator，而是直接返回被打乱的List中的Iterator */
	public Iterable<String> randomized() {
		return new Iterable<String>() {
			public Iterator<String> iterator() {
				List<String> shuffled = new ArrayList<String>(Arrays.asList(words));
				Collections.shuffle(shuffled, new Random(47));
				return shuffled.iterator();
			}
		};
	}
	/* 从第三行输出的结果可以看出：Collections.shuffle()方法没有影响到原来的数组，
	 * 只是打乱了shuffled中的引用。这是因为randomized()方法用一个ArrayList将
	 * Arrays.asList()方法的结果包装起来了。 */
	public static void main(String[] args) {
		MultiIterableClass mic = new MultiIterableClass();
		for (String s : mic.reversed()) {
			System.out.print(s + " ");
		}
		System.out.println();
		for (String s : mic.randomized()) {
			System.out.print(s + " ");
		}
		System.out.println();
		for (String s : mic) {
			System.out.print(s + " ");
		}
	}
}





















