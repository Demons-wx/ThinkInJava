package online.wangxuan.io.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import net.mindview.util.Print;

/**
 * 内存映射文件允许我们创建和修改那些因为太大而不能放入内存的文件。有了内存映射文件 <br>
 * 我们就可以假定整个文件都放在内存中，而且可以完全把它当作非常大的数组来访问，这种 <br>
 * 方法极大的简化了用于修改文件的代码，下面是一个小例子：
 * @author wx
 *
 */
public class LargeMappedFiles {
	static int length = 0x8FFFFFF; // 128M
	public static void main(String[] args) throws Exception {
		/* 为了既能读又能写，我们先由RandomAccessFile开始，获得该文件上的通道，然后调用
		 * map()产生MappedByteBuffer，这是一种特殊类型的直接缓冲器，注意我们必须直接指定
		 * 映射文件的初始位置和映射区域长度，这意味着我们可以映射某个大文件的较小的部分。 */
		MappedByteBuffer out = new RandomAccessFile("test.dat", "rw").getChannel()
				.map(FileChannel.MapMode.READ_WRITE, 0, length);
		/* 程序创建的文件为128M，这可能比操作系统所允许一次载入内存的空间大，但似乎我们可以
		 * 一次访问到整个文件，因为只有一部分文件放入了内存，文件的其他部分被交换了出去。用
		 * 这种方式，很大的文件(可达2G)也可以很容易的修改。注意底层操作系统的文件映射工具是
		 * 用来最大化的提高性能。 */
		for (int i = 0; i < length; i++) {
			out.put((byte)'x');
		}
		Print.print("Finished writing");
		for (int i = length/2; i < length/2 + 6; i++) {
			Print.printnb((char)out.get(i));
		}
	}
}
