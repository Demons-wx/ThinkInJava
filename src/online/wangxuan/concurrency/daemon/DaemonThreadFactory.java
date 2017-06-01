package online.wangxuan.concurrency.daemon;

import java.util.concurrent.ThreadFactory;
/**
 * <p>可以将DaemonThreadFactory作为参数传递给Eexcutor.newCachedThreadPool()</p>
 * @author wx
 *
 */
public class DaemonThreadFactory implements ThreadFactory {

	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}
}
