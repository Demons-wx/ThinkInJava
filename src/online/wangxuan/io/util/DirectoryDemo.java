package online.wangxuan.io.util;

import java.io.File;

import net.mindview.util.Print;

public class DirectoryDemo {
	public static void main(String[] args) {
		// All directories
		PPrint.pprint(Directory.walk(".").dirs);
		// All files begin with 'T'
		for (File file : Directory.walk("D:\\test", "T.*")) { // Directory中默认TreeInfo遍历的是files,在iterator()中设置
			Print.print(file);
		}
		Print.print("--------------------");
		// All java files beginning with 'T'
		for (File file : Directory.walk(".", "T.*\\.java")) {
			Print.print(file);
		}
		Print.print("====================");
		// Class files containing 'Z' or 'z'
		for (File file : Directory.walk(".", ".*[Zz].*\\.class")) {
			Print.print(file);
		}
	}
}
