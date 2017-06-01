package online.wangxuan.concurrency.cancel;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import online.wangxuan.concurrency.cancel.IOBlocked;

/**
 * 在Interrupting中，cancel()不能中断试图获取synchronized锁或者试图执行 <br>
 * I/O操作的线程。这意味着I/O具有锁住你的多线程程序的潜在可能。
 * 
 * <p>对付这类问题有一种笨拙但有效的方法，就是关闭任务在其上发生阻塞的底层资源</p>
 * @author wx
 *
 */
public class CloseResource {
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8088);
		InputStream socketInput = new Socket("localhost", 8088).getInputStream();
		exec.execute(new IOBlocked(socketInput));
		exec.execute(new IOBlocked(System.in));
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("shutting down all threads");
		exec.shutdownNow();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Closing " + socketInput.getClass().getName());
		socketInput.close();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Closing " + System.in.getClass().getName());
		System.in.close();
	}
}
