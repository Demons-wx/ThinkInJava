package online.wangxuan.io.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * 下例中包含的static方法可以像简单字符串那样读写文本文件，<br>
 * 并且我们可以创建一个TextFile对象，它用一个ArrayList来保存文件的若干行。
 * @author wx
 *
 */
public class TextFile extends ArrayList<String> {
	/* read a file as a single string */
	public static String read(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					new File(fileName).getAbsoluteFile()));
			try {
				String s;
				while ((s = in.readLine()) != null) {
					sb.append(s);
					sb.append("\n");
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	/* Write a single file in one method call */
	public static void write(String fileName, String text) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try {
				out.print(text);
			} finally {
				out.close();
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	/* Read a file, split by any regular expression */
	public TextFile(String fileName, String splitter) {
		super(Arrays.asList(read(fileName).split(splitter)));
		// regular expression split() often leaves an empty String at the first position
		if(get(0).equals("")) {
			remove(0);
		}
	}
	/* Normally read by lines; */
	public TextFile(String fileName) {
		this(fileName, "\n");
	}
	public void write(String fileName) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try {
				for (String item : this) {
					out.println(item);
				}
			} finally {
				out.close();
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException();
		}
	}
	/* simple test */
	public static void main(String[] args) {
		String file = read("src/online/wangxuan/io/util/TextFile.java");
		write("test.txt", file);
		TextFile text = new TextFile("test.txt");
		text.write("test2.txt");
		// Break into unique sorted list of words
		TreeSet<String> words = new TreeSet<String>(
				new TextFile("src/online/wangxuan/io/util/TextFile.java", "\\W+"));
		// Display the capitalized words
		System.out.println(words.headSet("a"));
	}
}




















