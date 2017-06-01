package online.wangxuan.io.representativeexp;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 如果我们从DataInputStream用readByte()一次一个字节的读取字符，那么任何字节值 <br>
 * 都是合法的结果，因此返回值不能用来检测输入是否结束。<br><br>
 * 相反，我们可以使用available()方法查看还有多少可供存取的字符。下面这个例子演示了怎样
 * 一次一个字节地读取文件：
 * @author WX
 *
 */
public class TestEOF {
	public static void main(String[] args) throws IOException {
		DataInputStream in = new DataInputStream(
				new BufferedInputStream(
						new FileInputStream("src/online/wangxuan/io/representativeexp/TestEOF.java")));
		/* 注意，available()的工作方式会随着所读取的媒介类型的不同而有所不同，
		 * 字面意思就是：在没有阻塞的情况下所能读取的字节数。对于文件，这意味
		 * 着整个文件，但是对于不同类型的流，可能就不是这样，需要谨慎使用。
		 * 
		 *  我们也可以通过捕获异常来检测输入的末尾，但是使用异常进行流控制，被认为
		 *  是对异常特性的错误使用。*/
		while(in.available() != 0) {
			System.out.print((char)in.readByte());
		}
	}
}
