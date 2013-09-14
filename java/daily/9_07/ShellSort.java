class ShellSort{
	public static void doSort(int[] a)
	{
		if(a == null)
			return;
		int len = a.length;
		int increment = 1;
		// while(increment<len)//注意错误
		while(increment<=len/3)
			increment = increment*3 + 1;
		do{
			for(int i=increment+1; i<len; ++i)//注意错误！！！
			{
				if(a[i] < a[i-increment])
				{
					int pos = i-increment;
					int tmp = a[i];
					while(pos>=0 && tmp < a[pos])
					{
						a[pos+increment] = a[pos];
						pos -= increment;
					}
					a[pos+increment] = tmp;
				}
			}
			increment = (increment-1)/3;
		}while(increment>=1);
	}

	private static void doSwap(int i, int j, int[] a)
	{
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}


	public static void doTraverse(int[] a)
	{
		if(a == null)
			return;
		for(int item : a)
		{
			System.out.print(item);
		}
		System.out.println();
	}
	public static void main(String[] args)
	{
		int a[] = {3,4,5,1,2,8,3,5,9,0,2};
		doTraverse(a);
		doSort(a);
		doTraverse(a);

		int[][] b ={
			null,
			{9},
			{4,9,5,4,2,3,7,8,1,0}
		};

		for(int[] item : b)
		{
			doTraverse(item);
			doSort(item);
			doTraverse(item);
		}
	}
}