package online.wangxuan.concurrency.wait.waxomatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import net.mindview.util.Print;

/**
 * 使用互斥并允许任务挂起的基本类是Condition,你可以通过在Condition上调用await()来挂起一个任务。<br>
 * 当外部条件发生变化，意味着某个任务应该继续执行，你可以通过调用signal()来通知这个任务，<br>
 * 或者调用signalAll()来唤醒所有在这个Condition上被其自身挂起的任务。 <br>
 * <br>
 * 与notifyAll()相比，signalAll()是更安全的方式。
 * @author wx
 *
 */
public class WaxOMatic2 {
	public static void main(String[] args) throws Exception {
		Car2 car2 = new Car2();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOff2(car2));
		exec.execute(new WaxOn2(car2));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}

class Car2 {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean waxOn = false;
	public void waxed() {
		try {
			lock.lock();
			waxOn = true;	// ready to buff
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	public void buffed() {
		lock.lock();
		try {
			waxOn = false; // ready for another coat of wax
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	public void waitForWaxing() throws InterruptedException {
		lock.lock();
		try {
			while (waxOn = false) {
				condition.await();
			}
		} finally {
			lock.unlock();
		}
	}
	public void waitForBuffing() throws InterruptedException {
		lock.lock();
		try {
			while (waxOn == true) {
				condition.await();
			}
		} finally {
			lock.unlock();
		}
	}
}

class WaxOn2 implements Runnable {
	private Car2 car2;
	public WaxOn2(Car2 car2) {
		this.car2 = car2;
	}
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Print.printnb("Wax on! ");
				TimeUnit.MILLISECONDS.sleep(200);
				car2.waxed();
				car2.waitForBuffing();
			}
		} catch (InterruptedException e) {
			Print.print("Exiting via interrupt");
		}
		Print.print("Ending wax on task");
	}
}

class WaxOff2 implements Runnable {
	private Car2 car2;
	public WaxOff2(Car2 c) {
		car2 = c;
	}
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car2.waitForWaxing();
				Print.printnb("Wax off! ");
				TimeUnit.MILLISECONDS.sleep(200);
				car2.buffed();
			}
		} catch (InterruptedException e) {
			Print.print("Exiting via interrupt");
		}
		Print.print("Ending wax off task");
	}
}





