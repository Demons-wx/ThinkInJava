package online.wangxuan.concurrency.syncobject;

/**
 * synchronized块必须给定一个在其上进行同步的对象，并且最合理的方式是，使用其方法正在
 * 被调用的当前对象：synchronized(this)。<br>
 * 在这种方式中，如果获得了synchronized块上的锁，那么该对象其他的synchronized方法和临界区
 * 就不能被调用了。<br>
 * 有时必须在另一个对象上同步，如果要这么做，就必须确保所有相关的任务都在同一个对象上同步。<br>
 * <br>
 * 下面演示两个任务可以同时进入同一个对象，只要这个对象上的方法在不同的锁上同步的即可：
 * @author wx
 *
 */
public class SyncObject {
	public static void main(String[] args) {
		final DualSynch ds = new DualSynch();
		new Thread() {
			public void run() {
				ds.f();
			}
		}.start();
		ds.g();
	}
}

/**
 * 两个同步是相互独立的
 * @author wx
 *
 */
class DualSynch {
	private Object syncObject = new Object();
	/* f()通过this同步 */
	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			System.out.print("f()");
			Thread.yield();
		}
	}
	/* g()在一个syncObject上同步 */
	public void g() {
		synchronized(syncObject) {
			for (int i = 0; i < 5; i++) {
				System.out.print("g()");
				Thread.yield();
			}
		}
	}
}