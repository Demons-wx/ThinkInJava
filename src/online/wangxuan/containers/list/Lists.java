package online.wangxuan.containers.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import net.mindview.util.Print;

import online.wangxuan.containers.util.Countries;



public class Lists {
	private static boolean b;
	private static String s;
	private static int i;
	private static Iterator<String> it;
	private static ListIterator<String> lit;
	/* 每个List都可以执行的操作 */
	public static void basicTest(List<String> a) {
		a.add(1, "x");
		a.add("x");
		a.addAll(Countries.names(25));
		a.addAll(3, Countries.names(25));
		b = a.contains("1");
		b = a.containsAll(Countries.names(25));
		// 随机访问
		s = a.get(1);
		i = a.indexOf("1");
		b = a.isEmpty();
		it = a.iterator();
		lit = a.listIterator();
		// 从位置3开始
		lit = a.listIterator(3);
		i = a.lastIndexOf("1");
		a.remove(1);
		a.remove("3");
		a.set(1, "y");
		a.retainAll(Countries.names(25));
		a.removeAll(Countries.names(25));
		i = a.size();
		a.clear();
	}
	/* 使用Iterator遍历元素 */
	public static void iterMotion(List<String> a) {
		ListIterator<String> it = a.listIterator();
		b = it.hasNext();
		b = it.hasPrevious();
		s = it.next();
		i = it.nextIndex();
		s = it.previous();
		i = it.previousIndex();
	}
	/* 使用Iterator修改元素 */
	public static void iterManipulation(List<String> a) {
		ListIterator<String> it = a.listIterator();
		it.add("47");
		// must move to an element after add()
		it.next();
		// remove the element after the newly produced one
		it.remove();
		// must move to an element after remove()
		it.next();
		// change the element after the deleted one
		it.set("47");
	}
	/* 用以查看list的操作效果 */
	public static void testVisual(List<String> a) {
		Print.print(a);
		List<String> b = Countries.names(25);
		Print.print("b = " + b);
		a.addAll(b);
		a.addAll(b);
		Print.print(a);
		// 使用ListIterator插入、移除和替换元素
		ListIterator<String> x = a.listIterator(a.size() / 2);
		x.add("one");
		Print.print(a);
		Print.print(x.next());
		x.set("47");
		Print.print(a);
		// 反转
		x = a.listIterator(a.size());
		while(x.hasPrevious()) {
			Print.printnb(x.previous() + " ");
		}
		Print.print();
		Print.print("testVisual finished");
	}
	/* LinkedList独有的方法 */
	public static void testLinkedList() {
		LinkedList<String> ll = new LinkedList<String>();
		ll.addAll(Countries.names(25));
		Print.print(ll);
		// 类似于Stack的push
		ll.addFirst("one");
		ll.addFirst("two");
		Print.print(ll);
		// 类似于Stack的peek
		Print.print(ll.getFirst());
		// 类似于Stack的pop
		Print.print(ll.removeFirst());
		// 类似于队列操作
		Print.print(ll.removeLast());
		Print.print(ll);
	}
	public static void main(String[] args) {
		basicTest(new LinkedList<String>(Countries.names(25)));
		basicTest(new ArrayList<String>(Countries.names(25)));
		iterMotion(new LinkedList<String>(Countries.names(25)));
		iterMotion(new ArrayList<String>(Countries.names(25)));
		iterManipulation(new LinkedList<String>(Countries.names(25)));
		iterManipulation(new ArrayList<String>(Countries.names(25)));
		testVisual(new LinkedList<String>(Countries.names(25)));
		testLinkedList();
	}
}
