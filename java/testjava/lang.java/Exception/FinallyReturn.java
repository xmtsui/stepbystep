class FinallyReturn{
	public static void main(String[] args)
	{
		System.out.println("res: " + doit());
	}

	static int doit()
	{
		int a = 0;
		try{
			a = 1;
			System.out.println(a);
			return a;
		}catch(Exception e)
		{
			a = 2;
			return a;
		}finally{
			a = 3;
			System.out.println(a);
		}
	}
}