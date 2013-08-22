class DefaultIntAndDouble{
	static int a = 0;
	int b = 0;
	public strictfp static void main(String[] args)
	{
		// int aa=0l;
		// float bb=0.1;
		// System.out.println(aa);
		// System.out.println(bb);
		
		long aa=0;
		System.out.println(aa);
		
		float bb=0.1f;
		System.out.println(bb);

		// short s1 = 1; s1 = s1 + 1;
		// short s1 = 1; s1 = 1 + s1;
		short s1 = 1; s1 += 1;
		System.out.println(s1);
		test.say();
		DefaultIntAndDouble d = new DefaultIntAndDouble();
		d.test();
		d.test(1);
	}

	static class test{
		static void say()
		{
			a =1;
			System.out.println(a);
			// System.out.println(b);
		}
	}

	 final void test()
	{
		System.out.println("yes");
	}

	 final void test(int i)
	{
		System.out.println("o yes!");
	}
}