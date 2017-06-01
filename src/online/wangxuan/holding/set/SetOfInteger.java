package online.wangxuan.holding.set;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Set中最常被使用的是测试归属性，你可以很容易的询问某个对象是否在某个Set中，<br>
 * 正因为如此，查找就成为了Set中最重要的操作，因此你通常都会选择一个HashSet的实现，<br>
 * 它专门对快速查找进行了优化。<br><br>
 * 
 * Set具有与Collection完全一样的接口，因此没有任何额外的功能，不像前面有两个不同的list。<br>
 * 实际上Set就是Collection，只是行为不同。 <br><br>
 * Set是基于对象的值来确定归属性的。
 * @author wx
 *
 */
public class SetOfInteger {
	public static void main(String[] args) {
		Random rand = new Random(47);
		Set<Integer> intest = new HashSet<Integer>();
		/* 在0到29之间的10000个随机数被添加到了Set中，因此你可以想象，
		 * 每一个数都重复了许多次。但是每一个数只有一个实例出现在结果中。 */
		for (int i = 0; i < 10000; i++) {
			intest.add(rand.nextInt(30));
		}
		System.out.println(intest);
	}
}
