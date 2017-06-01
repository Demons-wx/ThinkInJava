package online.wangxuan.io.nio;

import java.nio.ByteBuffer;

import net.mindview.util.Print;

/**
 * 尽管ByteBuffer只能保存字节类型的数据，但是它具有可以从其所容纳的字节中产生出 <br>
 * 各种不同类型值的方法。下面这个例子展示了怎样使用这些方法插入和抽取各种数值：
 * @author wx
 *
 */
public class GetData {
	private static final int BSIZE = 1024;
	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		// Allocation automatically zeroes the ByteBuffer
		/* 在分配一个ByteBuffer之后，可以通过检测它的值来查看缓冲器
		 * 的分配方式是否将其内容自动置零----它确实这样做了。这里一共
		 * 检测了1024个值，并且所有的值都是零。 */
		int i = 0;
		while(i++ < bb.limit()) {
			if(bb.get() != 0) {
				Print.print("nozero");
			}
		}
		Print.print("i = " + i);
		bb.rewind();
		
		// Store and read a char array
		bb.asCharBuffer().put("Howdy");
		char c;
		while((c = bb.getChar()) != 0) {
			Print.printnb(c + " ");
		}
		Print.print();
		bb.rewind();
		
		// Store and read a short 需要进行类型转换
		bb.asShortBuffer().put((short)471142);
		Print.print(bb.getShort());
		bb.rewind();
		
		// Store and read an int
		bb.asIntBuffer().put(99471142);
		Print.print(bb.getInt());
		bb.rewind();
		
		// Store and read a long
		bb.asLongBuffer().put(99471142);
		Print.print(bb.getLong());
		bb.rewind();
		
		// Store and read a float
		bb.asFloatBuffer().put(99471142);
		Print.print(bb.getFloat());
		bb.rewind();
		
		// Store and read a double
		bb.asDoubleBuffer().put(99471142);
		Print.print(bb.getDouble());
		bb.rewind();
	}
}



















