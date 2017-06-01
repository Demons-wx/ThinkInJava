package online.wangxuan.concurrency.eventest;

import java.util.concurrent.atomic.AtomicInteger;

import online.wangxuan.concurrency.IntGenerator;

/**
 * 下面是使用AtomicInteger重写MutexEvenGenerator.java
 * @author wx
 *
 */
public class AtomicEvenGenerator extends IntGenerator{
	private AtomicInteger currentEvenValue = new AtomicInteger(0);
	public int next() {
		return currentEvenValue.addAndGet(2);
	}
	public static void main(String[] args) {
		EvenChecker.test(new AtomicEvenGenerator());
	}
}
