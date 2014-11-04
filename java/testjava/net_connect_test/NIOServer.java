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
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
public class NIOServer{
	private ServerSocketChannel serverChannel;

	public NIOServer() throws Exception{
		serverChannel = ServerSocketChannel.open();//创建一个新的ServerChannel对象
		serverChannel.configureBlocking(false);//设置服务端通道为非阻塞
		ServerSocket ss = serverChannel.socket();//获取Socket对象
		ss.bind(new InetSocketAddress(8888));//将通道绑定于端口
		Selector selector = Selector.open();//创建监视通道的选择器selector，对准备好的连接进行循环处理
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);//使用通道的register方法在监视通道的选择器中进行注册
		System.out.println("SelectionKey.OP_ACCEPT : " + SelectionKey.OP_ACCEPT);
		System.out.println("SelectionKey.OP_READ : " + SelectionKey.OP_READ);
		System.out.println("SelectionKey.OP_WRITE : " + SelectionKey.OP_WRITE);
		System.out.println("SelectionKey.OP_CONNECT : " + SelectionKey.OP_CONNECT);
		//构建一个只读的byte序列
		byte[] rotation = new byte[95*2];
		for(byte i=' '; i<='~'; i++)
		{
			rotation[i-' '] = i;
			rotation[i+95-' '] = i;
		}
		String test = "Hello World";

		while(true){
			selector.select(1000);
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
							System.out.println("Server valid ops : " + server.validOps());
							System.out.println("Client valid ops : " + client.validOps());
							System.out.println("Accept connection from " + client);
							client.configureBlocking(false);//设置为非阻塞
							// SelectionKey key3 = client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
							SelectionKey key2 = client.register(selector, SelectionKey.OP_READ);
							//为客户端建立缓冲区
							ByteBuffer buffer = ByteBuffer.allocate(74);
							buffer.put(test.getBytes());
							// buffer.put(rotation, 0, 72);
							buffer.put((byte) '\r');
							buffer.put((byte) '\n');
							buffer.flip();
							key2.attach(buffer);
						}

						if(key.isWritable()){
							SocketChannel client = (SocketChannel) key.channel();
							ByteBuffer buffer = (ByteBuffer) key.attachment();
							// if(!buffer.hasRemaining())
							// {
							// 	// buffer.rewind();
							// 	// int first = buffer.get();
							// 	// buffer.rewind();
							// 	int position = first - ' ' + 1;
							// 	buffer.put(rotation, position, 72);
							// 	buffer.put((byte) '\r');
							// 	buffer.put((byte) '\n');
							// 	buffer.flip();
							// }
							client.write(buffer);
							client.close();
						}

						if(key.isReadable()){
							SocketChannel client = (SocketChannel)key.channel();
							ByteBuffer buffer = (ByteBuffer) key.attachment();
							buffer.clear();
							int num = 0;
							WritableByteChannel out = Channels.newChannel(System.out);
							while((num = client.read(buffer)) != -1)
							{
								System.out.println("Server read num: " + num);
								buffer.flip();
								out.write(buffer);
								buffer.clear();
							}
							key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
							client.close();
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
		}

		public static void main(String[] args)
		{
			try{
				new NIOServer();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}