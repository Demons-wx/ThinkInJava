package online.wangxuan.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CaptureUncaughtException使得你可以按照具体情况逐个地设置处理器。<br>
 * 如果你直到将要在代码中处处使用相同的异常处理器，那么更简单的方式是在Thread类中设置一个静态域。<br>
 * 并将这个处理器设置为默认的未捕获异常处理器。
 * <p>这个处理器只有在不存在线程专有的未捕获异常处理器的情况下才会被调用。
 * 系统会检查专有版本，如果没有发现，则检查线程组是否有其专有uncaughtException()方法，如果也没有
 * 再调用defaultUncaughtExceptionHandler。</p>
 * @author wx
 *
 */
public class SettingDefaultHandler {
	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread2());
	}
}
