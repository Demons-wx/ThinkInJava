package online.wangxuan.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicityTest implements Runnable {

	private int i = 0;
	/**
	 * 缺少同步使得其数值可以在处于不稳定的中间状态时被读取。
	 * @return
	 */
	public int getValue() {
		return i;
	}
	private synchronized void evenIncrement() {
		i++;
		i++;
	}
	public void run() {
		while(true) {
			evenIncrement();
		}
	}
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest at = new AtomicityTest();
		exec.execute(at);
		while(true) {
			int val = at.getValue();
			if(val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}
}
