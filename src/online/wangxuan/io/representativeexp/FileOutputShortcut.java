package online.wangxuan.io.representativeexp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * Java SE5在PrintWriter中添加了一个辅助构造器，使得你不比在每次希望创建文本文件 <br>
 * 并向其中写入时，都去执行所有的装饰工作，下面是用这种快捷方式重写BasicFileOutput.java
 * @author wx
 *
 */
public class FileOutputShortcut {
	static String file = "FileOutputShortcut.out";
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(
				new StringReader(
						BufferedInputFile.read("src/online/wangxuan/io/representativeexp/FileOutputShortcut.java")));
		// Here is the shortcut
		PrintWriter out = new PrintWriter(file);
		int lineCount = 1;
		String s;
		while((s = in.readLine()) != null) {
			out.println(lineCount + " " + s);
		}
		out.close();
		// show the stored file
		System.out.println(BufferedInputFile.read(file));
	}
}
