package online.wangxuan.io.representativeexp;

import java.io.IOException;
import java.io.StringReader;

/**
 * 从BufferedInputFile.read()读入的String结果被用来创建一个StringReader。 <br>
 * 然后调用read()每次读取一个字符，并把它发送到控制台。
 * @author wx
 *
 */
public class MemoryInput {
	public static void main(String[] args) throws IOException {
		StringReader in = new StringReader(
				BufferedInputFile.read("src/online/wangxuan/io/representativeexp/MemoryInput.java"));
		int c;
		while((c = in.read()) != -1) {
			System.out.print((char)c);
		}
	}
}
