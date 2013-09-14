public class InsertSort{
	public static void doSort1(int[] a)
	{
		if(a == null)
			return;
		int len = a.length;
		for(int i=1; i<len; ++i)
		{
			if(a[i] < a[i-1])
			{
				int pos = i-1;
				int tmp = a[i];
				// int pos = i;
				// while(pos>0 && a[pos]<a[pos-1])//注意！！！易犯错误，写成pos比较pos-1
				// while(pos>0 && tmp<a[pos-1])//应该是>=0
				// {
				// 	a[pos] = a[pos-1];//注意比较错误
				// 	pos--;
				// }
				while(pos>=0 && tmp<a[pos])//
				{
					a[pos+1] = a[pos];
					pos--;
				}
				a[pos+1] = tmp;
			}
		}
	}

	public static void doSort(int[] a)
	{
		if(a==null)
			return;
		int len = a.length;
		for(int i=1; i<len; ++i)
		{
			if(a[i] < a[i-1])
			{
				int tmp = a[0];
				a[0] = a[i];
				int index = i-1;
				while(a[0]<a[index])
				{
					a[index+1] = a[index];
					index--;
				}
				if(index==0 && a[index]<tmp)
				{
					a[1] = tmp;
				}
				else if(index==0 && a[index]>=tmp)
				{
					a[1] = a[0];
					a[0] = tmp;
				}
				else
				{
					a[index] = a[0];
					a[0] = tmp;
 				}
			}
		}
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