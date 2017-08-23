package online.wangxuan.io.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 结合ServerSocketChannel和Selector构建简单的服务器。
 *
 * 服务端
 * Created by wangxuan on 2017/8/23.
 */
public class ServerSocketChannelTest {

    private int size = 1024;
    private ServerSocketChannel socketChannel;
    private ByteBuffer byteBuffer;
    private Selector selector;
    private final int port = 8998;
    private int remoteClientNum = 0;

    public ServerSocketChannelTest() {
        try {
            initChannel();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    public void initChannel() throws Exception {
        socketChannel = ServerSocketChannel.open();

        // 通道要与Selector一起使用时，Channel必须处于非阻塞模式下。
        socketChannel.configureBlocking(false);
        // 服务端监听端口
        socketChannel.bind(new InetSocketAddress(port));
        System.out.println("listener on port:" + port);
        selector = Selector.open(); // 实例化一个selector

        /**
         * register()方法第二个参数是 interest集合 表示选择器所关心的通道操作
         * 它有以下四种操作类型：
         * - Connect 连接
         * - Accept 接受
         * - Read 读
         * - Write 写
         * 需要注意并非所有操作在所有可选择通道上都能被支持，比如ServerSocketChannel支持Accept，而SocketChannel中不支持
         */
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        byteBuffer = ByteBuffer.allocateDirect(size);

        // 大字节序 (最高有效位落在最低地址上的存储方式)
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
    }

    private void listener() throws Exception {

        while (true) {
            /** 通过selector的select()方法可以选择已经准备就绪的通道(这些通道包含你感兴趣的事件)
                返回的int值表示有多少通道已经就绪。
             */
            int n = selector.select();
            if (n == 0)
                continue;

            // 一旦select()方法返回值不为0，则可以通过调用Selector的selectedKeys()方法来访问已选择键集合。
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    registerChannel(selector, channel, SelectionKey.OP_READ);
                    remoteClientNum++;
                    System.out.println("online client num = " + remoteClientNum);
                    replyClient(channel);
                }

                if (key.isReadable()) {
                    readDataFromSocket(key);
                }

                iterator.remove();
            }
        }
    }

    protected void readDataFromSocket(SelectionKey key) throws Exception {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        byteBuffer.clear();
        while ((count = socketChannel.read(byteBuffer)) > 0) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }
            byteBuffer.clear();
        }
        if (count < 0)
            socketChannel.close();
    }

    private void replyClient(SocketChannel channel) throws IOException{
        byteBuffer.clear();
        byteBuffer.put("hello client!\r\n".getBytes());
        byteBuffer.flip();
        channel.write(byteBuffer);
    }

    private void registerChannel(Selector selector, SocketChannel channel, int ops) throws Exception{
        if (channel == null)
            return;
        channel.configureBlocking(false);
        channel.register(selector, ops);
    }

    public static void main(String[] args) {
        try {
            new ServerSocketChannelTest().listener();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
