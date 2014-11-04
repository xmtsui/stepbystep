import java.nio.ByteBuffer;
class ByteBufferTest{
	public static void main(String[] args)
	{
		ByteBuffer bb = ByteBuffer.allocate(10);
		letsSee(bb);
		
		bb.put(new byte[]{48});
		letsSee(bb);
		
		bb.flip();//改变limit
		letsSee(bb);
		
		byte[] b = new byte[1];
		bb.get(b);
		letsSee(bb);
		System.out.println(new String(b));

		bb.rewind();//不改变limit,假设已经设置好了limit
		b = bb.array();
		letsSee(bb);
		b[0] = 49;
		System.out.println(new String(bb.array()));
	}

	static int i = 0;
	private static void letsSee(ByteBuffer bb)
	{
		System.out.println("------------" + i++ + "----------------");
		System.out.print("position: " + bb.position());
		System.out.print("  limit: " + bb.limit());
		System.out.println("  capacity: " + bb.capacity());
	}
}