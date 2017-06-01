package online.wangxuan.concurrency.daemon.util;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import online.wangxuan.concurrency.daemon.DaemonThreadFactory;

public class DaemonThreadPoolExecutor extends ThreadPoolExecutor {

	public DaemonThreadPoolExecutor() {
		super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, 
				new SynchronousQueue<Runnable>(), new DaemonThreadFactory());
	}
}
