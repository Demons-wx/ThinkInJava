package online.wangxuan.concurrency.daemon;

import java.util.concurrent.TimeUnit;

/**
 * <p>可以调用isDaemon()方法来确定线程是否是一个后台线程。如果是，那么它创建的任何线程将被自动设置成后台线程.</p>
 * @author wx
 *
 */
public class Daemons {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Daemon());
		t.setDaemon(true);
		t.start();
		System.out.println("t isDaemon() = " + t.isDaemon() + ".");
		TimeUnit.SECONDS.sleep(1);
	}
}

/**
 * <p>Daemon线程被设置成了后台模式。然后派生出了许多子线程，这些子线程并没有被显示的设置为后台模式，但它们确实是后台线程。</p>
 *
 */
class Daemon implements Runnable {
	
	private Thread[] t = new Thread[10];
	public void run(){
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			System.out.print("DaemonSpawn " + i + " started. ");
		}
		for (int i = 0; i < t.length; i++) {
			System.out.print("t[" + i + "].isDaemon() = " + t[i].isDaemon() + ", ");
		}
		// Daemon进入了无限循环，并在循环中调用yield()，使其将控制权交给其他线程。
		while(true){
			Thread.yield();
		}
	}
}

class DaemonSpawn implements Runnable {

	public void run() {
		while(true){
			Thread.yield();
		}
	}
	
}