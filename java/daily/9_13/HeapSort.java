class HeapSort{
	public static void doHeapSort(int[] a)
	{
		if(a==null)
			return;
		int len = a.length;
		for(int i=len/2; i>=0; --i)
			adjustHeap(a, 0, len-1);
		for(int i=len-1; i>0; --i)
		{
			swap(a, 0, i);
			adjustHeap(a, 0, i-1);
		}
	}
	private static void adjustHeap(int[] a, int s, int e)
	{
		if(a==null)
			return;
		int len = a.length;
		if(s<0||e>=len||s>e)
			return;
		int tmp = a[s];
		for(int i=(2*s+1); i<=e; i=(2*i+1))//<=
		{
			if(i<=e-1 && a[i]<a[i+1])//<=
				i++;
			// if(a[s]>=a[i])//!!!严重错误，注意是tmp比较，a[s]随时在变
			if(tmp>=a[i])
				break;
			a[s] = a[i];
			s = i;
		}
		a[s] = tmp;
	}

	public static void doTraverse(int[] a)
	{
		if(a==null)
			return;
		int len = a.length;
		for(int i=0; i<len; ++i)
		{
			System.out.print(a[i] + " ");
		}
		System.out.print("\n");
	}

	private static void swap(int[] a, int i, int j)
	{
		if(a==null)
			return;
		int len = a.length;
		if(i<0||j<0||i>=len||j>=len)
			return;
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static void main(String[] args)
	{
		int[][] a = {
			null,
			{9},
			{9,4,2,3,1,2,3,7,4,2,3,1,2,3,7,4,2,3,1,2,3,7,4,2,3,1,2,3,7,4,2,3,1,2,3,7},
		};

		for(int[] item : a)
		{
			doTraverse(item);
			doHeapSort(item);
			doTraverse(item);
		}
	}
}