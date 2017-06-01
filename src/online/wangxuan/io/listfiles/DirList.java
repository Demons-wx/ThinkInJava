package online.wangxuan.io.listfiles;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @Args: "C.*\.java"
 * @author wx
 *
 */
public class DirList {
	public static void main(String[] args) {
		File path = new File("F:\\NewWorkSpaces\\ThinkInJava\\src\\online\\wangxuan\\concurrency");
		String[] list;
		if(args.length == 0) {
			list = path.list();
		} else {
			/**
			 * list方法会为此目录对象下的每个文件名调用accept()，来判断该文件是否包含在内
			 */
			list = path.list(new DirFilter(args[0]));
		}
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for (String dirItem : list) {
			System.out.println(dirItem);
		}
	}
}

class DirFilter implements FilenameFilter {
	private Pattern pattern;
	public DirFilter(String regex) {
		pattern = Pattern.compile(regex);
	}
	/**
	 * accept()方法必须接受一个代表某个特定文件所在目录的File对象，以及包含了
	 * 那个文件名的一个String
	 */
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();
	}
}