package online.wangxuan.io.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 这个工具与TextFile类似，因为它简化了读取二进制文件的过程。
 * @author wx
 *
 */
public class BinaryFile {
	public static byte[] read(File bFile) throws IOException {
		BufferedInputStream bf = new BufferedInputStream(
				new FileInputStream(bFile));
		try {
			byte[] data = new byte[bf.available()];
			bf.read(data);
			return data;
		} finally {
			bf.close();
		}
	}
	public static byte[] read(String bFile) throws IOException {
		return read(new File(bFile).getAbsoluteFile());
	}
}
