package online.wangxuan.concurrency.cancel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>花园参观者的主计数值</p>
 * @author wx
 *
 */
class Count {
	private int count = 0;
	private Random rand = new Random(47);
	public synchronized int increment() {
		int temp = count;
		/* 在从count读取到temp到递增temp存回count，有大约一半的时间产生让步 */
		if(rand.nextBoolean()) {
			Thread.yield();
		}
		return (count = ++temp);
	}
	public synchronized int value() {
		return count;
	}
}

/**
 * 入口，包含通过某个特定入口的参观者数量
 * @author wx
 *
 */
class Entrance implements Runnable {
	private static Count count = new Count();
	private static List<Entrance> entrances = new ArrayList<Entrance>();
	private int number = 0;
	private final int id;
	private static volatile boolean canceled = false;
	// Atomic operation on a volatile field
	public static void cancel() {
		canceled = true;
	}
	public Entrance(int id) {
		this.id = id;
		// keep this task in a list, Also prevents garbage collection of dead tasks
		entrances.add(this);
	}
	public void run() {
		while(!canceled) {
			synchronized(this) {
				++number;
			}
			System.out.println(this + " Total: " + count.increment());
			try {
				/* 当线程处于阻塞状态时，调度器将忽略线程，不会分配给线程任何CPU时间。
				 * 直到线程重新进入了就绪状态，它才有可能执行操作。 */
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("sleep interrupted");
			}
		}
		System.out.println("Stopping " + this);
	}
	public synchronized int getValue() {
		return number;
	}
	public String toString() {
		return "Entrance " + id + ": " + getValue();
	}
	public static int getTotalCount() {
		return count.value();
	}
	public static int sumEntrances() {
		/*int sum = 0;
		for (Entrance entrance : entrances) {
			sum += entrance.getValue();
		}
		return sum;*/
		// java8 lambda
		return entrances.stream().map(entrance -> entrance.getValue()).reduce(0, (acc, element) -> acc + element);
	}
}

public class OrnamentalGarden {
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++) {
			exec.execute(new Entrance(i));
		}
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		exec.shutdown();
		/* awaitTermination()等待每个任务结束，如果所有任务在超时范围内全部结束，返回true */
		if(!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
			System.out.print("Some tasks were not terminated!");
		}
		System.out.println("Total: " + Entrance.getTotalCount());
		System.out.println("Sum of Entrances: " + Entrance.sumEntrances());
	}
}
























