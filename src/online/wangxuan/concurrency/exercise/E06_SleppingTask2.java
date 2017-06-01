package online.wangxuan.concurrency.exercise;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>创建一个任务，它将睡眠1-10秒随机数量的时间，然后显示它的睡眠时间并退出。</p>
 * @author wx
 *
 */
class SleppingTask2 implements Runnable {
	private static Random rdm = new Random();
	private final int sleep_time = rdm.nextInt(10) + 1;
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(sleep_time);
		} catch (InterruptedException e) {
			System.err.println("Interrupted:" + e);
		}
		System.out.println(sleep_time);
	}
	
}

public class E06_SleppingTask2 {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		int i = 10;
		for (int j = 0; j < 10; j++) {
			exec.execute(new SleppingTask2());
		}
		Thread.yield();
		exec.shutdown();
	}
}
