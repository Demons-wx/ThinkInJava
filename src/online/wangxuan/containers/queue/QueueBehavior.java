package online.wangxuan.containers.queue;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import net.mindview.util.Generator;

public class QueueBehavior {
	private static int count = 10;
	static <T> void test(Queue<T> queue, Generator<T> gen) {
		for (int i = 0; i < count; i++) {
			queue.offer(gen.next());
		}
		while(queue.peek() != null) {
			System.out.print(queue.remove() + " ");
		}
		System.out.println();
	}
	static class Gen implements Generator<String> {
		String[] s = ("one tow three four five six seven eight nine ten".split(" "));
		int i;
		public String next() {
			return s[i++];
		}
	}
	public static void main(String[] args) {
		test(new LinkedList<String>(), new Gen());
		test(new PriorityQueue<String>(), new Gen());
		test(new ArrayBlockingQueue<String>(10), new Gen());
		test(new ConcurrentLinkedQueue<String>(), new Gen());
		test(new LinkedBlockingQueue<String>(), new Gen());
		test(new PriorityBlockingQueue<String>(), new Gen());
	}
}
