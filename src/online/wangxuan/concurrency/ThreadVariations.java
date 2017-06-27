package online.wangxuan.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * <p>有时候通过使用内部类将线程代码隐藏在类中将很有用。</p>
 * @author wx
 *
 */
public class ThreadVariations {

	public static void main(String[] args) {
		new InnerThread1("InnerThread1");
		new InnerThread2("InnerThread2");
		new InnerRunnable1("InnerRunnable1");
		new InnerRunnable2("InnerRunnable2");
		new ThreadMethod("ThreadMethod").runTask();
	}
}

// Using a named inner class;
class InnerThread1 {
	private int countDown = 5;
	private Inner inner;
	private class Inner extends Thread {
		public Inner(String name) {
			super(name);
			start();
		}
		public void run(){
			try {
				while(true){
					System.out.println(this);
					if(--countDown == 0) return;
					sleep(10);
				}
			} catch (InterruptedException e) {
				System.out.println("sleep() interrupted");
			}
		}
		public String toString(){
			return getName() + ": " + countDown;
		}
	}
	public InnerThread1(String name){
		inner = new Inner(name);
	}
}

// Using a anonymous inner class;
class InnerThread2 {
	private int countDown = 5;
	private Thread t;
	public InnerThread2(String name){
		t = new Thread(name) {
			public void run() {
				try {
					while(true){
						System.out.println(this);
						if(--countDown == 0) return;
						sleep(10);
					}
				} catch (InterruptedException e) {
					System.out.println("sleep() interrupted");
				}
			}
			public String toString(){
				return getName() + ": " + countDown;
			}
		};
		t.start();
	}
}

// Using a named Runnable immplementation
class InnerRunnable1 {
	private int countDown = 5;
	private Inner inner;
	
	private class Inner implements Runnable {
		Thread t;
		Inner(String name) {
			t = new Thread(this, name);
			t.start();
		}
		public void run(){
			try {
				while(true){
					System.out.println(this);
					if(--countDown == 0) return;
					TimeUnit.MICROSECONDS.sleep(10);
				}
			} catch (InterruptedException e) {
				System.out.println("sleep() interrupted");
			}
		}
		public String toString(){
			return t.getName() + ": " + countDown;
		} 
	}
	
	public InnerRunnable1(String name) {
		inner = new Inner(name);
	}
}

// Using an anonymous Runnable implementation
class InnerRunnable2 {
	private int countDown = 5;
	private Thread t;
	public InnerRunnable2(String name){
		t = new Thread(new Runnable() {
			public void run() {
				try {
					while(true){
						System.out.println(this);
						if(--countDown == 0) return;
						TimeUnit.MILLISECONDS.sleep(10);
					}
				} catch (InterruptedException e) {
					System.out.println("sleep() interrupted");
				}
			}
			public String toString() {
				return Thread.currentThread().getName() + ": " +countDown;
			}
		}, name);
		t.start();
	}
}


// lambda形式
class InnerRunnable3 {
    private int countDown = 5;
    private Thread t;
    public InnerRunnable3(String name) {
        t = new Thread(() -> {
            try {
                while (true) {
                    System.out.println(this);
                    if (--countDown == 0) return;
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println("sleep() interrupted");
            }
        }, name);
        t.start();
    }
}


/**
 * <p>ThreadMethod展示了在方法内部如何创建线程</p>
 * 当你准备好运行线程时，就可以调用这个方法，而在线程开始之后，该方法将返回。
 * 如果该线程只执行辅助操作，而不是该类的重要操作，那么这与该类的构造器内部启动线程相比，可能是一种更加有用而合理的方式。
 */
class ThreadMethod {
	private int countDown = 5;
	private Thread t;
	private String name;
	public ThreadMethod(String name) {
		this.name = name;
	}
	public void runTask() {
		if(t == null){
			t = new Thread(name) {
				public void run(){
					try {
						while(true) {
							System.out.println(this);
							if(--countDown == 0) return;
							sleep(10);
						}
					} catch (InterruptedException e) {
						System.out.println("sleep() interrupted");
					}
				}
				public String toString(){
					return getName() + ": " + countDown;
				}
			};
			t.start();
		}
	}
}



















