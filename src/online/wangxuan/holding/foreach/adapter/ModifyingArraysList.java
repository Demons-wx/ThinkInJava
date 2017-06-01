package online.wangxuan.holding.foreach.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 接上例MultiIterableClass，如果这个由Arrays.asList()方法产生的List <br>
 * 被直接打乱，那么它就会修改底层的数组，就像下面这样：
 * @author wx
 *
 */
public class ModifyingArraysList {
	public static void main(String[] args) {
		Random rand = new Random(47);
		Integer[] ia = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		/* 第一种情况，Arrays.asList()的输出被传递给ArrayList()的构造器，
		 * 这将创建一个引用ia的元素的ArrayList，因此打乱这些引用不会修改数组。 */
		List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(ia));
		System.out.println("Before shuffling: " + list1);
		Collections.shuffle(list1, rand);
		System.out.println("After shuffling: " + list1);
		System.out.println("array: " + Arrays.toString(ia));
		/**
		 * 如果直接使用Arrays.asList(ia)的结果，这种打乱就会修改ia的顺序。
		 * 意识到Arrays.asList()产生的List对象会使用底层数组作为其物理实现是很重要的。
		 * 只要你执行的操作会修改这个List，并且你不想原来的数组被修改，那么你
		 * 就应该在另一个容器中创建一个副本。
		 */
		List<Integer> list2 = Arrays.asList(ia);
		System.out.println("Before shuffling: " + list2);
		Collections.shuffle(list2, rand);
		System.out.println("After shuffling: " + list2);
		System.out.println("array: " + Arrays.toString(ia));
	}
}
