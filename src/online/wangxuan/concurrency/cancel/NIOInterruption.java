package online.wangxuan.concurrency.cancel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * nio类提供了更人性化的I/O中断。被阻塞的nio通道会自动响应中断
 * @author wx
 *
 */
class NIOBlocked implements Runnable {
	private final SocketChannel sc;
	public NIOBlocked(SocketChannel sc) {
		this.sc = sc;
	}
	public void run() {
		try {
			System.out.println("Waiting for read() in " + this);
			sc.read(ByteBuffer.allocate(1));
		} catch(ClosedByInterruptException e) {
			System.out.println("ClosedByInterruptException");
		} catch(AsynchronousCloseException e) {
			System.out.println("AsynchronousCloseException");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Exiting NIOBlocked.run() " + this);
	}
}

public class NIOInterruption {
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8088);
		InetSocketAddress isa = new InetSocketAddress("localhost", 8088);
		SocketChannel sc1 = SocketChannel.open(isa);
		SocketChannel sc2 = SocketChannel.open(isa);
		Future<?> f = exec.submit(new NIOBlocked(sc1));
		exec.execute(new NIOBlocked(sc2));
		exec.shutdown();
		TimeUnit.SECONDS.sleep(1);
		// produce a interrupt via cancel
		f.cancel(true);
		TimeUnit.SECONDS.sleep(1);
		// release the block by closing the channel
		sc2.close();
	}
}





















