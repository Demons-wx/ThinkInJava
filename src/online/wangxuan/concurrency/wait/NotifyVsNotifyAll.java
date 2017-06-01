package online.wangxuan.concurrency.wait;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * notifyAll()将唤醒"所有正在等待的任务"。这是否意味着在程序中的 <br>
 * 任何地方、任何处于wait()状态中的任务都将被任何对notifyAll()的调用唤醒？<br>
 * 情况并非如此！ <br>
 * 事实上，当notifyAll()因某个特定锁被调用时，只有等待这个锁的任务才会被唤醒：
 * @author wx
 *
 */
public class NotifyVsNotifyAll {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Task());
		}
		exec.execute(new Task2());
		Timer timer = new Timer();
		/* 设置timer为每4/10秒执行一次run()方法 */
		timer.scheduleAtFixedRate(new TimerTask() {
			boolean prod = true;
			public void run() {
				if(prod) {
					System.out.print("\nnotify() ");
					Task.blocker.prod();
					prod = false;
				} else {
					System.out.print("\nnotifyAll() ");
					Task.blocker.prodAll();
					prod = true;
				}
			}
		}, 400, 400);
		TimeUnit.SECONDS.sleep(5);
		timer.cancel();
		System.out.println("\nTimer canceled");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.print("Task2.blocker.prodAll() ");
		Task2.blocker.prodAll();
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.print("\nShutting down");
		exec.shutdownNow();
	}
}

class Blocker {
	synchronized void waitingCall() {
		try {
			while(!Thread.interrupted()) {
				wait();
				System.out.print(Thread.currentThread() + " ");
			}
		} catch (InterruptedException e) {
			// OK to exit this way
		}
	}
	synchronized void prod() {
		notify();
	}
	synchronized void prodAll() {
		notifyAll();
	}
}

class Task implements Runnable {
	static Blocker blocker = new Blocker();
	public void run() {
		blocker.waitingCall();
	}
}

class Task2 implements Runnable {
	// A separate Blocker object
	static Blocker blocker = new Blocker();
	public void run() {
		blocker.waitingCall();
	}
}

























