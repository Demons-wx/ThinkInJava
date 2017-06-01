package online.wangxuan.io.listfiles;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * <p>匿名内部类</p>
 * @author wx
 *
 */
public class DirList2 {
	public static FilenameFilter filter(final String regex) {
		/* creation of anonymous inner class */
		return new FilenameFilter() {
			private Pattern pattern = Pattern.compile(regex);
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).matches();
			}
		};
	}
	public static void main(String[] args) {
		File path = new File("F:\\NewWorkSpaces\\ThinkInJava\\src\\online\\wangxuan\\concurrency");
		String[] list;
		if(args.length == 0) {
			list = path.list();
		} else {
			list = path.list(filter(args[0]));
		}
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for (String dirItem : list) {
			System.out.println(dirItem);
		}
	}
}
