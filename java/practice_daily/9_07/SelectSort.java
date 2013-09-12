public class SelectSort{
	public static void doSort(int[] a)
	{
		if(a == null)
			return;
		int len = a.length;
		for(int i=0; i<len; ++i)
		{
			int min = i;
			for(int j=i+1; j<len; ++j)
			{
				if(a[j] < a[min])
					min = j;
			}
			if(i != min)
				doSwap(i, min, a);
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
		int a[] = {3,4,5,1,2,8,3,5,9};
		doTraverse(a);
		doSort(a);
		doTraverse(a);
	}
}