package online.wangxuan.concurrency;

/**
 * <p>对join方法的调用可以被中断，做法是在调用线程上调用interrupt()方法。</p>
 * 下面代码所表示的意思：<br>
 * Joiner线程中调用slepper.join()，此时joiner会被挂起，直到<br>
 * 1. slepper线程被interrupt() <br>
 * 2. slepper线程执行结束 <br>
 * 后才会继续执行。
 * 
 * @author wx
 *
 */
public class Joining {

	public static void main(String[] args) {
		Sleeper 
			sleepy = new Sleeper("Sleepy", 1500),
			grumpy = new Sleeper("Grumpy", 1500);
		Joiner 
			dopey = new Joiner("Dopey", sleepy),
			doc = new Joiner("Doc", grumpy);
		grumpy.interrupt();
	}
}

/**
 * Sleeper要休眠一段时间，这段时间是通过构造器传进来的参数。<br>
 * <p>在run()中，sleep()方法有可能在指定的时间期满时返回，但也可能被中断。
 * 当另一个线程在该线程上调用interrupt()时，将给该线程设定一个标志，表明该线程已被中断。
 * 然而，异常捕获时将清理这个标志，所以在catch子句中，在异常被捕获的时候这个标志总是为假。</p>
 * @author wx
 *
 */
class Sleeper extends Thread {
	private int duration;
	public Sleeper(String name, int sleepTime) {
		super(name);
		duration = sleepTime;
		start();
	}
	
	public void run() {
		try {
			sleep(duration);
		} catch (InterruptedException e) {
			System.out.println(getName() + " was interrupted. " + 
					"is interrupted: " + isInterrupted());
			return;
		}
		System.out.println(getName() + " has awakened");
	}
}

/**
 * <p>一个线程在另一个线程t上调用t.join(),此线程将被挂起,直到目标线程t结束才恢复。</p>
 * @author wx
 *
 */
class Joiner extends Thread {
	private Sleeper sleeper;
	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}
	public void run(){
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
		System.out.println(getName() + " join completed");
	}
}























