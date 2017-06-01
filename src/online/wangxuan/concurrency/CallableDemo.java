package online.wangxuan.concurrency;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>从任务中产生返回值</p>
 * 
 * 实现Callable接口
 * @author wx
 *
 */
class TaskWithResult implements Callable<String> {
	private final int id;
	public TaskWithResult(int id) {
		this.id = id;
	}
	@Override
	public String call() {
		return "result of TaskWithResult " + id;
	}
}

/**
 * submit()方法会产生Future对象，它用Callable返回结果的特定类型进行了参数化。<br>
 * 你可以用isDone()方法来查询Future是否已经完成，当任务完成时，它具有一个结果，你可以
 * 调用get()方法来获取该结果。也可以不用isDone()进行检查就直接调用get()，在这种情况下，
 * get()将阻塞，直至结果准备就绪。
 *
 */
public class CallableDemo {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();
		for (int i = 0; i < 10; i++) {
			results.add(exec.submit(new TaskWithResult(i)));
		}
		for (Future<String> fs : results) {
			try {
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				System.out.println(e);
				return;
			} catch (ExecutionException e) {
				System.out.println(e);
			} finally {
				exec.shutdown();
			}
		}
	}
}
