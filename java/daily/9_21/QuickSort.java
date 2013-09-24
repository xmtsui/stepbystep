class QuickSort implements Sort{
	static{
		System.out.println("Now quicksort...");
	}
	@Override
	public void doSort(int[] a)
	{
		if(a==null)
			return;
		int len = a.length;
		doQuickSort(a, 0, len-1);
	}

	private void doQuickSort(int[] a, int s, int e)
	{
		if(a==null)
			return;
		int len = a.length;
		if(s<0 || e>=len || s>e)
			return;
		int pivot = 0;
		if(s<e)
		{
			pivot = partition(a, s, e);
			doQuickSort(a, s, pivot-1);
			doQuickSort(a, pivot+1, e);
		}
	}

	public void swap(int[] a, int i, int j)
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

	private int partition(int[] a, int s, int e)
	{
		if(a==null)
			return 0;
		int len = a.length;
		if(s<0 || e>=len || s>e)
			return 0;
		int pivotValue = a[s];
		while(s<e)
		{
			while(s<e&&a[e]>=pivotValue)
				e--;
			swap(a, s, e);
			while(s<e&&a[s]<=pivotValue)
				s++;
			swap(a, s, e);
		}
		return s;
	}
}