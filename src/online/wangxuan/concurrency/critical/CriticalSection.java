package online.wangxuan.concurrency.critical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>将变量x和y维护成相同的值</p>
 * 
 * 自增操作不是线程安全的，并且没有任何方法被标记为synchronized，<br>
 * 所以不能保证一个非线程安全的Pair对象在多线程程序中不被破坏。
 * @author wx
 *
 */
class Pair { // Not thread-safe
	private int x, y;
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Pair() {
		this(0, 0);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void incrementX() {
		x++;
	}
	public void incrementY() {
		y++;
	}
	public String toString() {
		return "x: " + x + ", y: " + y;
	}
	public class PairVluesNotEqualException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public PairVluesNotEqualException() {
			super("Pair values not equal: " + Pair.this);
		}
	}
	public void checkState() {
		if(x != y) {
			throw new PairVluesNotEqualException();
		}
	}
}

/**
 * <p>Protect a Pair inside a thread-safe class</p>
 * PairManager类持有一个Pair对象并控制对它的一切访问。<br>
 * 
 * 
 * 注意：synchronized关键字不属于方法特征签名的组成部分，所以可以在覆盖方法中加上去。
 * @author wx
 *
 */
abstract class PairManager {
	AtomicInteger checkCounter = new AtomicInteger(0);
	protected Pair p = new Pair();
	private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());
	// Make a copy to keep the original safe;
	public synchronized Pair getPair() {
		return new Pair(p.getX(), p.getY());
	}
	// Assume this is a time consuming operation
	protected void store(Pair p) {
		storage.add(p);
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException ignore) {}
	}
	public abstract void increment();
}

/**
 * Synchronized the entire method
 * @author wx
 *
 */
class PairManager1 extends PairManager {
	public synchronized void increment() {
		p.incrementX();
		p.incrementY();
		store(getPair());
	}
}

/**
 * use a critical section
 * @author wx
 *
 */
class PairManager2 extends PairManager {
	public void increment() {
		Pair temp;
		synchronized(this) {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		}
		store(temp);
	}
}

/**
 * <p>用来测试两种不同类型的PairManager</p>
 * @author wx
 *
 */
class PairManipulator implements Runnable {
	private PairManager pm;
	public PairManipulator(PairManager pm) {
		this.pm = pm;
	}
	public void run() {
		while(true) {
			pm.increment();
		}
	}
	public String toString() {
		return "Pair: " + pm.getPair() + " checkCounter = " + pm.checkCounter.get();
	}
}

/**
 * 为了跟踪可以运行测试的频度，PairChecker在每次成功时都递增checkCounter。
 * @author wx
 *
 */
class PairChecker implements Runnable {
	private PairManager pm;
	public PairChecker(PairManager pm) {
		this.pm = pm;
	}
	public void run() {
		while(true) {
			pm.checkCounter.incrementAndGet();
			pm.getPair().checkState();
		}
	}
}

/**
 * 结论：<br>
 * 一般来说，对于PairChecker的检查频率，PairManager1.increment()不允许有<br>
 * PairManager2.increment()那样多。后者采用同步控制块进行同步，所以对象不加锁的时间更长。<br>
 * 这也是宁愿使用同步控制块而不是对整个方法进行同步控制的原因，使得其他线程能更多地访问。
 * @author wx
 *
 */
public class CriticalSection {
	static void testApproaches(PairManager pman1, PairManager pman2) {
		/* 开启四个线程，加上main 一共5个线程抢CPU资源 */
		ExecutorService exec = Executors.newCachedThreadPool();
		PairManipulator 
			pm1 = new PairManipulator(pman1),
			pm2 = new PairManipulator(pman2);
		PairChecker 
			pcheck1 = new PairChecker(pman1),
			pcheck2 = new PairChecker(pman2);
		exec.execute(pm1);
		exec.execute(pm2);
		exec.execute(pcheck1);
		exec.execute(pcheck2);
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("Sleep Interrupted");
		}
		System.out.println("pm1: " + pm1 + "\npm2: " +pm2);
		System.exit(0);
	}
	public static void main(String[] args) {
		PairManager
			pman1 = new PairManager1(),
			pman2 = new PairManager2();
		testApproaches(pman1, pman2);
	}
}


























