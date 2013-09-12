class HeapSort{
	public static void doSort(int[] a)
	{
		if(a == null)
			return;
		int len = a.length;
		
		for(int i=len/2-1; i>=0; --i)
			adjustHeap(a, i, len);
		
		for(int i=len-1; i>0; --i)
		{
			doSwap(0, i, a);
			adjustHeap(a, 0, i-1);
		}
		
	}

	private static void adjustHeap(int[] a, int i, int len)
	{
		if(a==null)
			return;
		if(i<0 || i>len || len<0)
			return;
		int tmp = a[i];
		for(int j=(2*i+1); j<len; j=(2*j+1))
		{
			if(j<len-1 && a[j]<a[j+1])
				j++;
			if(tmp >= a[j])
				break;
			a[i] = a[j];
			i = j;
		}
		a[i] = tmp;
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