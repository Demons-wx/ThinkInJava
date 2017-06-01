package online.wangxuan.containers.chosingcontainers;

/**
 * 这是模板方法模式的另一个示例。尽管你遵循了典型的模板方法模式，覆盖了每个特定测试的Test.test()方法，
 * 但是在本例中，其核心代码(不会发生变化)在一个单独的Tester类中。
 * @author wx
 *
 * @param <C> 待测试容器类型
 */
public abstract class Test<C> {
	String name;
	public Test(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @param container 待测容器
	 * @param tp 信使或数据传输对象，保存用于该特定测试的各种参数。包括：size，表示在容器中的元素数量，loops，用来
	 * 控制测试迭代的次数。
	 * @return 该测试执行的操作的数量
	 */
	abstract int test(C container, TestParam tp);
}
