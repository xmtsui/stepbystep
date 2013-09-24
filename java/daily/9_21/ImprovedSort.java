public class ImprovedSort{
	public static void main(String[] args)
	{
		int a[][] = {
			null,
			{1},
			{9,1,8,2,7,3,6,4,8,2,7,3,6,4,5}
		};
		// SortFactory sf = new QuickSortFactory();
		// SortFactory sf = new HeapSortFactory();
		// SortFactory sf = new ShellSortFactory();
		SortFactory sf = new MergeSortFactory();
		Sort s = sf.createSort();
		for(int i=0; i<a.length; ++i)
		{
			s.doSort(a[i]);
			if(a[i]!=null)
				for(int item : a[i])
					System.out.print(item + " ");
			System.out.println();
		}
	}
}