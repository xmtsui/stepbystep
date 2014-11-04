import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Channels;
public class NIOClientWrite{
	public NIOClientWrite() throws Exception{
		SocketAddress rama = new InetSocketAddress("10.15.96.59", 8888);
		SocketChannel client = SocketChannel.open(rama);
		ByteBuffer buffer = ByteBuffer.allocate(74);
		ReadableByteChannel in = Channels.newChannel(System.in);
		in.read(buffer);
		buffer.flip();
		client.write(buffer);
		buffer.clear();
	}

	public static void main(String[] args)
	{
		try{
			new NIOClientWrite();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}