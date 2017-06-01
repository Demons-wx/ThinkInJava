package online.wangxuan.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可以使用Executor来代替MoreBasicThreads.java中显示的创建Thread对象。<br>
 * ExecutorService是具有生命周期的Executor。<br>
 * 对shutdown()方法的调用可以防止新任务被提交给这个Executor。
 * 
 * <p>CachedThreadPool在程序执行过程中通常会创建与所需数量相同的线程，
 * 然后它回收旧线程时，停止创建新线程。因此它是合理的Executor首选。</p>
 * @author wx
 *
 */
public class CachedThreadPool {
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}
}
