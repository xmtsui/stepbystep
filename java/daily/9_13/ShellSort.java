class ShellSort{
	public static void doSort(int[] a)
	{
		if(a == null)
			return;
		int len = a.length;
		int inc = 1;
		while(inc <= len/3)
			inc = 3*inc + 1;
		do{
			// for(int i=inc+1; i<len; ++i)
			for(int i=inc; i<len; ++i)
			{
				int tmp = a[i];
				int pos = i-inc;
				if(a[i]<a[i-inc])
				{
					while(pos>=0 && tmp<a[pos])
					{
						a[pos+inc] = a[pos];
						pos-=inc;
					}
				}
				a[pos+inc] = tmp;
			}
			inc = (inc-1)/3;
		}while(inc>=1);
	}
	public static void doTraverse(int[] a)
	{
		if(a==null)
			return;
		for(int item : a)
			System.out.print(item + " ");
		System.out.println();
	}
	public static void main(String[] args)
	{
		int[][] a ={
			null,
			{9},
			{4,9,5,4,2,3,7,8,1,0}
		};

		for(int[] item : a)
		{
			doTraverse(item);
			doSort(item);
			doTraverse(item);
		}
	}
}