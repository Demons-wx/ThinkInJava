package online.wangxuan.holding.foreach;

import java.util.Map;

/**
 * 在Java SE5中，大量的类都是Iterable类型，主要包括所有的Collection类 <br>
 * 但不包括各种Map，例如下面代码可以显示所有的操作系统环境变量：
 * @author wx
 *
 */
public class EnvironmentVariables {
	public static void main(String[] args) {
		for (Map.Entry entry : System.getenv().entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}
