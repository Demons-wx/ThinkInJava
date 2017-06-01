package online.wangxuan.concurrency;
/**
 * <p>产生序列数</p>
 * 
 * java递增操作不是原子性的，并且涉及一个读操作和一个写操作，<br>
 * 所以即便是在这么简单的操作中，也为产生线程问题留下了空间。<br>
 * @author wx
 *
 */
public class SerialNumberGenerator {
	/**
	 * 在这里，易变性不是问题
	 */
	private static volatile int serialNumber = 0;
	/**
	 * 问题是nextSerialNumber()在没有同步的情况下对共享变量进行了访问。
	 * @return
	 */
	public static int nextSerialNumber() {
		return serialNumber++; // Not thread-safe
	}
}
