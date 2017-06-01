package online.wangxuan.initialization;

/**
 * 静态方法也可以调用非静态方法
 * @author wx
 *
 */
public class TestStatic {

	static void test(){
		new TestStatic().normal();
	}
	
	private void normal(){
		System.out.println("非静态方法...");
	}
	
	public static void main(String[] args) {
		TestStatic.test();
	}
}
