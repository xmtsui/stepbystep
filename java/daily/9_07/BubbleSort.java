public class BubbleSort{
	public static void doSort(int[] a)
	{
		if(a == null)
			return;
		int len = a.length;
		boolean endFlag = false;
		for(int i=0; i<len; ++i)
		{
			endFlag =true;
			for(int j=len-1; j>i; --j)
			{
				if(a[j] < a[j-1])
				{
					endFlag = false;
					doSwap(j, j-1, a);
				}
			}
			if(endFlag)
				break;
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