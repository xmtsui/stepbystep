class StringSplitTest{
	public static void main(String[] args)
	{
		String test = ",, test test";
		String[] test_ = test.split(",");
		System.out.println(test_.length);
		for(String item : test_)
		{
			System.out.println("!" + item + ".");
			String[] test__ = item.split(" ");
			for(String item_ : test__)
			{
				System.out.println("	!" + item_ + ".");
			}
		}

		String test1 = "test test  test   test";
		String[] test1_ = test1.split(" ");
		for(String item : test1_)
		{
			System.out.println(item.trim() + " : " + item.trim().length());
		}
	}
}