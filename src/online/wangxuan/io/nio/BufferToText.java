package online.wangxuan.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 从GetChannel.java程序中可以看到，为了输出文件中的信息，每次只读取一个字节 <br>
 * 的数据，然后将每个byte类型强制转换成char类型。这种方法似乎有点原始。 <br><br>
 * 
 * 有一个CharBuffer类，它有一个toString()方法：返回一个包含缓冲器中所有字符 <br>
 * 的字符串。既然ByteBuffer可以看作是具有asCharBuffer()方法的CharBuffer，<br>
 * 那么为什么不用它呢？ <br><br>
 * 
 * 正如下面输出语句中第一行可见，这种方法并不能解决问题：
 * @author wx
 *
 */
public class BufferToText {
	private static final int BSIZE = 1024;
	public static void main(String[] args) throws IOException {
		FileChannel fc = new FileOutputStream("data2.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes()));
		fc.close();
		/* 缓冲器容纳的是普通字节，为了把它们转换成字符，我们要么在输入它们的时候
		 * 对其进行编码，要么将其从缓冲器中输出时对他们进行解码。 */
		fc = new FileInputStream("data2.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		fc.read(buff);
		buff.flip();
		// Doesn't work
		System.out.println(buff.asCharBuffer());
		
		// Decode using this system's default Charset
		buff.rewind(); // 倒带
		String encoding = System.getProperty("file.encoding");
		System.out.println("Decoding using " + encoding + 
				": " + Charset.forName(encoding).decode(buff));
		
		// Or we could encode with something that will print
		fc = new FileOutputStream("data2.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
		fc.close();
		// Now try to reading again
		fc = new FileInputStream("data2.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
		
		// Use a CharBuffer to write through
		fc = new FileOutputStream("data2.txt").getChannel();
		buff = ByteBuffer.allocate(24); // More than needed
		buff.asCharBuffer().put("Some text");
		fc.write(buff);
		fc.close();
		// Read and display
		fc = new FileInputStream("data2.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
	}
}























