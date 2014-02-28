class TestFinal{
	public static void main(String[] args)
	{
		final String test1 = "test1";
		final StringBuilder test2 = new StringBuilder("test2");
		final StringBuilder test3 = new StringBuilder("test3");
		// test1 = "test2";
		test2.append("xxx");
		// test2 = test3;
		System.out.println(test1);
		System.out.println(test2);
	}
}