package online.wangxuan.containers.chosingcontainers;

import java.util.List;

/**
 * 为了使用这个框架，你需要将待测容器以及Test对象列表传递给Tester.run()方法。Tester.run()方法调用适当的
 * 重载构造器，然后调用timedTest()，它会执行针对该容器的列表中的每一个测试。 <br><br>
 * 
 * timeTest()会使用paramList中的每个TestParam对象进行重复测试。因为paramList是从静态的defaultParams数组
 * 中初始化出来的，因此你可以通过重新赋值defaultParams，来修改用于所有测试的paramList，或者可以通过传递针对
 * 某个测试的定制的paramList，来修改用于该测试的paramList：
 * @author wx
 *
 */
public class Tester<C> {
	public static int fieldWidth = 8;
	public static TestParam[] defaultParams = TestParam.array(10, 5000, 100, 5000, 1000, 5000, 10000, 500);
	
	/* 如果需要执行特殊的初始化，可以覆盖initialize()方法。
	 * 这将产生具有恰当尺寸的容器对象---你可以修改现有的容器对象，或者创建新的容器对象。 */
	protected C initialize(int size) {
		return container;
	}
	protected C container;
	private String headline = "";
	private List<Test<C>> tests;
	
	/* stringField()和numberField()方法会产生用于输出结果的格式化字符串
	 * 格式化的标准宽度可以通过修改静态的fieldWidth值进行调整 */
	private static String stringField() {
		return "%" + fieldWidth + "s";
	}
	private static String numberField() {
		return "%" + fieldWidth + "d";
	}
	
	private static int sizeWidth = 5;
	private static String sizeField = "%" + sizeWidth + "s";
	private TestParam[] paramList = defaultParams;
	
	public Tester(C container, List<Test<C>> tests) {
		this.container = container;
		this.tests = tests;
		if(container != null) {
			headline = container.getClass().getSimpleName();
		}
	}
	public Tester(C container, List<Test<C>> tests, TestParam[] paramList) {
		this(container, tests);
		this.paramList = paramList;
	}
	public void setHeadline(String newHeadline) {
		headline = newHeadline;
	}
	public static <C> void run(C cntnr, List<Test<C>> tests) {
		new Tester<C>(cntnr, tests).timeTest();
	}
	public static <C> void run(C cntnr, List<Test<C>> tests, TestParam[] paramList) {
		new Tester<C>(cntnr, tests, paramList).timeTest();
	}
	
	/* displayHeader()方法为每个测试格式化和打印头信息 */
	private void displayHeader() {
		int width = fieldWidth * tests.size() + sizeWidth;
		int dashLength = width - headline.length() - 1;
		StringBuilder head = new StringBuilder(width);
		for (int i = 0; i < dashLength / 2; i++) {
			head.append('-');
		}
		head.append(' ');
		head.append(headline);
		head.append(' ');
		for (int i = 0; i < dashLength / 2; i++) {
			head.append('-');
		}
		System.out.println(head);
		System.out.format(sizeField, "size");
		for (Test<C> test : tests) {
			System.out.format(stringField(), test.name);
		}
		System.out.println();
	}
	/* 每个Test.test()方法返回值都必须是该测试执行的操作的数量，这些测试都会计算其所有操作所需的纳秒数。 */
	public void timeTest() {
		displayHeader();
		for (TestParam param : paramList) {
			System.out.format(sizeField, param.size);
			for (Test<C> test : tests) {
				C kontainer = initialize(param.size);
				long start = System.nanoTime();
				int reps = test.test(kontainer, param);
				long duration = System.nanoTime() - start;
				long timePerRep = duration / reps;
				System.out.format(numberField(), timePerRep);
			}
			System.out.println();
		}
	}
}



















