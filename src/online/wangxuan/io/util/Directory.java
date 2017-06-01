package online.wangxuan.io.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;



public class Directory {
	/**
	 * local()方法使用listFiles()来产生File数组。
	 * @param dir
	 * @param regex
	 * @return
	 */
	public static File[] local(File dir, final String regex) {
		return dir.listFiles(new FilenameFilter() {
			private Pattern pattern = Pattern.compile(regex);
			public boolean accept(File dir, String name) {
				return pattern.matcher(new File(name).getName()).matches();
			}
		});
	}
	public static File[] local(String path, final String regex) {
		return local(new File(path), regex);
	}
	/**
	 * 元组 </br>
	 * 一个list持有所有普通文件，
	 * 另一个list持有目录
	 * @author wx
	 *
	 */
	public static class TreeInfo implements Iterable<File> {
		public List<File> files = new ArrayList<File>();
		public List<File> dirs = new ArrayList<File>();
		public Iterator<File> iterator() {
			return files.iterator();
		}
		void addAll(TreeInfo other) {
			files.addAll(other.files);
			dirs.addAll(other.dirs);
		}
		public String toString() {
			return "dirs: " + PPrint.pformat(dirs) + "\n\nfiles: " + PPrint.pformat(files);
		}
	}
	/**
	 * 将开始目录的名字转换为File对象
	 * @param start
	 * @param regex
	 * @return
	 */
	public static TreeInfo walk(String start, String regex) {
		return recurseDirs(new File(start), regex);
	}
	public static TreeInfo walk(File start, String regex) {
		return recurseDirs(start, regex);
	}
	public static TreeInfo walk(File start) {
		return recurseDirs(start, ".*");
	}
	public static TreeInfo walk(String start) {
		return recurseDirs(new File(start), ".*");
	}
	/**
	 * 递归的遍历目录
	 * @param startDir
	 * @param regex
	 * @return
	 */
	static TreeInfo recurseDirs(File startDir, String regex) {
		TreeInfo result = new TreeInfo();
		for (File item : startDir.listFiles()) {
			if(item.isDirectory()) {
				result.dirs.add(item);
				result.addAll(recurseDirs(item, regex)); // 递归，并将所有的file和dir加入到各自的list
			} else {
				if(item.getName().matches(regex)) {
					result.files.add(item);
				}
			}
		}
		return result;
	}
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println(walk("F:\\练习代码\\笔记"));
		} else {
			for (String arg : args) {
				System.out.println(walk(arg));
			}
		}
	}
}

















