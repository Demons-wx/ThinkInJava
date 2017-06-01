package online.wangxuan.holding.foreach.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * 当你有一个接口并需要另一个接口时，编写适配器就可以解决问题。 <br>
 * 这里，我希望在默认的前向迭代器的基础上，添加产生反向迭代器的能力，<br>
 * 因此我不能使用覆盖，而是添加一个能够产生Iterable对象的方法，<br>
 * 该对象可以用于foreach语句。正如你所见，这使得我们可以提供多种 <br>
 * 使用foreach的方式：
 * @author wx
 *
 */
public class AdapterMethodIdiom {
	public static void main(String[] args) {
		ReversibleArrayList<String> ral = new ReversibleArrayList<>(
				Arrays.asList("To be or not to be".split(" ")));
		for (String s : ral) {
			System.out.print(s + " ");
		}
		System.out.println();
		for (String s : ral.reversed()) {
			System.out.print(s + " ");
		}
	}
}

class ReversibleArrayList<T> extends ArrayList<T> {
	public ReversibleArrayList(Collection<T> c) {
		super(c);
	}
	public Iterable<T> reversed() {
		return new Iterable<T>() {
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					int current = size() - 1;
					public boolean hasNext() {
						return current > -1;
					}
					public T next() {
						return get(current--);
					}
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
}