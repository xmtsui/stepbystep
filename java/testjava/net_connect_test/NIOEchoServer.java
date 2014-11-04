import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;

public class NIOEchoServer {
    private static final int TIMEOUT = 300;
    private static final int PORT    = 8888;

    public static void main(String[] args) {
        try {
            //创建一个选择器  的作用是监听管理事件
            Selector selector = Selector.open();
            //创建一个ServerSocketChannel  对象 server
            ServerSocketChannel listenChannel = ServerSocketChannel.open();
            //配置为非阻塞方式
            listenChannel.configureBlocking(false);
            // 启动服务器并把服务器绑定到监听端口 8888
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            //注册到选择器selector中   注意注册的事件类型为 OP_ACCEPT 既告诉
            //注册器 Seclector 你需要监听 指定端口的On_ACCEPT事件 （注意现在注册器只会监听这一种事件 而且在指定端口） 
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                //监听事件
                if (selector.select(TIMEOUT) == 0) {
                    System.out.print(".");
                    continue;
                }
                //事件来源列表
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    //获取一个事件  开始处理这个事件
                    SelectionKey key = iter.next();
                    iter.remove(); //删除当前事件

                    //对每一个事件来源key分别进行处理
                    if (key.isAcceptable()) {
                        //前面说注册器Seclector要监听ON_ACCEPT事件 现在事件来了  需要服务端做相应处理了
                        //这里的处理方式是 启动一个 SocketChannel对象  来处理客户端的这个连接  这里的处理
                        //也很有意思  是给当前的这个事件key绑定一个SocketChannel对象 ，告诉Seltor 如有ON_READ事件
                        //进来 就用 此 SocketChannl来处理  
                        //启动服务器的一个channel对象对某个事件进行处理，这个channel对象代表客户端的连接
                        // 作用类似于socket
                        SocketChannel channel = listenChannel.accept();
                        channel.configureBlocking(false);
                        //把此channel 客户端对象作为一个事件注册到 选择器 selector中
                        // SelectionKey connkey = channel.register(selector, SelectionKey.OP_READ);
                        // NIOServerConnection conn = new NIOServerConnection(connkey);
                        // connkey.attach(conn);
                    }

                    // if (key.isReadable()) {
                    //     NIOServerConnection conn = (NIOServerConnection) key.attachment();
                    //     conn.handleRead();
                    // }

                    // if (key.isValid() && key.isWritable()) {
                    //     NIOServerConnection conn = (NIOServerConnection) key.attachment();
                    //     conn.handleWrite();
                    // }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class NIOServerConnection {
    private static final int BUFFSIZE = 1024;

    SelectionKey             key;
    SocketChannel            channel;
    ByteBuffer               buffer;

    public NIOServerConnection(SelectionKey key) {
        this.key = key;
        this.channel = (SocketChannel) key.channel();
        buffer = ByteBuffer.allocate(BUFFSIZE);
    }

    public void handleRead() throws IOException {
        // System.out.println("now server read");
        long bytesRead = channel.read(buffer);
        WritableByteChannel out = Channels.newChannel(System.out);
        System.out.print("from client: read num -> " + bytesRead + ", content -> ");
        buffer.flip();
        //        buffer.rewind();
        out.write(buffer);
        buffer.rewind();

        if (bytesRead == -1) {
            channel.close();
        } else {
            key.interestOps(SelectionKey.OP_WRITE);
        }
    }

    public void handleWrite() throws IOException {
        buffer.rewind();
        CharsetDecoder cd = Charset.forName("ASCII").newDecoder();
        CharBuffer charBuffer = cd.decode(buffer);
        String read = charBuffer.toString();
        buffer.rewind();
        buffer.put(read.toUpperCase().getBytes());
        buffer.rewind();
        channel.write(buffer);
        if (!buffer.hasRemaining()) {
            key.interestOps(SelectionKey.OP_READ);
        }
        buffer.compact();
    }
}