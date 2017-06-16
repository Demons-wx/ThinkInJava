package online.wangxuan.containers.chosingcontainers;

import java.util.*;

import net.mindview.util.CountingGenerator;
import net.mindview.util.Generated;

import online.wangxuan.containers.util.CountingIntegerList;

/**
 * 
 * @author wx
 *
 */
public class ListPerformance {
	static Random rand = new Random();
	static int reps = 1000;
	static List<Test<List<Integer>>> tests = new ArrayList<Test<List<Integer>>>();
	static List<Test<LinkedList<Integer>>> qTests = new ArrayList<Test<LinkedList<Integer>>>();
	
	static {
		tests.add(new Test<List<Integer>>("add") {
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int listSize = tp.size;
				for (int i = 0; i < loops; i++) {
					list.clear();
					for (int j = 0; j < listSize; j++) {
						list.add(j);
					}
				}
				return loops * listSize;
			}
		});
		tests.add(new Test<List<Integer>>("get") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = list.size();
				for (int i = 0; i < loops; i++) {
					list.get(rand.nextInt(listSize));
				}
				return loops;
			}
		});
		tests.add(new Test<List<Integer>>("set") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = list.size();
				for (int i = 0; i < loops; i++) {
					list.set(rand.nextInt(listSize), 47);
				}
				return loops;
			}
		});
		tests.add(new Test<List<Integer>>("iteradd") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				final int LOOPS = 1000000;
				int half = list.size() / 2;
				ListIterator<Integer> it = list.listIterator(half);
				for (int i = 0; i < LOOPS; i++) {
					it.add(47);
				}
				return LOOPS;
			}
		});
		tests.add(new Test<List<Integer>>("insert") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops;
				for (int i = 0; i < loops; i++) {
					list.add(5, 47);
				}
				return loops;
			}
		});
		tests.add(new Test<List<Integer>>("remove") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for (int i = 0; i < loops; i++) {
					list.clear();
					list.addAll(new CountingIntegerList(size));
					while (list.size() > 5)
						list.remove(5);
				}
				return loops * size;
			}
		});
		// qTest
		qTests.add(new Test<LinkedList<Integer>>("addFirst") {
			@Override
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for (int i = 0; i < loops; i++) {
					list.clear();
					for (int j = 0; j < size; j++)
						list.addFirst(47);
				}
				return loops * size;
			}
		});
		qTests.add(new Test<LinkedList<Integer>>("addLast") {
			@Override
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for (int i = 0; i < loops; i++) {
					list.clear();
					for (int j = 0; j < size; j++)
						list.addLast(47);
				}
				return loops * size;
			}
		});
		qTests.add(new Test<LinkedList<Integer>>("rmFirst") {
			@Override
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for (int i = 0; i < loops; i++) {
					list.clear();
					list.addAll(new CountingIntegerList(size));
					while (list.size() > 0)
						list.removeFirst();
				}
				return loops * size;
			}
		});
		qTests.add(new Test<LinkedList<Integer>>("rmLast") {
			@Override
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for (int i = 0; i < loops; i++) {
					list.clear();
					list.addAll(new CountingIntegerList(size));
					while (list.size() > 0)
						list.removeLast();
				}
				return loops * size;
			}
		});
	}
	
	static class ListTester extends Tester<List<Integer>> {
		/* 父类没有指定默认的构造方法，子类需要显式声明 */
		public ListTester(List<Integer> container, List<Test<List<Integer>>> tests) {
			super(container, tests);
		}
		@Override
		protected List<Integer> initialize(int size) {
			container.clear();
			container.addAll(new CountingIntegerList(size));
			return container;
		}
		public static void run(List<Integer> list, List<Test<List<Integer>>> tests) {
			new ListTester(list, tests).timeTest();
		}
	}
	
	public static void main(String[] args) {
		if(args.length > 0) {
			Tester.defaultParams = TestParam.array(args);
		}
		// 对于array只能做下面两个测试
		Tester<List<Integer>> arrayTest = new Tester<List<Integer>>(null, tests.subList(1, 3)) {
			@Override
			protected List<Integer> initialize(int size) {
				Integer[] ia = Generated.array(Integer.class, new CountingGenerator.Integer(), size);
				return Arrays.asList(ia);
			}
		};
		arrayTest.setHeadline("Array as List");
		arrayTest.timeTest();

		Tester.defaultParams = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
		if(args.length > 0) {
			Tester.defaultParams = TestParam.array(args);
		}

		ListTester.run(new ArrayList<Integer>(), tests);
		ListTester.run(new LinkedList<Integer>(), tests);
		ListTester.run(new Vector<Integer>(), tests);

		Tester.fieldWidth = 12;
		Tester<LinkedList<Integer>> qTest = new Tester<LinkedList<Integer>>(new LinkedList<Integer>(), qTests);
		qTest.setHeadline("Queue tests");
		qTest.timeTest();
	}
}
