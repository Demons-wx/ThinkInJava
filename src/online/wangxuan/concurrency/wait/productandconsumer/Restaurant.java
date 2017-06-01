package online.wangxuan.concurrency.wait.productandconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import net.mindview.util.Print;

/**
 * 考虑这样一个饭店，它有一个厨师和一个服务员。<br>
 * 这个服务员必须等待厨师准备好膳食。当厨师准备好时，他会通知服务员，之后服务员上菜，<br>
 * 然后返回继续等待。<br>
 * <br>
 * 这是一个任务协作的示例：厨师代表生产者，服务员代表消费者。两个任务必须在膳食被生产和消费 <br>
 * 时进行握手，而系统必须以有序的方式关闭。
 * @author wx
 *
 */
public class Restaurant {
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);
	public Restaurant() {
		exec.execute(chef);
		exec.execute(waitPerson);
	}
	public static void main(String[] args) {
		new Restaurant();
	}
}

class Meal {
	private final int orderNum;
	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}
	public String toString() {
		return "Meal " + orderNum;
	}
}

class WaitPerson implements Runnable {
	private Restaurant restaurant;
	public WaitPerson(Restaurant r) {
		restaurant = r;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				/* 当前只有一个任务将在WaitPerson的锁上等待，即WaitPerson任务自身，
				 * 出于这个原因，理论上可以调用notify()而不是notifyAll()。 */
				synchronized (this) { 
					while(restaurant.meal == null) {
						wait();
					}
				}
				Print.print("Waitperson got " + restaurant.meal);
				synchronized (restaurant.chef) { // 通知chef烧制下一份meal
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			Print.print("WaitPerson interrupted");
		}
	}
}

class Chef implements Runnable {
	private Restaurant restaurant;
	private int count = 0;
	public Chef(Restaurant r) {
		restaurant = r;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					while(restaurant.meal != null) {
						wait();
					}
				}
				if(++count == 10) {
					Print.print("Out of food, closing");
					restaurant.exec.shutdownNow();
				}
				/**
				 * 一旦chef送上Meal并通知WaitPerson，这个Chef就将等待，直至
				 * waitPerson收集到订单并通知chef，之后chef就可以烧制下一份Meal了。
				 */
				Print.printnb("Order up! ");
				synchronized (restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			Print.print("Chef interrupted");
		}
	}
}





















