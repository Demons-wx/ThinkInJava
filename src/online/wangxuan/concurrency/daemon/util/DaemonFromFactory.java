package online.wangxuan.concurrency.daemon.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import online.wangxuan.concurrency.daemon.DaemonThreadFactory;

/**
 * 
 * @author wx
 *
 */
public class DaemonFromFactory implements Runnable {

	public void run() {
		try {
			while(true){
				TimeUnit.MICROSECONDS.sleep(100);
				System.out.println(Thread.currentThread() + ":" + this);
			}
		} catch (InterruptedException e) {
			System.out.println("interrupted");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < 10; i++) {
			exec.execute(new DaemonFromFactory());
		}
		System.out.println("all daemon started");
		TimeUnit.MICROSECONDS.sleep(100);
	}
}
