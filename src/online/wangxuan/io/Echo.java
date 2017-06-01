package online.wangxuan.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * System.in是一个没有被包装过的未经加工的InputStream。这就意味着 <br>
 * 尽管我们可以立即使用System.out和System.err，但是在读取System.in <br>
 * 之前必须对其进行包装。<br><br>
 * 
 * 通常我们会用readLine()一次一行的读取输入，为此，我们将System.in包装 <br>
 * 成BufferedReader来使用，这要求我们必须用InputStreamReader把System.in <br>
 * 转换成Reader。<br><br>
 * 
 * 下面这个例子将直接回显你所输入的每一行。
 * @author wx
 *
 */
public class Echo {
	public static void main(String[] args) throws IOException {
		BufferedReader stdin = new BufferedReader(
				new InputStreamReader(System.in));
		String s;
		while((s = stdin.readLine()) != null && s.length() > 0) {
			System.out.println(s);
		}
	}
}
