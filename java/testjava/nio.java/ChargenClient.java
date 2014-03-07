import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.Channels;
class ChargenClient{
	public static void main(String[] args)
	{
		try{
			SocketAddress rama = new InetSocketAddress("localhost", 1119);
			SocketChannel client = SocketChannel.open(rama);
			ByteBuffer buffer = ByteBuffer.allocate(74);
			// int bytesRead = client.read(buffer);
			WritableByteChannel out = Channels.newChannel(System.out);
			while(client.read(buffer) != -1) {
				buffer.flip();
				// System.out.println(buffer);
				out.write(buffer);
				buffer.clear();
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}