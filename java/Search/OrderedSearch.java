/**
 * 有序表查询
 *
 * 较完善的二分查找
 * 插值查找
 * 斐波那契查找
 *
 * 次数较多的时候，比如10000次，100000
 * 递归最慢,非递归与插值相近
 * 
 * 再大一点1000000，
 * 递归最慢，非递归中等，插值最快
 *
 * 斐波那契c没有数组重分配问题，所以效率高，java不适用
 * @author xmtsui
 * @version v1.0 2013-08-10
 */
import java.math.BigInteger;
import java.util.Arrays;
class OrderedSearch{
	/**
	 * 二分查找入口
	 * @param  seq        [description]
	 * @param  key        [description]
	 * @param  regression [description]
	 * @return            [description]
	 */
	private static int BinarySearch(int[] seq, int key, boolean regression)
	{
		if(seq == null)
			return -1;
		if(regression)
			return doBinarySearch(seq, key, 0, seq.length-1);
		else
			return doBinarySearch(seq, key);
	}

	/**
	 * 递归
	 * 
	 * 注意：如果seq没有排序，则结果不确定
	 * 如果有重复值，返回哪一个也不定
	 * @param  seq  [description]
	 * @param  key  [description]
	 * @param  low  [description]
	 * @param  high 注意传入的时候考虑边界
	 * @return      &gt;=0的任何表示找到，未找到返回-1
	 */
	private static int doBinarySearch(int[] seq, int key, int low, int high)
	{
		//检查边界条件
		if(low<0 || high>seq.length-1)
		{
			System.err.println("ERROR: 边界出错:[0~" + (seq.length-1) + "]");
			return -1;
		}
		
		//优化查询
		if(key<seq[0] || key>seq[high])
			return -1;
		
		//折半查询
		int medium = (low+high)/2;
		if(low<=high)
		{
			if(seq[medium] > key)
				return doBinarySearch(seq, key, low, medium-1);
			else if(seq[medium] < key)
				return doBinarySearch(seq, key, medium+1, high);
			else
				return medium;
		}
		else
			return -1;
	}

	/**
	 * 非递归
	 * 
	 * 注意：如果seq没有排序，则结果不确定
	 * 如果有重复值，返回哪一个也不定
	 * @param  seq [description]
	 * @param  key [description]
	 * @return     &gt;=0的任何表示找到，未找到返回-1
	 */
	private static int doBinarySearch(int[] seq, int key)
	{
		int low=0, high=seq.length-1;

		//优化key不在范围内的时候的查找
		if(key<seq[0] || key>seq[high])
			return -1;
		
		//折半查询
		while(low<=high)
		{
			// int medium=(low+high)/2;
			int medium=(low+high)>>>1;
			if(seq[medium] > key)
				high = medium - 1;//注意是medium-1,不是high-1
			else if(seq[medium] < key)
				low = medium + 1;//注意是medium+1,不是low+1
			else
				return medium;
		}
		return -1;
	}

	/**
	 * 插值查找
	 * 
	 * 注意：如果seq没有排序，则结果不确定
	 * 如果有重复值，返回哪一个也不定
	 * @param  seq [description]
	 * @param  key [description]
	 * @return     [description]
	 */
	private static int InterpolationSearch(int[] seq, int key)
	{
		int low=0, high=seq.length-1;

		//优化key不在范围内的时候的查找
		if(key<seq[0] || key>seq[high])
			return -1;
		
		//插值查找
		while(low<=high)
		{
			//防止除数为0
			if(seq[high] == seq[low])
			{
				if(key == seq[high])
					return high;
				else
					return -1;
			}

			/*防止溢出代码*/
			// BigInteger op1 = BigInteger.valueOf(high-low);
			// BigInteger op2 = BigInteger.valueOf(key-seq[low]);
			// BigInteger op3 = BigInteger.valueOf(seq[high]-seq[low]);
			// BigInteger op4 = op1.multiply(op2).divide(op3);
			// int mid = low + op4.intValue();
			
			//数字太大可能溢出
			int mid = low + (high-low) * (key-seq[low]) / (seq[high]-seq[low]);
			// System.out.println("mid: " + mid);
			if(seq[mid] > key)
				high = mid-1;
			else if(seq[mid] < key)
				low = mid+1;
			else
				return mid;
		}
		return -1;
	}

	/**
	 * 斐波那契查找
	 * 
	 * 因为Arrays.copyOf效率不高
	 * @param  seq [description]
	 * @param  key [description]
	 * @param  f   [description]
	 * @return     [description]
	 */
	private static int FabonacciSearch(int[] seq, int key, int[] f)
	{
		int low=0, high=seq.length-1;
		int k=0;
		while(high > f[k]-1)
			k++;

		int[] tmp = Arrays.copyOf(seq, f[k]-1);

		for(int i=high; i<f[k]-1; ++i)
		{
			tmp[i] = seq[high];
		}

		while(low <= high)
		{
			int mid = low + f[k-1] - 1;
			if(key < tmp[mid])
			{
				high = mid-1;
				k = k-1;
			}
			else if(key > tmp[mid])
			{
				low = mid+1;
				k = k-2;
			}
			else
			{
				if(mid <= high)
					return mid;
				else
					return high;
			}
		}
		return -1;
	}

	/**
	 * 非递归
	 * @param  n 斐波那契数列的下标
	 * @return   指定位置的值
	 */
	private static int Fabonacci(int n)
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
		int[] seq = {3,5,7,9,12,23,34,56};

		System.out.println("---case1 二分----");
		System.out.println("二分递归:" + BinarySearch(null, 1, true));
		System.out.println("二分非递归:" + BinarySearch(null, 1, false));

		System.out.println("---case2 二分----");
		System.out.println("二分递归" + BinarySearch(seq, 1, true));
		System.out.println("非递归" + BinarySearch(seq, 1, false));

		System.out.println("---case3 二分----");
		System.out.println("二分递归" + BinarySearch(seq, 23, true));
		System.out.println("二分非递归" + BinarySearch(seq, 23, false));

		System.out.println("---case1 插值----");
		System.out.println("插值：" + InterpolationSearch(seq, 23));

		int N=50;
		int[] f = new int[50];
		for(int i=0; i<N; i++)
		{
			f[i] = Fabonacci(i);
		}

		System.out.println("---case1 fabonacci----");
		System.out.println("插值：" + FabonacciSearch(seq, 23, f));

		int kb=1024;
		int mb=1024*1024;
		int MAX=kb*10;//10kb
		// int MAX=mb*10;//10mb 此时插值公式整型溢出
		int[] seq_big = new int[MAX];//10mb*4byte=40mb
		for(int i=0; i<MAX; i++)
		{
			seq_big[i] = i;
		}

		System.out.println("---case5：big----");
		long start1 = System.currentTimeMillis();
		for(int i=0; i<100000; i++){
			// System.out.println("二分递归:" + BinarySearch(seq_big, MAX-1,true));
			// BinarySearch(seq_big, MAX-1,true);
			BinarySearch(seq_big, MAX-MAX/2,true);
		}	
		long end1 = System.currentTimeMillis();
		long time1 = end1 - start1;

		long start2 = System.currentTimeMillis();
		for(int i=0; i<100000; i++){
			// System.out.println("二分非递归:" + BinarySearch(seq_big, MAX-1,false));
			// BinarySearch(seq_big, MAX-1,false);
			BinarySearch(seq_big, MAX-MAX/2,false);
		}	
		long end2 = System.currentTimeMillis();
		long time2 = end2 - start2;

		long start3 = System.currentTimeMillis();
		for(int i=0; i<100000; i++){
			// System.out.println("插值:" + InterpolationSearch(seq_big, MAX/2));
			// InterpolationSearch(seq_big, MAX-1);
			InterpolationSearch(seq_big, MAX-MAX/2);
		}
		long end3 = System.currentTimeMillis();
		long time3 = end3 - start3;

		long start4 = System.currentTimeMillis();
		for(int i=0; i<100000; i++){
			// System.out.println("插值:" + FabonacciSearch(seq_big, MAX/2));
			// InterpolationSearch(seq_big, MAX-1);
			FabonacciSearch(seq_big, MAX-MAX/2, f);
		}
		long end4 = System.currentTimeMillis();
		long time4 = end4 - start4;

		System.out.println("For big data, time1: " + time1 
			+ "| time2: " + time2 
			+ "| time3: " + time3
			+ "| time4: " + time4
			);
	}
}