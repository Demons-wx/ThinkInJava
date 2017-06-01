package online.wangxuan.concurrency;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CaptureUncaughtException {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
		exec.execute(new ExceptionThread2());
	}
}

class ExceptionThread2 implements Runnable {
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("run() by "+ t);
		System.out.println("eh = " + t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
}

/**
 * <p>Thread.UncaughtExceptionHandler是Java SE5中新的接口</p>
 * 它允许你在每一个Thread对象上都附着一个异常处理器。<br>
 * uncaughtException()会在线程因未捕获的异常而临近死亡时被调用。
 * @author wx
 *
 */
class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught " + e);
	}
}

/**
 * 为了使用MyUncaughtExceptionHandler，我们创建了一个新类型的ThreadFactory。<br>
 * 它将在每个新创建的Thread对象上附着一个Thread.UncaughtExceptionHandler>。<br>
 * 我们将这个工厂传递给Executors创建新的ExecutorService方法
 * @author wx
 *
 */
class HandlerThreadFactory implements ThreadFactory {
	public Thread newThread(Runnable r) {
		System.out.println(this + " creating new Thread");
		Thread t = new Thread(r);
		System.out.println("created " + t);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("eh = " + t.getUncaughtExceptionHandler());
		return t;
	}
}

