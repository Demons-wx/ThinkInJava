package online.wangxuan.io.representativeexp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 如果想打开一个文件用于字符输入，可以使用以String或File对象作为文件名的 <br>
 * FileInputReader。为了提高速度，我们希望对那个文件进行缓冲，那么我们将所产生的 <br>
 * 引用传给一个BufferedReader构造器。由于BufferedReader也提供readLine()方法，所以 <br>
 * 这是我们最终对象和进行读取的接口。当readLine()返回null时，你就到达了文件末尾。
 * @author wx
 *
 */
public class BufferedInputFile {
	public static String read(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String s;
		StringBuilder sb = new StringBuilder();
		while((s = in.readLine()) != null) {
			sb.append(s + "\n");
		}
		in.close();
		return sb.toString();
	}
	public static void main(String[] args) throws IOException {
		System.out.println(read("src/online/wangxuan/io/representativeexp/BufferedInputFile.java"));
	}
}
