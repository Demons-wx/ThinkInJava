package online.wangxuan.concurrency.cancel;

import java.util.concurrent.TimeUnit;

/**
 * NeedsCleanup类强调在你经由异常离开循环时，正确清理资源的必要性。<br><br>
 * 注意：所有在Blocked3.run()中创建的NeedsCleanup资源都必须在其后面紧跟try-finally子句，
 * 以确保cleanup()方法总是会被调用。
 * @author wx
 *
 */
class NeedsCleanup {
	private final int id;
	public NeedsCleanup(int ident) {
		id = ident;
		System.out.println("NeedsCleanup " + id);
	}
	public void cleanup() {
		System.out.println("Cleaning up " + id);
	}
}
/**
 * 如果interrupt()在point2之后被调用，即在非阻塞操作的过程中。那么首先循环将结束，然后所有本地对象
 * 将被销毁，最后循环会经由while语句顶部退出。<br><br>
 * 但是，如果interrupt()在point1和point2之间被调用，那么这个任务就会在第一次试图调用阻塞操作之前，
 * 经由InterruptedException退出。在这种情况下，在异常被抛出之时唯一被创建出来的NeedsCleanup对象
 * 将被清除，你也就有了在finally子句中执行其他任何清除工作的机会。
 * @author wx
 *
 */
class Blocked3 implements Runnable {
	private volatile double d = 0.0;
	public void run() {
		try{
			while(!Thread.interrupted()) {
				// point1
				NeedsCleanup n1 = new NeedsCleanup(1);
				try {
					System.out.println("Sleeping");
					TimeUnit.SECONDS.sleep(1);
					// point2
					NeedsCleanup n2 = new NeedsCleanup(2);
					try {
						System.out.println("Calculating");
						for (int i = 1; i < 2500000; i++) {
							d = d + (Math.PI + Math.E) / d;
						}
						System.out.println("Finished time-consuming operation");
					} finally {
						n2.cleanup();
					}
				} finally {
					n1.cleanup();
				}
			}
			System.out.println("Exiting via while() test");
		} catch(InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		}
		
	}
}

/**
 * 必须给程序提供一个命令行参数，来表示在它调用interrupt()之前以毫秒为单位的延迟时间。
 * 通过不同的延迟，你可以在不同的地点退出Blocked3.run()。
 * @author wx
 *
 */
public class InterruptingIdiom {
	public static void main(String[] args) throws NumberFormatException, InterruptedException {
		if(args.length != 1) {
			System.out.println("usage: java InterruptingIdiom delay-in-mS");
			System.exit(1);
		}
		Thread t = new Thread(new Blocked3());
		t.start();
		TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
		t.interrupt();
	}
}
















