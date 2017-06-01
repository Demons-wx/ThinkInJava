package online.wangxuan.io.nio;

import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * 视图缓冲器 可以让我们通过某个特定的基本数据类型的视窗查看其底层的ByteBuffer。 <br>
 * ByteBuffer是实际存储数据的地方，支持者前方的视图，因此对视图的任何修改都会 <br>
 * 映射成为对ByteBuffer的修改。<br><br>
 * 
 * 下面这个例子，通过IntBuffer操纵ByteBuffer中的int型数据：
 * @author wx
 *
 */
public class IntBufferDemo {
	private static final int BSIZE = 1024;
	public static void main(String[] args) throws FileNotFoundException {
		
		// 重定向输出到文件中
		/*PrintStream out = new PrintStream(
				new BufferedOutputStream(
						new FileOutputStream("test1.out")));
		System.setOut(out);*/
		
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		IntBuffer ib = bb.asIntBuffer();
		
		// Store an array of int
		ib.put(new int[]{11, 42, 47, 99, 143, 811, 1016});
		
		// Absolute location read and write
		System.out.println(ib.get(3));
		ib.put(3, 1811);
		
		// Setting a new limit before rewinding the buffer
		ib.flip();	
		while(ib.hasRemaining()) {
			int i = ib.get();
			System.out.println(i);
		}
//		out.close();
	}
}
