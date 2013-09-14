class MergeSort{
	public static void doSort(int[] a)
	{

	}

	private static void doMergeSort(int[] a, int start, int end)
	{
		int mid = (start+end)/2;
		doMergeSort(a, start, mid-1);
		doMergeSort(a, mid, end);
		doMerge(a, start, mid, end);
	}

	private static void doMerge(int[] a, int start, int mid, int end)
	{
		
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