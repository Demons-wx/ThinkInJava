package online.wangxuan.io.representativeexp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * 要读取格式化数据，可以使用DataInputStream，它是一个面向字节的I/O类 <br>
 * 因此我们必须使用InputStream类而不是Reader类。
 * @author wx
 *
 */
public class FormattedMemoryInput {
	public static void main(String[] args) throws IOException {
		try {
			/* 必须为ByteArrayInputStream提供字节数组，为了产生该数组String
			 * 包含了一个可以实现此项工作的getByte()方法。 */
			DataInputStream in = new DataInputStream(
					new ByteArrayInputStream(
							BufferedInputFile.read(
									"src/online/wangxuan/io/representativeexp/FormattedMemoryInput.java").getBytes()));
			while(true) {
				System.out.print((char)in.readByte());
			}
		} catch (EOFException e) {
			System.err.println("End of stream");
		}
	}
}
