package online.wangxuan.io;

import java.io.PrintWriter;

/**
 * System.out是一个PrintStream，而PrintStream是一个OutputStream。 <br>
 * PrintWriter有一个可以接受OutputStream作为参数的构造器。
 * @author wx
 *
 */
public class ChangeSystemOut {
	public static void main(String[] args) {
		/* 重要的是要使用有两个参数的PrintWriter的构造器，并将第二个参数设为true
		 * 以便开启自动清空功能，否则，你将看不到输出。 */
		PrintWriter out = new PrintWriter(System.out, true);
		out.println("Hello world");
	}
}
