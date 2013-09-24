class MergeSort implements Sort{
	@Override
	public void doSort(int[] a)
	{
		if(a==null)
			return;
		int len = a.length;
		doMergeSort(a, a, 0, len-1);
	}

	private void doMergeSort(int[] a, int[] t, int s, int e)
	{
		if(a==null)
			return;
		int len = a.length;
		int[] tmp = new int[len];
		if(s==e)
			t[s] = a[s];
		else
		{
			int mid = (s+e)/2;
			doMergeSort(a, tmp, s, mid);
			doMergeSort(a, tmp, mid+1, e);
			merge(tmp, t, s, mid, e);
		}
	}

	private void merge(int[] a, int[] t, int s, int m, int e)
	{
		if(t==null||a==null)
			return;
		int len = a.length;
		if(s<0||e>=len||s>e||m<s||m>e)
			return;
		int i=0, s1=0, s2=0;

		for(i=s,s1=s,s2=m+1; s1<=m&&s2<=e; )
		{
			if(a[s1]<=a[s2])
				t[i++]=a[s1++];
			else
				t[i++]=a[s2++];
		}

		while(s1<=m)
			t[i++]=a[s1++];
		while(s2<=e)
			t[i++]=a[s2++];
	}
}