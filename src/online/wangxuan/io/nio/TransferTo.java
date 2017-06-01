package online.wangxuan.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * channelCopy并不是我们处理文件复制操作的理想方式。 <br>
 * 特殊方法transferTo()和transferFrom()则允许我们将一个通道和另一个通道直接相连：
 * @author wx
 *
 */
public class TransferTo {
	public static void main(String[] args) throws IOException {
		if(args.length != 2) {
			System.out.println("arguments: sourcefile destfile");
			System.exit(1);
		}
		FileChannel 
			in = new FileInputStream(args[0]).getChannel(),
			out = new FileOutputStream(args[1]).getChannel();
		in.transferTo(0, in.size(), out);
		// or
	//	out.transferFrom(in, 0, in.size());
	}
}
