package online.wangxuan.io.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import net.mindview.util.Print;

/**
 * 一旦底层的ByteBuffer通过视图缓冲器填满了整数或其他基本类型时，<br>
 * 就可以直接被写道通道中了。然后使用视图缓冲器可以把任何数据都转 <br>
 * 化成某一特定的基本类型。<br><br>
 * 
 * 在下面的例子中，通过在同一个ByteBuffer上建立不同的视图缓冲器，<br>
 * 将同一字节序列翻译成short、int、float、long、double类型的数据：
 * @author wx
 *
 */
public class ViewBuffers {
	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, 0, 0, 0, 'a'});
		bb.rewind();
		Print.printnb("Byte Buffer ");
		while(bb.hasRemaining()) {
			Print.printnb(bb.position() + " -> " + bb.get() + ". ");
		}
		Print.print();
		
		CharBuffer cb = ((ByteBuffer)bb.rewind()).asCharBuffer();
		Print.printnb("Char Buffer ");
		while(cb.hasRemaining()) {
			Print.printnb(cb.position() + " -> " + cb.get() + ". ");
		}
		Print.print();
		
		FloatBuffer fb = ((ByteBuffer)bb.rewind()).asFloatBuffer();
		Print.printnb("Float Buffer ");
		while(fb.hasRemaining()) {
			Print.printnb(fb.position() + " -> " + fb.get() + ". ");
		}
		Print.print();
		
		IntBuffer ib = ((ByteBuffer)bb.rewind()).asIntBuffer();
		Print.printnb("Int Buffer ");
		while(ib.hasRemaining()) {
			Print.printnb(ib.position() + " -> " + ib.get() + ". ");
		}
		Print.print();
		
		LongBuffer lb = ((ByteBuffer)bb.rewind()).asLongBuffer();
		Print.printnb("Long Buffer ");
		while(lb.hasRemaining()) {
			Print.printnb(lb.position() + " -> " + lb.get() + ". ");
		}
		Print.print();
		
		ShortBuffer sb = ((ByteBuffer)bb.rewind()).asShortBuffer();
		Print.printnb("Short Buffer ");
		while(sb.hasRemaining()) {
			Print.printnb(sb.position() + " -> " + sb.get() + ". ");
		}
		Print.print();
		
		DoubleBuffer db = ((ByteBuffer)bb.rewind()).asDoubleBuffer();
		Print.printnb("Double Buffer ");
		while(db.hasRemaining()) {
			Print.printnb(db.position() + " -> " + db.get() + ". ");
		}
		Print.print();
	}
}
