package online.wangxuan.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * <p>异常处理</p>
 * 在任务中抛出的异常，在main方法中捕获是无效的。
 * @author wx
 *
 */
public class ExceptionThread implements Runnable {

	public void run(){
		throw new RuntimeException();
	}
	
	public static void main(String[] args) {
		try {
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());
		} catch (RuntimeException e) {
			System.out.println("Exception has been handled!");
		}
	}
}
