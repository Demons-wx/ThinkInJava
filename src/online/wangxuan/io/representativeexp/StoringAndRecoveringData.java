package online.wangxuan.io.representativeexp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * PrintWriter可以对数据进行格式化，以便人们阅读，但是为了输出可供另一个"流" <br>
 * 恢复的数据，我们需要用DataOutputStream写入数据，并用DataInputStream恢复数据。 <br>
 * @author wx
 *
 */
public class StoringAndRecoveringData {
	public static void main(String[] args) throws IOException {
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
				new FileOutputStream("Data.txt")));
		/* 当我们使用DataOutputStream时，写字符串并且让DataInputStream能够恢复
		 * 它的唯一可靠的做法就是使用UTF-8编码，这个示例是用writeUTF()和readUTF()实现的。 */
		out.writeDouble(3.14159);
		out.writeUTF("that was PI");
		out.writeDouble(1.41413);
		out.writeUTF("Square root of 2");
		out.close();
		DataInputStream in = new DataInputStream(
				new BufferedInputStream(new FileInputStream("Data.txt")));
		System.out.println(in.readDouble());
		// only readUTF() will recover the Java-UTF String properly
		System.out.println(in.readUTF());
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
	}
}
