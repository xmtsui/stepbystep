public class NumSwap{
	public static void doSwap(int[] a)
	{
		if(a==null)
			return;
		int len = a.length;
		int[] b = new int[len];
		int s1=0, s2=len-1;
		for(int i=0; i<len; ++i)
		{
			if(a[i]>=0)
				b[s1++] = a[i];
			else
				b[s2--] = a[i];
		}
		System.arraycopy(b,0,a,0,len);
	}

	public static void doSwap1(int[] a)
	{
		if(a==null)
			return;
		int len = a.length;
		int s = 0, e = len-1;
		while(s<e)
		{
			// while(a[s]>=0)//典型错误
			while(s<e && a[s]>=0)
				s++;
			swap(a,s,e);
			// while(a[e]<0)
			while(s<e && a[e]<0)
				e--;
			swap(a,s,e);
		}
	}

	public static void swap(int[] a, int i, int j)
	{
		if(a==null)
			return;
		if(i!=j)
		{
			a[i] ^= a[j];
			a[j] ^= a[i];
			a[i] ^= a[j];
		}
	}
	public static void main(String[] args)
	{
		int[] a = {-3,2,3,4,0,-1,2,3,-3,-4,-7};
		doSwap(a);
		for(int item : a)
		{
			System.out.print(item+" ");
		}
		System.out.println();
	}
}