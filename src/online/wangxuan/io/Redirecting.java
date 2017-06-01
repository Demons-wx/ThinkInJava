package online.wangxuan.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Java的System类提供了一些简单的静态方法调用，以允许我们对标准输入、<br>
 * 输出和错误I/O流进行重定向。<br><br>
 * 
 * 如果我们突然开始在显示器上创建大量输出时，<br>
 * 而这些输出滚动的太快以至于无法阅读时，重定向输出就显得极为有用。
 * @author wx
 *
 */
public class Redirecting {
	public static void main(String[] args) throws IOException {
		PrintStream console = System.out;
		BufferedInputStream in = new BufferedInputStream(
				new FileInputStream("src/online/wangxuan/io/Redirecting.java"));
		PrintStream out = new PrintStream(
				new BufferedOutputStream(
						new FileOutputStream("test.out")));
		System.setIn(in);
		/* 将系统输出重定向到文件中 */
		System.setOut(out);
		System.setErr(out);
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		String s;
		while((s = br.readLine()) != null) {
			/* 不会输出到屏幕，而是写到test.out文件中 */
			System.out.println(s);
		}
		out.close();
		/* 恢复标准输出 */
		System.setOut(console);
	}
}
