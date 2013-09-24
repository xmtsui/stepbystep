class PrintPrime{
	public static void main(String[] args)
	{
		int max = 10000;
		for(int i=0; i<max; ++i)
		{
			if(doCheck(i))
				System.out.print(i+" ");
		}
	}

	public static boolean doCheck(int i)
	{
		if(i<0||i>10000)
			return false;
		if(i==1)
			return true;
		for(int j=2; j<i; ++j)
		{
			if(i%j==0)
				return false;
			else
				continue;
		}
		return true;
	}

	public static boolean doCheck(int )
}