package online.wangxuan.containers.chosingcontainers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

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
	}
	
	static class ListTester extends Tester<List<Integer>> {
		/* 父类没有指定默认的构造方法，子类需要显式声明 */
		public ListTester(List<Integer> container, List<Test<List<Integer>>> tests) {
			super(container, tests);
		}
		@Override protected List<Integer> initialize(int size) {
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
		Tester<List<Integer>> arrayTest = new Tester<List<Integer>>(null, tests.subList(1, 3)) {
			/*  */
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
	}
}
