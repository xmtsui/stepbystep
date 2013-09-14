class QuickSort{
	public static void doSort(int[] a)
	{
		if(a==null)
			return;
		int len = a.length;
		doQuickSort(a, 0, len-1);
	}

	public static void doQuickSort(int[] a, int s, int e)
	{
		if(a==null)
			return;
		int len = a.length;
		int pivot;
		if(s < e)
		{
			pivot = Partition(a, s, e);
			doQuickSort(a, s, pivot-1);
			doQuickSort(a, pivot+1, e);
		}
	}

	private static int Partition(int[] a, int s, int e)
	{
		int pivotValue = a[s];
		while(s < e)
		{
			while(s<e && a[e]>=pivotValue)
				e--;
			Swap(a, s, e);
			while(s<e && a[s]<=pivotValue)
				s++;
			Swap(a, s, e);
		}
		return s;
	}

	public static void doTraverse(int[] a)
	{
		if(a==null)
			return;
		for(int item : a)
			System.out.print(item + " ");
		System.out.println();
	}


	private static void Swap(int[] a, int i, int j)
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
			{1},
			{9,4,2,5,6,0,1,2,4,3,7}
		};

		for(int[] item : a)
		{
			doTraverse(item);
			doSort(item);
			doTraverse(item);
		}
	}
}