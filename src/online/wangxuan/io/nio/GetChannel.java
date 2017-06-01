package online.wangxuan.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO速度的提升来自于所使用的结构更接近与惭怍系统执行I/O的方式：通道和缓冲器。<br>
 * 我们可以把它想象成一个煤矿，通道是一个包含煤层(数据)的矿藏，而缓冲器则是派送 <br>
 * 到矿藏的卡车。卡车满载而归，我们再从卡车上获得煤炭。<br><br>
 * 
 * 也就是说：我们并没有直接和通道交互，我们只是和缓冲器交互，并把缓冲器派送到通道。<br>
 * 通道要么从缓冲器中获得数据，要么向缓冲器发送数据。<br><br>
 * 
 * 唯一直接与通道交互的缓冲器是ByteBuffer，也就是说，可以存储未加工字节的缓冲器。<br>
 * 但是，没办法输出或读取对象，即使是字符串对象也不行。这种处理虽然很低级，但却正 <br>
 * 好，因为这是大多数操作系统中更有效的映射方式。<br><br>
 * 
 * 旧的I/O类库中有三个类被修改了，用以产生FileChannel。这三个被修改的类是FileInputStream、 <br>
 * FileOutputStream以及用于即读又写的RandomAccessFile。这些都是字节操作流，与底层的nio <br>
 * 性质一致。Reader和Writer这种字符模式类不能用于产生通道，但是java.nio.channels.Channels <br>
 * 类提供了实用方法，用以在通道中产生Reader和Writer。<br><br>
 * 
 * 下面示例简单演示了上面三种类型的流，用于产生可写的、可读可写的及可读的通道：<br><br>
 * 
 * 将字节存放于ByteBuffer的方法之一是：使用一种'put'方法直接对他们进行填充，<br>
 * 填入一个或多个字节，或基本数据类型值。<br>
 * 不过，正如所见，也可以使用wrap()方法将已存在的字节数组包装到ByteBuffer中。<br>
 * 一旦如此，就不再复制底层的数组，而是把它作为所产生的ByteBuffer的存储器，<br>
 * 我们称之为数组支持的ByteBuffer。
 * 
 * @author wx
 *
 */
public class GetChannel {
	private static final int BSIZE = 1024;
	public static void main(String[] args) throws IOException {
		// Write a file
		FileChannel fc = new FileOutputStream("data.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text ".getBytes()));
		fc.close();
		// add to the end of the file
		fc = new RandomAccessFile("data.txt", "rw").getChannel();
		fc.position(fc.size()); // move to the end
		fc.write(ByteBuffer.wrap("Some more".getBytes()));
		fc.close();
		// Read the file
		/* 对于只读访问，我们必须显示的使用静态的allocate()方法来分配ByteBufer，nio
		 * 的目标就是快速移动大量的数据，因此ByteBuffer的大小就显得尤为重要，实际上
		 * 这里使用1k可能比我们通常使用的要小一点。(必须通过实际运行应用程序来找到最佳尺寸)。
		 * 达到更高的速度也是有可能的，方法就是使用allocateDirect()而不是allocate()，
		 * 以产生一个与操作系统有跟高耦合性的直接缓冲器。*/
		fc = new FileInputStream("data.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		fc.read(buff);
		buff.flip();
		while(buff.hasRemaining()) {
			System.out.print((char)buff.get());
		}
	}
}















