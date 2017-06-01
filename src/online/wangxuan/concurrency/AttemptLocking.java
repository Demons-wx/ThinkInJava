package online.wangxuan.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>使用synchronized关键字不能尝试获取锁且最终获取锁失败，<br>
 * 或者尝试着获取锁一段时间然后放弃它。要实现这些，<br>
 * 必须使用concurrent类库：</p>
 * @author wx
 *
 */
public class AttemptLocking {
	private ReentrantLock lock = new ReentrantLock();
	/**
	 * 尝试获取但最终未获取锁，这样如果其他人已经获取了这个锁，
	 * 那你就可以决定离开去执行其他的一些事情，而不是等待直至这个锁
	 * 被释放。
	 */
	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.out.println("tryLock():" + captured);
		} finally {
			if(captured) {
				lock.unlock();
			}
		}
	}
	/**
	 * 尝试去获取锁，该尝试可以在2秒之后失败。
	 */
	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		try {
			System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
		} finally {
			if(captured) {
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) {
		final AttemptLocking al = new AttemptLocking();
		al.untimed();
		al.timed();
		/**
		 * 创建一个单独的Thread，它将获取锁。
		 */
		new Thread() {
			{
				setDaemon(true);
			}
			public void run() {
				al.lock.lock();
				System.out.println("acquired");
			}
		}.start();
		Thread.yield();
		al.untimed();
		al.timed();
	}
}
