package online.wangxuan.io.representativeexp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 使用RandomAccessFile，类似于组合使用了DataInputStream和DataOutputStream <br>
 * 因为它实现了相同的接口：DataInput和DataOutput，另外我们可以看到，利用seek()可以 <br>
 * 在文件中到处移动，并修改文件中的某个值。 <br><br>
 * 在使用RandomAccessFile时，你必须知道文件排版，这样才能正确操作它。<br>
 * RandomAccessFile拥有读取基本类型和UTF-8字符串的各种具体方法。<br><br>
 * RandomAccessFile除了实现DataInput和DataOutput接口之外，有效的与I/O继承 <br>
 * 层次实现了分离。因为它不支持装饰，所以不能将其与InputStream及OutputStream <br>
 * 子类的任何部分组合起来。我们必须假定RandomAccessFile已经被正确缓冲，因为我 <br>
 * 们不能添加这样的功能。
 * @author wx
 *
 */
public class UsingRandomAccessFile {
	static String file = "rtest.dat";
	static void display() throws IOException {
		RandomAccessFile rf = new RandomAccessFile(file, "r");
		for (int i = 0; i < 7; i++) {
			System.out.println("value " + i + ": " + rf.readDouble());
		}
		System.out.println(rf.readUTF());
		rf.close();
	}
	public static void main(String[] args) throws IOException {
		RandomAccessFile rf = new RandomAccessFile(file, "rw");
		for (int i = 0; i < 7; i++) {
			rf.writeDouble(i * 1.414);
		}
		rf.writeUTF("The end of the file");
		rf.close();
		display();
		rf = new RandomAccessFile(file, "rw");
		/* 因为double总是8字节长，所以为了用seek()查找第5个双精度值，你只需用5*8来产生查找位置 */
		rf.seek(5*8);
		rf.writeDouble(47.0001);
		rf.close();
		display();
	}
}
