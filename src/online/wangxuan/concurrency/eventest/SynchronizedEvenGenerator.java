package online.wangxuan.concurrency.eventest;

import online.wangxuan.concurrency.IntGenerator;

/**
 * <p>第一个进入next()的任务会获得锁，任何其他试图获取锁的任务都将
 * 从其开始尝试时被阻塞，直至第一个任务释放锁。</p>
 * @author wx
 *
 */
public class SynchronizedEvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;
	public synchronized int next() {
		++currentEvenValue;
		Thread.yield();
		++currentEvenValue;
		return currentEvenValue;
	}
	public static void main(String[] args) {
		EvenChecker.test(new SynchronizedEvenGenerator());
	}
}
