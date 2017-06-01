package online.wangxuan.concurrency.eventest;

import online.wangxuan.concurrency.IntGenerator;

/**
 * <p>持续的生成偶数，然后用EvenChecker进行检测，如果出现非偶数，则canceled=true</p>
 * @author wx
 *
 */
public class EvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;
	/**
	 * 有一点很重要，那就是要注意到递增程序自身也需要多个步骤，并且在递增过程中任务可能
	 * 被线程机制挂起---也就是说，在java中，递增不是原子操作。因此，如果不保护任务，即
	 * 使单一的递增也是不安全的。
	 */
	public int next() {
		++currentEvenValue; 
		++currentEvenValue;
		return currentEvenValue;
	}
	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}
}
