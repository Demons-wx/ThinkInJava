package online.wangxuan.concurrency.daemon;

import java.util.concurrent.TimeUnit;

/**
 * <p>当这个程序运行，你将会看到finally子句不执行，如果你注释掉setDaemon()子句的调用，finally会执行。</p>
 * @author wx
 *
 */
public class DaemonsDontRunFinally {
	
	public static void main(String[] args) {
		Thread t = new Thread(new ADaemon());
		t.setDaemon(true);
		t.start();
	}
}

class ADaemon implements Runnable {
	
	public void run(){
		try {
			System.out.println("Started ADaemon");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		} finally {
			System.out.println("This should always run?");
		}
	}
}