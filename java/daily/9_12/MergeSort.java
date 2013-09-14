class MergeSort{
	static void doSort(int[] a)
	{
		doMergeSort(a, 0, a.length-1);
	}

	private static void doMergeSort(int[] a, int s, int e)
	{
		if(a==null)
			return;
		int len = a.length;
		if(s<0 || e>=a.length)
			return;
		
		if(s==e)
			return;
		else
		{
			int mid = (s+e)/2;
			doMergeSort(a, s, mid);
			doMergeSort(a, mid+1, e);
			doMerge(a, s, mid, e);
		}
	}

	private static void doMerge(int[] a, int s, int m, int e)
	{
		if(a==null)
			return;
		int len = a.length;
		if(s<0 || e>=len)
			return;
		int[] tmp = new int[len];
		int i=0, s1=0, s2=0;
		for(i=s,s1=s,s2=m+1; s1<=m&&s2<=e;)
		{
			if(a[s1] < a[s2])
				tmp[i++] = a[s1++];
			else
				tmp[i++] = a[s2++];
		}

		while(s1<=m)
			tmp[i++] = a[s1++];
		while(s2<=e)
			tmp[i++] = a[s2++];

		for(int z=s; z<=e; ++z)
		{
			System.out.print(a[z]);
			a[z] = tmp[z];
		}
		System.out.print("\n");

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