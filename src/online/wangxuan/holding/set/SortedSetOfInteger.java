package online.wangxuan.holding.set;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 如果你想对结果排序，一种方式是使用TreeSet来替代HashSet。 <br><br>
 * HashSet所维护的顺序与TreeSet或LinkedHashSet都不同，因为他们的实现 
 * 具有不同的元素存储方式。 <br>
 * TreeSet将元素存储在红-黑树数据结构中，而HashSet使用的是散列函数。
 * LinkedHashSet因为查询速度的原因也使用了散列，但是看起来它使用了链表
 * 来维护元素的插入顺序。
 * @author wx
 *
 */
public class SortedSetOfInteger {
	public static void main(String[] args) {
		Random rand = new Random(47);
		SortedSet<Integer> inset = new TreeSet<Integer>();
		for (int i = 0; i < 10000; i++) {
			inset.add(rand.nextInt(30));
		}
		System.out.println(inset);
	}
}
