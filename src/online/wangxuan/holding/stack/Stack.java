package online.wangxuan.holding.stack;

import java.util.LinkedList;

/**
 * LinkedList具有能够直接实现栈的所有功能的方法，因此可以直接将LinkedList作为栈使用。
 * @author wx
 *
 * @param <T>
 */
public class Stack<T> {
	private LinkedList<T> storage = new LinkedList<T>();
	public void push(T v) {
		storage.addFirst(v);
	}
	public T peek() {
		return storage.getFirst();
	}
	public T pop() {
		return storage.removeFirst();
	}
	public boolean empty() {
		return storage.isEmpty();
	}
	public String toString() {
		return storage.toString();
	}
}
