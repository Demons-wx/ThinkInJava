package online.wangxuan.containers.chosingcontainers;

/**
 * 每个容器都要经历一系列对test()的调用，每个都带有不同的TestParam，因此TestParam还包含静态的array()
 * 方法，使得创建TestParam对象数组变得更容易。<br><br>
 * array()的第一个版本接受的是可变参数列表，其中包括可互换的size和loops的值，<br>
 * 第二个版本接受相同类型的列表，但是它的值都在String中，通过这种方式，它可以用来解析命令行参数：
 * @author wx
 *
 */
public class TestParam {
	public final int size;
	public final int loops;
	public TestParam(int size, int loops) {
		this.size = size;
		this.loops = loops;
	}
	public static TestParam[] array(int... values) {
		int size = values.length / 2 ;
		TestParam[] result = new TestParam[size];
		int n = 0;
		for (int i = 0; i < size; i++) {
			result[i] = new TestParam(values[n++], values[n++]);
		}
		return result;
	}
	public static TestParam[] array(String[] values) {
		int[] vals = new int[values.length];
		for (int i = 0; i < vals.length; i++) {
			vals[i] = Integer.decode(values[i]);
		}
		return array(vals);
	}
}
