package online.wangxuan.holding.queue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

/**
 * 优先队列声明下一个弹出元素是最需要的元素(具有最高优先级)。 <br>
 * 如果构建了一个消息系统，某些消息比其他消息更重要，因而应该更快的得到处理，<br>
 * 那么她们何时得到处理就与它们何时到达无关。<br><br>
 * PriorityQueue添加到Java SE5，是为了提供这种行为的一种自动实现。<br><br>
 * 
 * 当你在PriorityQueue上调用offer()方法插入一个对象时，这个对象会在队列中被排序。<br>
 * 默认使用对象在队列中的自然顺序，但是你可以通过提供自己的Comparator来修改这个顺序。<br>
 * PriorityQueue可以确保在你调用peek()、pool()和remove()方法时，获取的元素是队列中 <br>
 * 优先级最高的元素。
 * @author wx
 *
 */
public class PriorityQueueDemo {
	public static void main(String[] args) {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
		Random rand = new Random(47);
		for (int i = 0; i < 10; i++) {
			priorityQueue.offer(rand.nextInt(i + 10));
		}
		QueueDemo.printQ(priorityQueue);
		
		/* 顺序输出 
		 * 重复是允许的，最小值拥有最高优先级*/
		List<Integer> ints = Arrays.asList(25,22,20,
				18,14,9,3,1,1,2,3,9,14,18,21,23,25);
		priorityQueue = new PriorityQueue<Integer>(ints);
		QueueDemo.printQ(priorityQueue);
		
		/* 逆序输出 演示Comparator的使用*/
		priorityQueue = new PriorityQueue<Integer>(
				ints.size(), Collections.reverseOrder());
		priorityQueue.addAll(ints);
		QueueDemo.printQ(priorityQueue);
		
		/* String中，空格也算值，并且比字母优先级高 */
		String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
		List<String> strings = Arrays.asList(fact.split(""));
		PriorityQueue<String> stringPQ = new PriorityQueue<String>(strings);
		QueueDemo.printQ(stringPQ);
		
		stringPQ = new PriorityQueue<String>(
				strings.size(), Collections.reverseOrder());
		stringPQ.addAll(strings);
		QueueDemo.printQ(stringPQ);
		
		/* HashSet来消除重复元素 */
		Set<Character> charSet = new HashSet<Character>();
		for (char c : fact.toCharArray()) {
			charSet.add(c);
		}
		PriorityQueue<Character> characterPQ = new PriorityQueue<Character>(charSet);
		QueueDemo.printQ(characterPQ);
	}
}







