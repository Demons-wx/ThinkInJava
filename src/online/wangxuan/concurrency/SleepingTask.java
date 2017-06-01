package online.wangxuan.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>对sleep()的调用可以抛出InterruptedException异常，并且可以看到，它在run()中被捕获。
 * 因为异常不能跨线程传播回main()，所以必须在本地处理所有在任务内部产生的异常。</p>
 * @author wx
 *
 */
public class SleepingTask extends LiftOff{
	public void run(){
		try {
			while(countDown-- > 0) {
				System.out.println(status());
				// old style
				// Thread.sleep(100);
				TimeUnit.MICROSECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
	} 
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new SleepingTask());
		}
		exec.shutdown();
	}
}
