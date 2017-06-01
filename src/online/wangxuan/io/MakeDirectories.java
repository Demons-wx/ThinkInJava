package online.wangxuan.io;

import java.io.File;

/**
 * File类不仅仅只代表存在的文件或目录。也可以用File对象来创建新的目录或尚不存在的整个目录路径 <br>
 * 我们还可以查看文件的特性(如：大小、最后修改日期、读/写)，检查某个File对象代表的是一个文件 <br>
 * 还是一个目录，并且可以删除文件。
 * @author wx
 *
 */
public class MakeDirectories {
	private static void usage() {
		System.err.println(
			"Usage:MakeDirectories path1 ...\n" +
			"Creates each path\n" +
			"Usage:MakeDirectories -d path1 ...\n" +
			"Deletes each path\n" + 
			"Usage:MakeDirectories -r path1 path2\n" + 
			"Renames from path1 to path2");
		System.exit(1);
	}
	private static void fileDate(File f) {
		System.out.println(
			"Absolute path: " + f.getAbsolutePath() + 
			"\n Can read: " + f.canRead() + 
			"\n Can write: " + f.canWrite() + 
			"\n getName: " + f.getName() + 
			"\n getParent: " + f.getParent() + 
			"\n getPath: " + f.getPath() + 
			"\n length: " + f.length() + 
			"\n lastModofied: " + f.lastModified());
		if(f.isFile()) {
			System.out.println("It's a file");
		} else if(f.isDirectory()) {
			System.out.println("It's a directory");
		}
	}
	public static void main(String[] args) {
		if(args.length < 1) {
			usage();
		}
		if(args[0].equals("-r")) {
			if(args.length != 3) {
				usage();
			}
			File 
				old = new File(args[1]),
				rname = new File(args[2]);
			old.renameTo(rname);
			fileDate(old);
			fileDate(rname);
			return;
		}
		int count = 0;
		boolean del = false;
		if(args[0].equals("-d")) {
			count++;
			del = true;
		}
		count--;
		while(++count < args.length) {
			File f = new File(args[count]);
			if(f.exists()) {
				System.out.println(f + " exists");
				if(del) {
					System.out.println("deleting..." + f);
					f.delete();
				}
			} else { // doesn't exist
				if(del) { 
					f.mkdirs();
					System.out.println("created " + f);
				}
			}
			fileDate(f);
		}
	}
}




























