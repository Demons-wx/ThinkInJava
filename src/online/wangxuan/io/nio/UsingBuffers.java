package online.wangxuan.io.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import net.mindview.util.Print;

/**
 * 下面的示例用到一个很简单的算法(交换相邻字符)，以对CharBuffer中的字符进行 <br>
 * 编码和译码：
 * @author wx
 *
 */
public class UsingBuffers {
	private static void symmetricScramble(CharBuffer buffer) {
		while(buffer.hasRemaining()) {
			buffer.mark(); // 将mark的值设置为position的值
			char c1 = buffer.get();
			char c2 = buffer.get();
			buffer.reset(); // 将position的值设置为mark的值
			buffer.put(c2).put(c1);
		}
	}
	public static void main(String[] args) {
		char[] data = "UsingBuffers".toCharArray();
		ByteBuffer bb = ByteBuffer.allocate(data.length * 2);
		CharBuffer cb = bb.asCharBuffer();
		cb.put(data);
		Print.print(cb.rewind());
		symmetricScramble(cb);
		Print.print(cb.rewind());
		symmetricScramble(cb);
		Print.print(cb.rewind());
	}
}
