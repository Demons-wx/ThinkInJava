package online.wangxuan.io.representativeexp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * FileWriter对象可以向文件写入数据，创建一个与指定文件连接的FileWriter。 <br>
 * 实际上，我们通常会用BufferedWriter将其包装起来用以缓冲输出。<br>
 * 在本例中，为了提供格式化机制，它被装饰成了PrintWriter。按照这种方式创建的数据文件可作为普通文本文件读取。
 * @author wx
 *
 */
public class BasicFileOutput {
	static String file = "BasicFileOutput.out";
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(
				new StringReader(
						BufferedInputFile.read("src/online/wangxuan/io/representativeexp/BasicFileOutput.java")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		int lineCount = 1;
		String s;
		while((s = in.readLine()) != null) {
			out.println(lineCount++ + " " + s);
		}
		/* 如果我们不为所有的输出文件调用close()，就会发现缓冲区内容不会被刷新清空，那么它们也就不完整。 */
		out.close();
		System.out.println(BufferedInputFile.read(file));
	}
}
