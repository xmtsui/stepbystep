class HeapSort implements Sort{
	static{
		System.out.println("Now heapsort...");
	}
	@Override
	public void doSort(int[] a)
	{
		if(a==null)
			return;
		int len = a.length;
		for(int i=len/2; i>=0; --i)
		{
			adjustHeap(a, i, len-1);
		}

		for(int i=len-1; i>=0; --i)
		{
			swap(a, 0, i);
			adjustHeap(a, 0, i-1);
		}
	}

	private void swap(int[] a, int i, int j)
	{
		if(a==null)
			return;
		int len = a.length;
		if(i<0 || j>=len || i>j)
			return;
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private void adjustHeap(int[] a, int s, int e)
	{
		if(a==null)
			return;
		int len = a.length;
		if(s<0 || e>=len || s>e)
			return;
		int tmp = a[s];
		for(int i=(2*s+1); i<=e; i=(2*i+1))
		{
			if(i<=e-1&&a[i]<a[i+1])
				i++;
			if(tmp > a[i])
				break;
			a[s] = a[i];
			s = i;
		}
		a[s] = tmp;
	}
}