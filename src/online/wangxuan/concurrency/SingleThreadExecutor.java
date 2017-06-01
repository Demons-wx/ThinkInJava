package online.wangxuan.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>SingleThreadExecutor就像是线程数量为1的FixedThreadPool</p>
 * 如果向SingleThreadExecutor提交了多个任务，那么这些任务将排队，每个任务都会在下一个任务开始
 * 之前运行结束，所有的任务将使用相同的线程。并且是在下一个任务开始之前完成的。
 * @author wx
 *
 */
public class SingleThreadExecutor {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}
}
