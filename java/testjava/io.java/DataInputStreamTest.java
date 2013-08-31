import java.io.DataInputStream;
import java.io.IOException;
class DataInputStreamTest{
	public static void main(String[] args)
	{
		try{
			DataInputStream dis = new DataInputStream(System.in);
			byte t = dis.readByte();
			System.out.println(t);

			boolean b = dis.readBoolean();
			System.out.println(b);
		}catch (IOException e)
		{

		}
	}
}