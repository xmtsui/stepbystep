class TestSomeThing2{
	public static void main(String[] args)
	{
		byte blockSize = 127;
		short dataLen = 20;
		int test = (int) (blockSize-(dataLen % blockSize));
		System.out.println(test);
	}
}