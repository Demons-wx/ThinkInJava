package online.wangxuan.io.nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * 尽管'旧'的I/O流在用nio实现后性能有所提高，但是'映射文件访问'往往可以更加显著 <br>
 * 的加快速度。下面的程序进行了简单的性能比较：<br><br>
 * 
 * 尽管'映射写'似乎要用到FileOutputStream，但是映射文件中的所有输出必须使用RandomAccessFile，
 * 正如前面程序代码中的读/写一样。<br><br>
 * 
 * 注意test()方法包括初始化各种I/O对象的时间，因此，即使建立映射文件花费很大，但是整体受益
 * 比起I/O流来说还是很显著的。
 * @author wx
 *
 */
public class MappedIO {
	private static int numOfInts = 4000000;
	private static int numOfUbuffInts = 200000;
	
	private abstract static class Tester {
		private String name;
		public Tester(String name) {
			this.name = name;
		}
		public void runTest() {
			System.out.println(name + ": ");
			try {
				long start = System.nanoTime();
				test();
				double duration = System.nanoTime() - start;
				System.out.format("%.2f\n", duration / 1.0e9);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		public abstract void test() throws IOException;
	}
	
	private static Tester[] tests = {
		new Tester("Stream Write") {
			public void test() throws IOException {
				DataOutputStream dos = new DataOutputStream(
						new BufferedOutputStream(
								new FileOutputStream(new File("temp.tmp"))));
				for (int i = 0; i < numOfInts; i++) {
					dos.writeInt(i);
				}
				dos.close();
			}
		},
		new Tester("Mapped Write") {
			public void test() throws IOException {
				FileChannel fc = new RandomAccessFile("temp.tmp", "rw").getChannel();
				IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
				for (int i = 0; i < numOfInts; i++) {
					ib.put(i);
				}
				fc.close();
			}
		},
		new Tester("Stream Read") {
			public void test() throws IOException {
				DataInputStream dis = new DataInputStream(
						new BufferedInputStream(
								new FileInputStream(new File("temp.tmp"))));
				for (int i = 0; i < numOfInts; i++) {
					dis.readInt();
				}
				dis.close();
			}
		},
		new Tester("Mapped Read") {
			public void test() throws IOException {
				FileChannel fc = new FileInputStream(new File("temp.tmp")).getChannel();
				IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
				while(ib.hasRemaining()) {
					ib.get();
				}
				fc.close();
			}
		},
		new Tester("Stream Read/Write") {
			public void test() throws IOException {
				RandomAccessFile raf = new RandomAccessFile(new File("temp.tmp"), "rw");
				raf.writeInt(1);
				for(int i = 0; i < numOfUbuffInts; i++) {
					raf.seek(raf.length() -4);
					raf.writeInt(raf.readInt());
				}
				raf.close();
			}
		},
		new Tester("Mapped Read/Write") {
			public void test() throws IOException {
				FileChannel fc = new RandomAccessFile(new File("temp.tmp"), "rw").getChannel();
				IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
				ib.put(0);
				for(int i = 1; i < numOfUbuffInts; i++) {
					ib.put(ib.get(i - 1));
				}
				fc.close();
			}
		}
	};
	public static void main(String[] args) {
		for (Tester test : tests) {
			test.runTest();
		}
	}
}


















