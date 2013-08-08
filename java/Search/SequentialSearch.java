/**
 * 顺序查找
 *
 * 比较效率的时候，注意查找顺序一致
 * @author xmtsui
 * @version v1.0 2013-08-08
 */
class SequentialSearch{
	/**
	 * 无哨兵顺序查找，seq为数组，key为要查找的关键字
	 * @param  seq [description]
	 * @param  key [description]
	 * @return     [description]
	 */
	private static int doSeqSearch(int[] seq, int key)
	{
		if(seq == null)
			return -1;
		for(int i=0; i<seq.length; ++i)
		{
			if(seq[i] == key)
				return i;//找到返回index
		}
		return -1;//未找到返回-1
	}

	/**
	 * 有哨兵顺序查找,从小到大
	 * 10m的时候，效率不如前者高
	 * Sentry: n.哨兵；警卫
	 * @param  seq [description]
	 * @param  key [description]
	 * @return     [description]
	 */
	private static int doSeqSearchWithSentry(int[] seq, int key)
	{
		int tmp=0;
		if(seq == null)
			return -1;
		if(seq[seq.length-1] == key)
			return seq.length-1;
		else
		{
			tmp = seq[seq.length-1];
			seq[seq.length-1] = key;
		}
		int i=0;
		while(seq[i] != key)
			++i;
		seq[seq.length-1] = tmp;
		if(i==seq.length-1)
			return -1;
		else
			return i;
	}

	private static int doBinarySearch(int[] seq, int key)
	{
		if(seq == null)
			return -1;
		int low=0;
		int high=seq.length;
		while(low <= high)
		{
			int medium=(low+high)/2;
			if(key>seq[medium])
				low=medium+1;
			else if(key<seq[medium])
				high=medium-1;
			else
				return medium;
		}
		return -1;
	}

	private static int doBinarySearch_regression(int[] seq, int key, int low, int high)
	{
		if(seq == null)
			return -1;
		//检测边界参数
		if(low>high)
			return -1;
		//查找key,若比最小值小，或者最大值大，则查找失败，返回－1
		if(key<seq[low] || key>seq[high])
			return -1;
		//折半递归查找
		int medium=(low+high)/2;
		if(key>seq[medium])
			return doBinarySearch_regression(seq, key, low+1, high);
		else if(key<seq[medium])
			return doBinarySearch_regression(seq, key, low, high-1);
		else
			return medium;
	}

	public static void main(String[] args)
	{
		int[] seq = {3,5,7,9,12,23,34,56};
		System.out.println("---case1----");
		System.out.println("index is:" + doSeqSearch(null, 1));
		System.out.println("index is:" + doSeqSearchWithSentry(null, 1));
		System.out.println("index is:" + doBinarySearch(null, 1));
		System.out.println("index is:" + doBinarySearch_regression(null, 1, 0, seq.length-1));

		System.out.println("---case2----");
		System.out.println("index is:" + doSeqSearchWithSentry(seq, 1));
		System.out.println("index is:" + doSeqSearch(seq, 1));
		System.out.println("index is:" + doBinarySearch(seq, 1));
		System.out.println("index is:" + doBinarySearch_regression(seq, 1, 0, seq.length-1));

		System.out.println("---case3----");
		System.out.println("index is:" + doSeqSearch(seq, 23));
		System.out.println("index is:" + doSeqSearchWithSentry(seq, 23));
		System.out.println("index is:" + doBinarySearch(seq, 23));
		System.out.println("index is:" + doBinarySearch_regression(seq, 23, 0, seq.length-1));

		// 2147483647
		// 1000000000
		// 1000000
		int kb=1024;
		int mb=1024*1024;
		int MAX=mb*10;//10mb
		// int MAX=kb*8;//8kb，递归栈溢出
		// int MAX=kb*4;//4kb，递归栈不溢出
		int[] seq_big = new int[MAX];//1mb*4byte=4mb
		for(int i=0; i<MAX; i++)
		{
			seq_big[i] = i;
		}
		long start1 = System.currentTimeMillis();
		System.out.println("big seq, index is:" + doSeqSearch(seq_big, MAX-2));
		long end1 = System.currentTimeMillis();
		long time1 = end1 - start1;

		long start2 = System.currentTimeMillis();
		System.out.println("big seq, index is:" + doSeqSearchWithSentry(seq_big, MAX-2));
		long end2 = System.currentTimeMillis();
		long time2 = end2 - start2;

		long start3 = System.currentTimeMillis();
		System.out.println("big seq, index is:" + doBinarySearch(seq_big, MAX-2));
		long end3 = System.currentTimeMillis();
		long time3 = end3 - start3;

		long start4 = System.currentTimeMillis();
		// System.out.println("big seq, index is:" + doBinarySearch_regression(seq_big, MAX-2, 0, MAX-1));
		long end4 = System.currentTimeMillis();
		long time4 = end4 - start4;

		System.out.println("For big data, time1: " + time1 + "| time2: "+ time2 + "| time3: " + time3 + "| time4: "+ time4);
	}
}