package online.wangxuan.concurrency.eventest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import online.wangxuan.concurrency.IntGenerator;

/**
 * <p>注意：对lock()的调用，必须放置在finally子句中带有unlock()的try-finally语句中，并且
 * return语句必须在try子句中出现，以确保unlock()不会过早发生，从而将数据暴露给第二个任务。</p>
 * 
 * <p>Lock对于synchronized的优势在于：<br>
 * 如果在使用synchronized关键字时，某些事物失败了，那就会抛出一个异常。<br>
 * 但你没有机会做任何清理工作，以维护系统使其处于良好状态。<br>
 * 有了显示的Lock对象，你就可以使用finally子句将系统维护在正确的状态了。</p>
 * @author wx
 *
 */
public class MutexEvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;
	private Lock lock = new ReentrantLock();
	public int next() {
		lock.lock();
		try {
			++currentEvenValue;
			Thread.yield();
			++currentEvenValue;
			return currentEvenValue;
		} finally {
			lock.unlock();
		}
	}
	public static void main(String[] args) {
		EvenChecker.test(new MutexEvenGenerator());
	}
}
