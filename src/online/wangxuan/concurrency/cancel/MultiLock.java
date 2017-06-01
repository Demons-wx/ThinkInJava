package online.wangxuan.concurrency.cancel;

/**
 * 下面示例说明同一个互斥可以如何被同一个任务多次获得
 * <p>一个任务应该能够调用在同一个对象中的其他的synchronized方法，而这个任务已经持有锁了。</p>
 * @author wx
 *
 */
public class MultiLock {
	public synchronized void f1(int count) {
		if(count-- > 0) {
			System.out.println("f1() calling f2() with count " + count);
			f2(count);
		}
	}
	public synchronized void f2(int count) {
		if(count-- > 0) {
			System.out.println("f2() calling f1() with count " + count);
			f1(count);
		}
	}
	/**
	 * f1()和f2()互相调用直至count变为0，由于这个任务已经在第一次对f1()的调用中获得了
	 * multiLock()对象锁，因此同一个任务将在对f2()的调用中再次获得这个锁。
	 * @param args
	 */
	public static void main(String[] args) {
		final MultiLock multiLock = new MultiLock();
		new Thread() {
			public void run() {
				multiLock.f1(10);
			}
		}.start();
	}
}
