package online.wangxuan.concurrency.threadlocal;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>线程本地存储</p>
 * 为使用相同变量的每个不同的线程都创建不同的存储。因此如果你有5个线程都是用变量x
 * 所表示的对象，那线程本地存储就会生成5个用于x的不同的存储块。<br>
 * <br>
 * 创建和管理线程本地存储可以由java.lang.ThreadLocal类来实现
 * @author wx
 *
 */
public class ThreadLocalVariableHolder {
	
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
		/* 47为伪随机数的初始值 */
		private Random rand = new Random(47);
		/* 返回一个随机的初始值，线程第一次使用 get() 方法访问变量时将调用此方法 */
		protected synchronized Integer initialValue() {
			/* 返回此随机数生成器序列中 0（包括）和 n（不包括）之间均匀分布的 int 值。 */
			return rand.nextInt(10000);
		}
	};
	public static void increment() {
		value.set(value.get() + 1);
	}
	public static int get() {
		return value.get();
	}
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Accessor(i));
		}
		TimeUnit.SECONDS.sleep(3);
		/* shutdown() 和 shutdownNow()的区别：shutdown()不会停止当前正在运行的线程，只是不允许往线程池中添加新任务。
		 * shutdownNow() 会试图停止当前正在运行的线程 */
		exec.shutdownNow();
		
	}
}
/**
 * 在创建ThreadLocal时，你只能通过get()和set()方法来访问该对象的内容：<br>
 * 其中，get()方法将返回与其线程相关联的对象的副本，<br>
 * 而set()方法会将参数插入到为其线程存储的对象中，并返回存储中原有的对象。<br>
 * <br>
 * 注意：increment()和get()方法都不是synchronized的，因为ThreadLocal保证不会出现竞争条件。
 * @author wx
 *
 */
class Accessor implements Runnable {
	private final int id;
	public Accessor(int idn) {
		id = idn;
	}
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}
	public String toString() {
		return "#" + id + ": " + ThreadLocalVariableHolder.get();
	}
}