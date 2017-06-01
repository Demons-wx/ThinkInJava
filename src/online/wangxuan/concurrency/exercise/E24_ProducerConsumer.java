package online.wangxuan.concurrency.exercise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 使用wait()和notifyAll()解决单个生产者消费问题。<br>
 * 存在生产者比消费者速度快或者消费者比生产者速度快的可能性。
 * @author wx
 *
 */
public class E24_ProducerConsumer {
	public static void main(String[] args) throws InterruptedException {
		if(args.length < 2) {
			System.err.println("Usage java E24_ProducerConsumer" + 
					"<producer sleep time> <consumer sleep time>");
			System.exit(1);
		}
		int producerSleep = Integer.parseInt(args[0]);
		int consumerSleep = Integer.parseInt(args[1]);
		FlowQueue<Item> fq = new FlowQueue<Item>(100);
		ExecutorService exec = Executors.newFixedThreadPool(2);
		exec.execute(new Producer(fq, producerSleep));
		exec.execute(new Consumer(fq, consumerSleep));
		TimeUnit.SECONDS.sleep(2);
		exec.shutdownNow();
	}
}

/**
 * A queue for solving flow-control(流量控制) problems
 * @author wx
 *
 * @param <T>
 */
class FlowQueue<T> {
	private Queue<T> queue = new LinkedList<T>();
	private int maxSize;
	public FlowQueue(int maxSize) {
		this.maxSize = maxSize;
	}
	public synchronized void put(T v) throws InterruptedException {
		while(queue.size() >= maxSize) {
			wait();
		}
		/* 如果可以在不违反容量限制的情况下立即将指定的元素插入此队列 */
		queue.offer(v);
		maxSize++;
		notifyAll();
	}
	public synchronized T get() throws InterruptedException {
		while(queue.isEmpty()) {
			wait();
		}
		T returnVal = queue.poll();
		maxSize--;
		notifyAll();
		return returnVal;
	}
}

class Item {
	private static int counter;
	private int id = counter++;
	public String toString() {
		return "Item " + id;
	}
}

// produce Item objects
class Producer implements Runnable {
	private int delay;
	private FlowQueue<Item> output;
	public Producer(FlowQueue<Item> output, int sleepTime) {
		this.output = output;
		delay = sleepTime;
	}
	public void run() {
		for(;;) {
			try {
				output.put(new Item()); // 如果没有Item,释放锁
				TimeUnit.MILLISECONDS.sleep(delay); // 交出执行权
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}

// Consumes any object
class Consumer implements Runnable {
	private int delay;
	private FlowQueue<?> input;
	public Consumer(FlowQueue<?> input, int sleepTime) {
		this.input = input;
		delay = sleepTime;
	}
	public void run() {
		for(;;) {
			try {
				System.out.println(input.get());
				TimeUnit.MILLISECONDS.sleep(delay);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}


















