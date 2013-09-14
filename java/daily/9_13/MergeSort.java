class MergeSort{
	public static void doSort(int[] a)
	{
		if(a==null)
			return;
		int len = a.length;
		doMergeSort(a, a, 0, len-1);
	}

	private static void doMergeSort(int[] a, int[] t, int s, int e)
	{
		if(a==null)
			return;
		int len = a.length;
		if(s<0||e>=len||s>e)
			return;
		// int[] tmp = new int[len];
		int[] tmp = new int[len];
		if(s==e)
			t[s] = a[s];
		else
		{
			int mid = (s+e)/2;
			doMergeSort(a, tmp, s, mid);
			doMergeSort(a, tmp, mid+1, e);
			doMerge(tmp, t, s, mid, e);
		}
	}

	private static void doMerge(int[] a, int[] t, int s, int m, int e)
	{
		if(a==null)
			return;
		int len = a.length;
		if(s<0||e>=len||s>e||m<s||m>e)
			return;
		int i=0, s1=0, s2=0;
		for(i=s,s1=s,s2=m+1; s1<=m&&s2<=e; )
		{
			if(a[s1] < a[s2])
				t[i++] = a[s1++];
			else
				t[i++] = a[s2++];
		}

		while(s1<=m)
			t[i++] = a[s1++];
		while(s2<=e)
			t[i++] = a[s2++];
	}

	public static void doTraverse(int[] a)
	{
		if(a==null)
			return;
		for(int item : a)
		{
			System.out.print(item+" ");
		}
		System.out.print("\n");

	}
	public static void main(String[] args)
	{
		int[][] a = {
			null,
			{9},
			{9,4,2,5,6,2,1,3,4,8}
		};

		for(int[] item : a)
		{
			doTraverse(item);
			doSort(item);
			doTraverse(item);
		}
	}
}