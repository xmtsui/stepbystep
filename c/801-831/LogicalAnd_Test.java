class LogicalAnd_Test{
	public static void main(String[] args)
	{
		test t = new test();
		if(true)
			System.out.println("1&1: " + (1&1));

		int i = 9;
		System.out.println("i&1: " + (i&1) + " | i: " + i);
		i&=1;
		System.out.println("i&1: " + (i&1) + " | i: " + i);
		System.out.println("null instance of: " + (t instanceof Object));
		System.out.println("null instance of: " + (null instanceof Object));
	}
}