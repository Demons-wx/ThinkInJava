package online.wangxuan.concurrency.eventest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import online.wangxuan.concurrency.IntGenerator;

/**
 * <p>多线程的去检测EvenGenerator产生的值是否为偶数</p>
 * 注意：test()方法会产生指定数量的线程去检查EvenGenerator的产出，<br>
 * 由于EvenGenerator中next()方法中的递增操作是非原子的，所以B线程可能会在<br>
 * A线程完成第一个递增操作后访问currentEvenValue,此时是非偶数，任务失败。
 * @author wx
 *
 */
public class EvenChecker implements Runnable {

	private IntGenerator generator;
	private final int id;
	public EvenChecker(IntGenerator generator, int id) {
		super();
		this.generator = generator;
		this.id = id;
	}
	public void run() {
		while(!generator.isCanceled()) {
			int val = generator.next();
			if(val % 2 != 0) {
				System.out.println(val + " not even!");
				generator.cancel();
			} 
		}
	}
	public static void test(IntGenerator gp, int count) {
		System.out.println("Press Control-C to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			exec.execute(new EvenChecker(gp, i));
		}
		exec.shutdown();
	}
	public static void test(IntGenerator gp) {
		test(gp, 10);
	}
}
