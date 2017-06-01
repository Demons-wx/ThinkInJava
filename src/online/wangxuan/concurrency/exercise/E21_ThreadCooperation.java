package online.wangxuan.concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import net.mindview.util.Print;

/**
 * 创建两个Runnable，其中一个run()方法启动并调用wait(), <br>
 * 而第二个类应该捕获第一个Runnable对象的引用，其run()方法应该在一定的 <br>
 * 秒数之后，为第一个任务调用notifyAll()，从而使得第一个任务显示一条信息。
 * @author wx
 *
 */
public class E21_ThreadCooperation {
	public static void main(String[] args) {
		Runnable coop1 = new Coop1(),
				 coop2 = new Coop2(coop1);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(coop1);
		exec.execute(coop2);
		Thread.yield();
		exec.shutdown();
	}
}

class Coop1 implements Runnable {
	public Coop1() {
		Print.print("Constructed Coop1");
	}
	public void run() {
		Print.print("Coop1 going into wait");
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Print.print("Coop1 exited wait");
	}
}

class Coop2 implements Runnable {
	Runnable otherTask;
	public Coop2(Runnable otherTask) {
		this.otherTask = otherTask;
		Print.print("Constructed Coop2");
	}
	public void run() {
		Print.print("Coop2 pausing 5 seconds");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Print.print("Coop2 calling notifyAll");
		synchronized (otherTask) {
			otherTask.notifyAll();
		}
	}
}



















