package online.wangxuan.concurrency.daemon;

import java.util.concurrent.TimeUnit;
/**
 * <p>当所有的非后台线程结束时，程序也就终止了，所有的后台线程都将被杀死。</p>
 * @author wx
 *
 */
public class SimpleDaemons implements Runnable {

	public void run() {
		try {
			while(true){
				TimeUnit.MICROSECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			}
		} catch (InterruptedException e) {
			System.out.println("sleep() interrupted..");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Thread daemon = new Thread(new SimpleDaemons());
			// 必须在线程启动之前调用setDaemon()才能把它设置为后台线程
			daemon.setDaemon(true);
			daemon.start();
		}
		System.out.println("all daemon started");
		/**
		 * 一旦main()线程完成工作，就没有什么能够阻止程序结束了，因为除了后台线程之外，已经没有在运行的线程了。
		 * main()线程被设置为短暂睡眠，所以可以观察到后台线程启动的情况。
		 */
		TimeUnit.MICROSECONDS.sleep(50);
	}
}
