class MergeSortAgain{
	private static int index=0;
	public static void doSort(int[] a)
	{
		if(a==null)
			return;
		int len = a.length;
		System.out.print(" a: \t{ "+ a + "}\n");
		doMergeSort(a, a, 0, len-1);
	}

	private static void doMergeSort(int[] a, int[] t, int start, int end)
	{
		if(a==null)
			return;
		int len = a.length;
		int[] tmp = new int[len];
		if(start<0 || end >=len)
			return;
		if(start == end)
		{
			// System.out.println(start+" " + end+ " " + t[start]+" "+a[start]);
			t[start] = a[start];
			// return;
		}
		else
		{
			int mid = (start+end)/2;
			System.out.print("from " + start + " to " + mid + "\n");
			doMergeSort(a, tmp, start, mid);
			System.out.print("from " + (mid+1) + " to " + end + "\n");
			doMergeSort(a, tmp, mid+1, end);
			
			System.out.print("Now merge... [" + start +" | " + mid + " | " + end + "]\n");
			/*see it 2*/
			System.out.print("_"+ index +"_ : ");
			System.out.print(" { "+ t + "}\t from " + start + " to " + end + " ");
			for(int i=start; i<=end; ++i)
				System.out.print(a[i]);
			System.out.print("\n");

			doMerge(tmp, t, start, mid, end);
			/*see it
			System.out.print("_"+ index++ +"_ : ");
			System.out.print(" { "+ t + "}\t ");
			for(int item : t)
				System.out.print(item);
			System.out.print("\n");*/

			
		}
	}

	private static void doMerge(int[] a, int[] t, int s, int m, int e)
	{
		int i=0, s1=0, s2=0;
		for(i=s,s1=s,s2=m+1; s1<=m&&s2<=e;)
		{
			if(a[s1]<a[s2])
				t[i++] = a[s1++];
			else
				t[i++] = a[s2++];
		}
		while(s1<=m)
			t[i++] = a[s1++];
		while(s2<=e)
			t[i++] = a[s2++];
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