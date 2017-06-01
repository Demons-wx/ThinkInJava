package online.wangxuan.holding.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


/**
 * 队列常被当作一种可靠的将对象从程序的某个区域传输到另一个区域的途径。<br>
 * 队列在并发编程中特别重要，因为它们可以安全的将对象从一个任务传输给另一个任务。
 * @author wx
 *
 */
public class QueueDemo {
	public static void printQ(Queue queue) {
		/* peek() 和 element() 在不移除的情况下返回队头 */
		while(queue.peek() != null) {
			System.out.print(queue.remove() + " ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<Integer>();
		Random rand = new Random(47);
		for (int i = 0; i < 10; i++) {
			/* offer，在允许的情况下，将一个元素插入到队尾，或者返回false */
			queue.offer(rand.nextInt(i + 10));
		}
		printQ(queue);
		Queue<Character> qc = new LinkedList<Character>();
		for (Character c : "Brontosaurus".toCharArray()) {
			qc.offer(c);
		}
		printQ(qc);
	}
}
