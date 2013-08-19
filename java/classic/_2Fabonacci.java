/**
 * Fabonacci problem solved
 * 递归与非递归
 *
 * @author xmtsui
 * @version v1.0
 */
class _2Fabonacci{
	/**
	 * 递归
	 * @param  n 斐波那契数列的下标
	 * @return   指定位置的值
	 */
	static int Fabonacci1(int n)
	{
		if(n < 0)
			return -1;
		else if(n == 0)
			return 0;
		else if(n == 1)
			return 1;
		else
			return Fabonacci1(n-1) + Fabonacci1(n-2);
	}

	/**
	 * 非递归
	 * @param  n 斐波那契数列的下标
	 * @return   指定位置的值
	 */
	static int Fabonacci2(int n)
	{
		if(n < 0)
			return -1;
		else if(n==0)
			return 0;
		else if(n==1)
			return 1;
		else
		{
			int[] result= new int[n+1];
			result[0]=0;
			result[1]=1;
			for(int i=2; i<=n; i++)//注意此处不能写成i<n
			{
				result[i] = result[i-1] + result[i-2];
			}
			return result[n];
		}
	}

	public static void main(String[] args)
	{
		int N=30;
		
		long start1 = System.currentTimeMillis();
		for(int i=-1; i<N; i++)
		{
			System.out.print(Fabonacci1(i)+"|");
			// Fabonacci1(i);
		}
		long end1 = System.currentTimeMillis();
		long time1 = end1 - start1;

		System.out.println();

		long start2 = System.currentTimeMillis();
		for(int i=-1; i<N; i++)
		{
			System.out.print(Fabonacci2(i)+"|");
			// Fabonacci2(i);
		}
		long end2 = System.currentTimeMillis();
		long time2 = end2 - start2;

		System.out.println();
		System.out.println("For big data, time1: " + time1 + "| time2: "+ time2);
	}
}