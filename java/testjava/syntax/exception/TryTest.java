public class TryTest{
	static void testA() throws RuntimeException
	{
		try{
			int i = 0;
			throw new RuntimeException();
		}finally{
			System.out.println("now finnaly.");
		}
	}

	public static void main(String[] args)
	{
		try{
			testA();
		}catch (Throwable e)
		{
			System.err.println("now exception2.");
		}
		System.out.println("now end.");
	}
}