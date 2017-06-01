package online.wangxuan.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 一旦调用read()来告知FileChannel向ByteBuffer存储字节，就必须调用缓冲器上的flip() <br>
 * 让它做好让别人读取字节的准备。如果我们打算使用缓冲器进行一步的read()操作，我们 <br>
 * 也必须调用clear()来为每个read()做好准备。下面这个简单的文件复制程序中可以看到：
 * @author wx
 *
 */
public class ChannelCopy {
	private static final int BSIZE = 1024;
	public static void main(String[] args) throws IOException {
		if(args.length != 2) {
			System.out.println("arguments: sourcefile destfile");
			System.exit(1);
		}
		FileChannel 
			in = new FileInputStream(args[0]).getChannel(),
			out = new FileOutputStream(args[1]).getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		/* 每次read操作之后，就会将数据输入到缓冲器中，flip()则是准备缓冲器以便
		 * 它的信息可以由write()提取。write()操作之后，信息仍在缓冲器中，接着
		 * clear()操作则对所有的内部指针重新安排，以便缓冲器在另一个read()操作
		 * 期间内能够做好接收数据的准备。 */
		while(in.read(buffer) != -1) {
			buffer.flip(); // Prepare for writing
			out.write(buffer);
			buffer.clear(); // Prepare for reading
		}
	}
}
