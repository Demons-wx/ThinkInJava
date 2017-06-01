package online.wangxuan.concurrency.cancel;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BlockedMutex类有一个构造器，它要获取所创建对象上自身的Lock，并且从不释放这个锁。<br>
 * 
 * @author wx
 *
 */
class BlockedMutex {
	private Lock lock = new ReentrantLock();
	public BlockedMutex() {
		lock.lock();
	}
	public void f() {
		try {
			// this will never be available to a second task
			lock.lockInterruptibly();
			System.out.println("lock acquired in f()");
		} catch (InterruptedException e) {
			System.out.println("Interrupted from lock acquisition in f()");
		}
	}
}

/**
 * 如果你试图从第二个任务中调用f()，那么将会总是因为Mutex不可获得而被阻塞。<br>
 * run()方法总是在调用blocked.f()的地方停止。
 * @author wx
 *
 */
class Blocked2 implements Runnable {
	BlockedMutex blocked = new BlockedMutex();
	public void run() {
		System.out.println("Waiting for f() in BlockedMutex");
		blocked.f();
		System.out.println("Broken out of blocked call");
	}
}

/**
 * 无论在任何时刻，只要任务以不可中断的方式被阻塞，那么都有潜在的会锁住程序的可能。<br>
 * Java SE5并发类库中添加了一个新特性，即在ReentrantLock上阻塞的任务具备可以被中 <br>
 * 断的能力，这与synchronized方法或临界区上阻塞的任务完全不同。
 * 
 * <p>与I/O不同，interrupt()可以打断被互斥所阻塞的调用</p>
 * 
 * @author wx
 *
 */
public class Interrupting2 {
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Issuing t.interrupt()");
		t.interrupt();
	}
}

















