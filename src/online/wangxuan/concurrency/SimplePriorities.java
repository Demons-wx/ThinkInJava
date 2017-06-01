package online.wangxuan.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>注意：在绝大多数情况下，所有的线程都应该以默认的优先级运行，试图操作优先级通常是一种错误。</p>
 * @author wx
 *
 */
public class SimplePriorities implements Runnable {

	private int countDown = 5;
	private volatile double d;
	private int priority;
	public SimplePriorities(int priority) {
		this.priority = priority;
	}
	public String toString(){
		return Thread.currentThread() + ":" +countDown;
	}
	/**
	 * 在run()中，执行了100000次开销很大的浮点运算，包括double类型的加法和除法，变量d是valatile的，以确保不进行任何编译器优化。<br>
	 * 如果没有加入这些运算的话，就看不到设置优先级的效果，有了这些运算，就能观察到优先级为MAX_PRIORITY的线程被线程调度器优先选择。
	 */
	public void run() {
		Thread.currentThread().setPriority(priority);
		while(true){
			// an expensive, interrupted operation
			for (int i = 0; i < 100000; i++) {
				d += (Math.PI + Math.E) / (double)i;
				if(i % 1000 ==0){
					Thread.yield();
				}
			}
			System.out.println(this); // 打印当前线程对象，调用被重写的toString()
			if(--countDown == 0) return;
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		}
		exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		exec.shutdown();
	}
}
