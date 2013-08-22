class StringTest{
	public static void main(String[] args)
	{
		String str = "a"+"b"+"c";
		String s1 = "a";
		String s2 = "b";
		String s3 = "c";
		String s4 = new String(str+s1);
		System.out.println(str);


		String sss1 = "This is only a";
		String sss2 = " simple";
		String sss3 = " test";
		long start1 = System.currentTimeMillis();
		for(int i=0; i<=10000; ++i)
		{
			// String ss1 = "This is only a" + " simple" + " test";//最快
			String ss1 = sss1 + sss2 + sss3;//最慢
		}
		long end1 = System.currentTimeMillis();
		long time1 = end1 - start1;

		long start2 = System.currentTimeMillis();
		for(int i=0; i<=10000; ++i)
		{
			//中等
			// StringBuffer ss2 = 
				// new StringBuffer("This is only a").append(" simple").append("test");
			StringBuffer ss2 = 
				new StringBuffer(sss1).append(sss2).append(sss3);
		}
		long end2 = System.currentTimeMillis();
		long time2 = end2 - start2;

		System.out.println("For big data, time1: " + time1 + "| time2: "+ time2);

	}
}