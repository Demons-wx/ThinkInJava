package online.wangxuan.concurrency.critical;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 使用Lock对象创建临界区
 * <p>对于ExplicitPairManager2的increment()方法线程出错的看法：</p>
 * 1. ExplicitPairManager1，PairManager1，PairManager2中的increment()方法都被synchronized关键字标记；<br>
 * 2. 在本例中，无论是同步方法还是同步控制块，锁的都是PairManager的对象；<br>
 * 3. 由synchronized概念可知：要控制对共享资源的访问，得先把它包装进一个对象。
 *    然后把所有要访问这个资源的方法标记为synchronized。
 * 	    如果某个任务处于一个对标记为synchronized的方法的调用中，那么这个线程从该方法返回之前，
 *    其他所有要调用类中任何标记为synchronized的方法的线程都会被阻塞。<br>
 * 4. 所以线程在进行ExplicitPairManager2中的increment()方法时，
 *    并不会影响PairManager中的另一个synchronized方法getPair()获得锁，
 *    因此，getPair().checkState()方法可能读取到共享资源p.x和p.y非同步状态的值。
 * @author wx
 *
 */
public class ExplicitCriticalSection {
	public static void main(String[] args) {
		PairManager 
			pman1 = new ExplicitPairManager1(),
			pman2 = new ExplicitPairManager2();
		CriticalSection.testApproaches(pman1, pman2);
	}
}

/**
 * 锁住整个方法
 * @author wx
 *
 */
class ExplicitPairManager1 extends PairManager {
	private Lock lock = new ReentrantLock();
	public synchronized void increment() {
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			store(getPair());
		} finally {
			lock.unlock();
		}
	}
}

/**
 * 锁住临界区
 * @author wx
 *
 */
class ExplicitPairManager2 extends PairManager {
	private Lock lock = new ReentrantLock();
	public void increment() {
		Pair temp;
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		} finally {
			lock.unlock();
		}
		store(temp);
	}
}























