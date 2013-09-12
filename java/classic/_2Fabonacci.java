/**
 * Fabonacci problem solved
 * 如果一對兔子每月能生一對小兔（一雄一雌），而每對小兔
 * 在牠出生後的第三個月裡，又能開始生一對小兔，假定在
 * 不發生死亡的情況下，由一對出生的小兔開始，50個月後會有
 * 多少對兔子？
 * 在第一個月時，只有一對小兔子，過了一個月，那對兔子成熟
 * 了，在第三個月時便生下一對小兔子，這時有兩對兔子。再過
 * 多一個月，成熟的兔子再生一對小兔子，而另一對小兔子長大
 * ，有三對小兔子。如此推算下去，我們便發現一個規律： 
 * 1,1,2,3,5,8,13,21,34,55,89,144,233…
 * 
 * 递归优点：实现简单,缺点耗费栈空间，最慢
 * 尾递归：实现简单，速度中等
 * 
 * 非递归优点：速度快
 * 使用辅助内存的话，适于求整个序列（较慢）
 * 不用辅助内存，适于求序列的某一个值（较快）
 *
 * @author xmtsui
 * @version v1.0
 */
import java.math.BigInteger;
class _2Fabonacci{
	/**
	 * 求斐波那契数列的第n项
	 * 递归实现
	 * @param  n 斐波那契数列的下标
	 * @return   指定位置的值
	 */
	static int Fabonacci1(int n)
	{
		if(n <= 0)
			return -1;
		else if(n == 1)
			return 1;
		else if(n == 2)
			return 1;
		else
			return Fabonacci1(n-1) + Fabonacci1(n-2);
	}

	/**
	 * 求斐波那契数列的第n项
	 * 尾递归实现
	 * http://devilkirin.iteye.com/blog/673275
	 * 
	 * @param  n 斐波那契数列的下标
	 * @return   指定位置的值
	 */
	static BigInteger Fabonacci1_(int n)
	{
		if(n <= 0)
			return BigInteger.valueOf(-1);
		else if(n==1 || n==2)
			return BigInteger.valueOf(1);
		else
		{
			// return tail(n, BigInteger.valueOf(1), BigInteger.valueOf(1), 3);
			return tail(n, BigInteger.valueOf(1), BigInteger.valueOf(1));
		}
	}

	private static BigInteger tail(int n, BigInteger b1, BigInteger b2, int begin)
	{
		if(n == begin)
			return b1.add(b2);
		else
			return tail(n, b2, b1.add(b2), begin+1);
	}

	private static BigInteger tail(int n, BigInteger b1, BigInteger b2)
	{
		if(n == 1)
			return b1;
		else
			return tail(n-1, b2, b1.add(b2));
	}
	/**
	 * 求斐波那契数列的第n项
	 * 非递归实现
	 * 
	 * 该实现空间耗费为n,
	 * 可以求整个数列，适合给斐波那契查找
	 * @param  n 斐波那契数列的下标
	 * @return   指定位置的值
	 */
	static BigInteger Fabonacci2(int n)
	{
		if(n <= 0)
			return BigInteger.valueOf(-1);
		else if(n==1 || n==2)
			return BigInteger.valueOf(1);
		else
		{
			BigInteger[] result= new BigInteger[n+1];
			result[0]=BigInteger.valueOf(0);
			result[1]=BigInteger.valueOf(1);
			for(int i=2; i<=n; i++)//注意此处不能写成i<n
			{
				result[i] = result[i-1].add(result[i-2]);
			}
			return result[n];
		}
	}

	/**
	 * 求斐波那契数列的第n项
	 * 非递归实现
	 *
	 * 该实现空间耗费为o(1)
	 * @param  n
	 * @return
	 */
	static BigInteger Fabonacci3(int n)
	{
		if(n<=0)
			return BigInteger.valueOf(-1);
		else if(n == 1 || n == 2)
			return BigInteger.valueOf(1);
		else
		{
			BigInteger res=BigInteger.valueOf(0);
			BigInteger a=BigInteger.valueOf(0);
			BigInteger b=BigInteger.valueOf(1);
			for(int i=2; i<=n; ++i)
			{
				res = a.add(b);
				a = b;
				b = res;
			}
			return res;
		}
	}

	/**
	 * 求斐波那契数列
	 * @param  n 斐波那契数列的下标
	 * @return   数列
	 */
	private static int[] Fabonacci4(int n)
	{
		int[] result= new int[n+1];
		if(n <= 0)
			result = null;
		else if(n==1)
			result[0]=1;
		else if(n==2)
			result[1]=1;
		else
		{
			result[0]=1;
			result[1]=1;
			for(int i=2; i<=n; i++)//注意此处不能写成i<n
			{
				result[i] = result[i-1] + result[i-2];
			}
		}
		return result;
	}

	public static void main(String[] args)
	{
		int N=10;
		for(int i=1; i<N; ++i)
		{
			System.out.print(Fabonacci1(i)+" ");
		}
		System.out.println();
		for(int i=1; i<N; ++i)
		{
			System.out.print(Fabonacci1_(i)+" ");
		}
		System.out.println();
		for(int i=1; i<N; ++i)
		{
			System.out.print(Fabonacci2(i)+" ");
		}
		System.out.println();
		for(int i=1; i<N; ++i)
		{
			System.out.print(Fabonacci3(i)+" ");
		}
		System.out.println();
		for(int item:Fabonacci4(N))
			System.out.print(item+" ");
		System.out.println();
		
		/*效率测试*/
		N=30;
		
		long start1 = System.currentTimeMillis();
		for(int i=1; i<N; i++)
		{
			// System.out.print(Fabonacci1(N)+"|");
			Fabonacci1(N);
		}
		long end1 = System.currentTimeMillis();
		long time1 = end1 - start1;
		System.out.println();

		long start1_ = System.currentTimeMillis();
		for(int i=1; i<N*30; i++)
		{
			// System.out.print(Fabonacci2(N)+"|");
			Fabonacci1_(N);
		}
		long end1_ = System.currentTimeMillis();
		long time1_ = end1_ - start1_;
		System.out.println();

		long start2 = System.currentTimeMillis();
		for(int i=1; i<N*30; i++)
		{
			// System.out.print(Fabonacci2(N)+"|");
			Fabonacci2(N);
		}
		long end2 = System.currentTimeMillis();
		long time2 = end2 - start2;
		System.out.println();

		long start3 = System.currentTimeMillis();
		for(int i=1; i<N*30; i++)
		{
			// System.out.print(Fabonacci3(N)+"|");
			Fabonacci3(N);
		}
		long end3 = System.currentTimeMillis();
		long time3 = end3 - start3;
		System.out.println();

		System.out.println("For big data, time1: " + time1 
			+ "| time1_: "+ time1_
			+ "| time2: "+ time2
			+ "| time3: "+ time3);

		int[] test = Fabonacci4(N);
		for(int item:test)
			System.out.print(item+"|");
			System.out.println();
		}
	}