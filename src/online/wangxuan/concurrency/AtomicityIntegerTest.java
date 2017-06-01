package online.wangxuan.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>使用原子类重写AtomicityTest</p>
 * Java提供的原子性变量类：AtomicInteger、AtomicLong、AtomicReference<br>
 * 对于常规编程来说，它们很少派上用场，但是涉及到性能调优，它们就大有用武之地。
 * @author wx
 *
 */
public class AtomicityIntegerTest implements Runnable {
	private AtomicInteger i = new AtomicInteger(0);
	public int getValue() {
		return i.get();
	}
	private void evenIncrement() {
		i.addAndGet(2);
	}
	public void run() {
		while(true) {
			evenIncrement();
		}
	}
	public static void main(String[] args) {
		/* 由于这个程序不会失败，所以添加了一个timer，以便在5s后自动终止。 */
		new Timer().schedule(new TimerTask() {
			public void run() {
				System.err.println("Aborting");
				System.exit(0);
			}
		}, 5000); // Terminate after 5s
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityIntegerTest ait = new AtomicityIntegerTest();
		exec.execute(ait);
		while(true) {
			int val = ait.getValue();
			if(val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}
}
