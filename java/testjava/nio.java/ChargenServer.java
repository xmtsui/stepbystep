import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Set;
class ChargenServer{
	public static void main(String[] args)
	{
		try{
			ServerSocketChannel serverChannel = ServerSocketChannel.open();//创建一个新的ServerChannel对象
			serverChannel.configureBlocking(false);//设置服务端通道为非阻塞

			ServerSocket ss = serverChannel.socket();//获取Socket对象
			ss.bind(new InetSocketAddress(1119));//将通道绑定于端口

			// SocketChannel clientChannel = serverChannel.accept();//监听接入的连接
			// clientChannel.configureBlocking(false);//设置客户端通道为非阻塞，允许服务器处理多个并发连连接
			// System.out.println(clientChannel);

			Selector selector = Selector.open();//创建监视通道的选择器selector，对准备好的连接进行循环处理
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);//使用通道的register方法在监视通道的选择器中进行注册
			//使用SelectionKey类中的命名常亮来指定所关注的操作，服务器Socket唯一关心的是OP_ACCEPT：即服务器是否准备好接受一个新连接
			//而客户端通道，希望了解的是是否准备好写入通道。所以使用OP_WRITE
			// SelectionKey key = clientChannel.register(selector, SelectionKey.OP_WRITE);//

			//构建一个只读的byte序列
			byte[] rotation = new byte[95*2];
			for(byte i=' '; i<='~'; i++)
			{
				rotation[i-' '] = i;
				rotation[i+95-' '] = i;
			}

			//select（）检查是否有可操作的数据。对于长时间运行的服务器，这一般要在无限循环中进行
			while(true){
				selector.select();
				//处理选择了的键
				Set readKeys = selector.selectedKeys();
				Iterator iterator = readKeys.iterator();
				while(iterator.hasNext())
				{
					SelectionKey key = (SelectionKey) (iterator.next());
					iterator.remove();//防止处理两次
					try{
						if(key.isAcceptable()){
							ServerSocketChannel server = (ServerSocketChannel) key.channel();//获取到ServerSocket通道
							SocketChannel client = server.accept();//建立连接
							System.out.println("Accept connection from " + client);
							client.configureBlocking(false);//设置为非阻塞
							SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE);
							//为客户端建立缓冲区
							ByteBuffer buffer = ByteBuffer.allocate(74);
							buffer.put(rotation, 0, 72);
							buffer.put((byte) '\r');
							buffer.put((byte) '\n');
							buffer.flip();
							key2.attach(buffer);
						}else if(key.isWritable()){
							SocketChannel client = (SocketChannel) key.channel();
							ByteBuffer buffer = (ByteBuffer) key.attachment();
							if(!buffer.hasRemaining())
							{
								buffer.rewind();
								int first = buffer.get();
								buffer.rewind();
								int position = first - ' ' + 1;
								buffer.put(rotation, position, 72);
								buffer.put((byte) '\r');
								buffer.put((byte) '\n');
								buffer.flip();
							}
							client.write(buffer);
						}
					}catch(IOException e)
					{
						key.cancel();
						try{
							key.channel().close();
						}catch (IOException ee){}
					}
				}
			}//end of while
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}