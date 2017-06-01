package online.wangxuan.containers.queue;

import net.mindview.util.Print;

/**
 * 双端队列测试
 * @author wx
 *
 */
public class DequeTest {
	static void fillTest(Deque<Integer> deque) {
		for (int i = 20; i < 27; i++) {
			deque.addFirst(i);
		}
		for (int i = 50; i < 55; i++) {
			deque.addLast(i);
		}
	}
	public static void main(String[] args) {
		Deque<Integer> di = new Deque<Integer>();
		fillTest(di);
		Print.print(di);
		while(di.size() != 0) {
			Print.printnb(di.removeFirst() + " ");
		}
		Print.print();
		fillTest(di);
		while(di.size() != 0) {
			Print.printnb(di.removeLast() + " ");
		}
	}
}
