class HeapSortAgain{
	public static void doSort(int[] a)
	{
		if(a==null)
			return;
		int len = a.length;
		for(int i=len/2-1; i>=0; --i)
		{
			adjustHeap(i, len-1, a);
		}

		for(int i=len-1; i>=0; --i)
		{
			doSwap(0, i, a);
			adjustHeap(0, i-1, a);
		}
	}

	public static void adjustHeap(int s, int e, int[] a)
	{
		if(a==null)
			return;
		if(s<0 || s>=e || e<0 || e>=a.length)
			return;
		int tmp = a[s];
		for(int i=(2*s+1); i<=e; i=(2*i+1))
		{
			// if(a[i] > a[i+1]) //易错
			// if(i<e&&a[i] > a[i+1])
				i++;
			if(tmp < a[i])
				break;
			a[s] = a[i];
			s = i;
		}
		a[s] = tmp;

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
		int a[] = {3,4,5,1,2,8,3,5,1,0};
		doTraverse(a);
		doSort(a);
		doTraverse(a);
	}
}