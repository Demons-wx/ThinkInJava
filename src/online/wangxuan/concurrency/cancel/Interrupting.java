package online.wangxuan.concurrency.cancel;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 可中断的阻塞
 * @author wx
 *
 */
class SleepBlocked implements Runnable {
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
		System.out.println("Exiting SleepBlocked.run()");
	}
}

class IOBlocked implements Runnable {
	private InputStream in;
	public IOBlocked(InputStream is) {
		in = is;
	}
	public void run() {
		try {
			System.out.println("Waiting for read():");
			in.read();
		} catch (IOException e) {
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("Interrupted from blocked I/O");
			} else {
				throw new RuntimeException(e);
			}
		}
		System.out.println("Exiting IOBlocked.run()");
	}
}

/**
 * 构造器中的匿名类通过调用f()获取了对象锁，由于f()永远都不返回，因此这个锁永远都不会释放<br>
 * 所以SynchronizedBlocked.run()在试图调用f()时阻塞。
 * @author wx
 *
 */
class SynchronizedBlocked implements Runnable {
	public synchronized void f() {
		while(true) {
			Thread.yield();
		}
	}
	public SynchronizedBlocked() {
		new Thread() {
			public void run() {
				f();
			}
		}.start();
	}
	public void run() {
		System.out.println("Trying to call f()");
		f();
		System.out.println("Exiting SynchronizedBlocked.run()");
	}
}

/**
 * 从输出中可以看出，你能够中断对sleep()的调用，但是你不能中断正在试图获取synchronized<br>
 * 锁或者试图执行I/O操作的线程。
 * @author wx
 *
 */
public class Interrupting {
	private static ExecutorService exec = Executors.newCachedThreadPool();
	/* 如果你在Executor上调用shutdownNow()，那么它将发送一个interrupt()调用给它
	 * 启动的所有线程。但是如果你只希望中断某个单一任务，如果使用Executor，那么调用
	 * submit()而不是execute()来启动任务，就可以持有该任务的上下文。它会返回一个泛型
	 * Future<?>，持有这种Future的关键在于你可以在其上调用cancel()，并因此使用它来
	 * 中断某个特定任务。 */
	static void test(Runnable r) throws InterruptedException {
		Future<?> f = exec.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Interrupting " + r.getClass().getName());
		f.cancel(true);
		System.out.println("Interrupt sent to " + r.getClass().getName());
	}
	public static void main(String[] args) throws Exception {
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(3);
		System.out.println("Aborting with System.exit(0)");
		System.exit(0);
	}
}





















