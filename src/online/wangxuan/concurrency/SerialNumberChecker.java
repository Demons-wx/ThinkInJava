package online.wangxuan.concurrency;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * <p>证明i++操作不具有原子性</p>
 * 
 * 步骤：<br>
 * 1. 维护一个CircularSet，保存所有的序数； <br>
 * 2. 开启多个线程，产生序列数 SerialNumberGenerator.nextSerialNumber()；<br>
 * <br>
 * 结果分析：<br>
 * 如果产生的序列数在CircularSet中出现，则重复，即说明i++非原子操作<br>
 * 如果产生的序列数没有在CircularSet中出现，则加入CircularSet中<br>
 * <br>
 * 备注：add()操作和contain()操作都是加了锁的。
 * @author wx
 *
 */
public class SerialNumberChecker {
	private static final int SIZE = 10;
	private static CircularSet serials = new CircularSet(1000);
	private static ExecutorService exec = Executors.newCachedThreadPool();
	static class SerialChecker implements Runnable {
		public void run() {
			while(true) {
				int serial = SerialNumberGenerator.nextSerialNumber(); // 多线程读写会产生错误
				if(serials.contains(serial)) {
					System.out.println("Duplicate: " + serial);
					System.exit(0);
				}
				serials.add(serial);
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, InterruptedException {
		for (int i = 0; i < SIZE; i++) {
			exec.execute(new SerialChecker());
		}
		if(args.length > 0) {
			TimeUnit.SECONDS.sleep(new Integer(args[0]));
			System.out.println("No duplicates detected");
			System.exit(0);
		}
	}
}
/**
 * <p>持有所产生的序列数</p>
 * @author wx
 *
 */
class CircularSet {
	private int[] array;
	private int len;
	private int index = 0;
	public CircularSet(int size) {
		array = new int[size];
		len = size;
		for (int i = 0; i < size; i++) {
			array[i] = -1;
		}
	}
	// 添加操作，当产生的序列数多于size之后，就覆盖前面的
	public synchronized void add(int i) {
		array[index] = i;
		index = ++index % len;
	}
	public synchronized boolean contains(int val) {
		for (int i = 0; i < len; i++) {
			if(array[i] == val) {
				return true;
			}
		}
		return false;
	}
}
