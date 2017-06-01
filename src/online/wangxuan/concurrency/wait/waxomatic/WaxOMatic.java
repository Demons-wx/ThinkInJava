package online.wangxuan.concurrency.wait.waxomatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import net.mindview.util.Print;


/**
 * WaxOMatic有两个过程：<br>
 * 一个是将蜡涂到car上，一个是抛光它。抛光任务在涂蜡任务完成之前是不能执行其工作的。<br>
 * 而涂蜡任务在涂另一层蜡之前必须等待抛光任务完成。
 * @author wx
 *
 */
public class WaxOMatic {
	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOff(car));
		exec.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}

class Car {
	private boolean waxOn = false;
	public synchronized void waxed() {
		waxOn = true;	// ready to buff
		notifyAll();
	}
	public synchronized void buffed() {
		waxOn = false;	// ready for another coat of wax
		notifyAll();
	}
	public synchronized void waitForWaxing() throws InterruptedException {
		while(waxOn == false) {
			wait();
		}
	}
	public synchronized void waitForBuffing() throws InterruptedException {
		while(waxOn == true) {
			wait();
		}
	}
}

class WaxOn implements Runnable {
	private Car car;
	public WaxOn(Car c) {
		car = c;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Print.printnb("Wax On! ");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				car.waitForBuffing();
			}
		} catch (InterruptedException e) {
			Print.print("Exiting via interrupt");
		}
		Print.print("Ending wax on task");
	}
}

class WaxOff implements Runnable {
	private Car car;
	public WaxOff(Car c) {
		car = c;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				car.waitForWaxing(); // 没打蜡的话，线程挂起，等待打蜡。
				Print.printnb("Wax Off! ");  // 开始抛光...
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed(); // 抛光完成，唤醒打蜡进程
			}
		} catch (InterruptedException e) {
			Print.print("Exiting via interrupt");
		}
		Print.print("Ending Wax Off task");
	}
}





















