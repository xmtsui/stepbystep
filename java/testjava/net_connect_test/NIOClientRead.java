import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.Channels;
public class NIOClientRead{
	public NIOClientRead() throws Exception{
		SocketAddress rama = new InetSocketAddress("localhost", 8888);
		SocketChannel client = SocketChannel.open(rama);
		// client.configureBlocking(false);
		ByteBuffer buffer = ByteBuffer.allocate(74);
		WritableByteChannel out = Channels.newChannel(System.out);
		int num = 0;
			System.out.println("test1");
		while((num = client.read(buffer)) != -1) {
			System.out.println("test2");
			System.out.println("Client read num : " + num);
			buffer.flip();
			out.write(buffer);
			buffer.clear();
		}
	}

	public static void main(String[] args)
	{
		try{
			new NIOClientRead();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}