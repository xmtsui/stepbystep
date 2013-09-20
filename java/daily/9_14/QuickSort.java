class QuickSort{
	public static void doSort(int[] a)
	{
		if(a==null)
			return;
		int len = a.length;
		doQuickSort(a, 0, len-1);
	}
	private static void doQuickSort(int[] a, int s, int e)
	{
		int pivot = a[0];
		if(s<e)
		{
			pivot = partition(a, s, e);
			doQuickSort(a, s, pivot-1);
			doQuickSort(a, pivot+1, e);
		}
	} 
	private static int partition(int[] a, int s, int e)
	{
		int pivotkey = a[s];
		while(s<e)
		{
			while(s<e && a[e]>=pivotkey)
				e--;
			swap(a, s, e);
			while(s<e && a[s]<=pivotkey)
				s++;
			swap(a, s, e);
		}
		return s;
	}
	private static void swap(int[] a, int i, int j)
	{
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static void doTraverse(int[] a)
	{
		if(a==null)
			return;
		for(int item:a)
		{
			System.out.print(item+" ");
		}
		System.out.println();
	}
	public static void main(String[] args)
	{
		int[][] a = {
			null,
			{9},
			{9,2,5,3,7,4,6,8,1,0}
		};

		for(int[] item : a)
		{
			doTraverse(item);
			doSort(item);
			doTraverse(item);
		}

		int K = 100;
		int[] aa = new int[K];
		for(int i=0; i<K; ++i)
		{
			int r = (int) (1000 * Math.random());
			aa[i] = r;
		}
		doTraverse(aa);
		doSort(aa);
		doTraverse(aa);
	}
}