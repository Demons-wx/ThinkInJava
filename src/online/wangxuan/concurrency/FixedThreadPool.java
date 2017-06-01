package online.wangxuan.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>FixedThreadPool 可以一次性预先执行代价高昂的线程分配，因而可以限制线程的数量。</p>
 * 
 * @author wx
 *
 */
public class FixedThreadPool {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}
}
