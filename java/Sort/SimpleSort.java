/**
 * 简单排序算法：冒泡，简单选择(选择最小交换)，插入(直接插入有序)
 * (三个都是内部排序，且稳定的)
 * 
 * 结论分析：
 * 冒泡：
 * 1024之内的排序，bubble1,bubble2比bubble0要好
 * 1024-1024*5之间，三个差不多
 * 1024*5~1024*10以后，第一种要好很多
 *
 * 所有简单排序：
 * 1024之内,哨兵bubble，select略优，insert稍慢
 * 1024*5~1024*10以后，插入最优，其次选择，再其次冒泡1交换（bubble1)
 * 
 * 总体上说选择略优于冒泡，直接插入最优
 *
 * (关于内存，当分配不超过1m时直接使用栈内存，不在堆上分配，jvm逃逸分析)
 * @author xmtsui
 * @version v1.0
 */
import java.util.Arrays;
class SimpleSort {	
	/**
	 * 伪冒泡排序（交换排序）
	 * 最好o(n^2),平均o(n^2),最坏o(n^2),空间o(1)
	 * 
	 * 比较次数: 固定，都是n^2 > n(n-1)/2
	 * 交换次数: 不固定，最好0， 最差n-1
	 * @param l [description]
	 */
	static void BubbleSort0(SeqList l)
	{
		int len = l.len;
		for(int i=1; i<len; ++i)
		{
			for(int j=i+1; j<=len; ++j)
			{
				if(l.r[i]>l.r[j])
					Swap(l, i, j);
			}
		}
	}
	
	/**
	 * 一般冒泡排序
	 * 最好o(n^2),平均o(n^2),最坏o(n^2),空间o(1)
	 * 
	 * 比较次数: 固定，都是n(n-1)/2
	 * 交换次数: 固定，都是n(n-1)/2
	 * @param l [description]
	 */
	static void BubbleSort1(SeqList l)
	{
		int len = l.len;
		for(int i=1; i<len; ++i)//从1开始，所以循环到len-1（如果下标从0开始，就是len-2）
		{
			for(int j=len-1; j>=i; --j)//从len-1开始，循环到1（如果下标从0开始的，就到0）
			{
				if(l.r[j]>l.r[j+1])//如果找到前面比后面大的，交换
					Swap(l,j,j+1);
			}
		}	
	}
	
	/**
	 * 改进冒泡排序
	 * 最好o(n^2),平均o(n^2),最坏o(n^2),空间o(1)
	 * 
	 * 比较次数: 固定，都是n(n-1)/2
	 * 交换次数: 固定，都是n(n-1)/2
	 * 但有序之后，就停止无意义的交换
	 * @param l [description]
	 */
	static void BubbleSort2(SeqList l)
	{
		int len = l.len;
		boolean flag = true;
		for(int i=1; i<len&&flag; ++i)
		{
			flag=false;
			for(int j=len-1; j>=i; --j)
			{
				if(l.r[j]>l.r[j+1])
				{
					Swap(l,j,j+1);
					flag=true;
				}
			}
		}
	}

	/**
	 * 选择排序（选择最小，然后交换排序）
	 * 最好o(n^2),平均o(n^2),最坏o(n^2),空间o(1)
	 * 
	 * 比较次数: 固定，都是n(n-1)/2
	 * 交换次数: 好的时候0，最坏n-1
	 * @param l [description]
	 */
	static void SelectSort(SeqList l)
	{
		int len = l.len;
		for(int i=0; i<len; ++i)
		{
			int min = i;
			for(int j=i+1; j<=len; ++j)
			{
				if(l.r[min] > l.r[j])//如果找到比当前最小值下标还小的，更新min
				min = j;
			}
			if(min != i)//如果没找到还小的，则不交换。所以正序的时候，一次交换都没有
			Swap(l,i,min);
		}
	}

	/**
	 * 插入排序（插入已有序排序）
	 * 最好o(n),平均o(n^2),最坏o(n^2),空间o(1)
	 * 
	 * 比较次数：最好，比较n-1次（无移动）；最差，逆序，2+3+4+...+n（移动次数3+4+...+n)
	 * 移动次数：最好0，最差，逆序，3+4+...+(n+2)（存临时变量和最终插入，算两次）
	 * @param l [description]
	 */
	static void InsertSort(SeqList l)
	{
		int len = l.len;
		for(int i=2; i<=len; ++i)//从第二个开始
		{
			if(l.r[i-1]>l.r[i])//若前一个数比当前i数大，则将当前数插入有序子表,若正序则无插入操作
			{
				int j=i-1;
				l.r[0]=l.r[i];//既用于临时变量存储，又用于while时候的临界监测（可不判断j是否<=0)
				while(l.r[j]>l.r[0])//循环后移有序表中比i数大的，结束的时候，j下标在合适位置的前一个
				{
					l.r[j+1] = l.r[j];
					j--;
				}
				l.r[j+1]=l.r[0];//j+1定位到合适的位置，插入
			}
		}
	}

	/**
	 * 希尔排序
	 * @param l [description]
	 */
	static void ShellSort(SeqList l)
	{
		int len = l.len;

		//关于increment取值，有很多办法，需要查资料学习对比
		//第一种 
		//int increment = len/2;
		//第二种 Knuth序列
		int increment=1;
		while(increment <= len/3)
			increment = increment*3+1;

		//插入排序
		do{
			for(int i=increment+1; i<=len; ++i)
			{
				//有序增量子表中，如果前一个>后一个，则交换
				if(l.r[i-increment] > l.r[i])
				{
					l.r[0] = l.r[i];
					int j = i-increment;//从前一个开始,插入排序是j=i-1
					while(j>=0 && l.r[j] > l.r[0])//相比插入排序，增加了j>=0的判断
					{
						l.r[j+increment] = l.r[j];
						j-=increment;
					}
					l.r[j+increment] = l.r[0];
				}
			}
			increment = (increment-1)/3;
		} while(increment>=1);
	}

	/**
	 * 堆排序
	 * @param l [description]
	 */
	static void HeapSort(SeqList l)
	{
		int len = l.len;
		for(int i=len/2; i>0; --i)
			AdjustHeap(l, i, len);

		for(int i=len; i>1; --i)
		{
			Swap(l, 1, i);
			AdjustHeap(l, 1, i-1);
		}
	}

	/**
	 * 调整堆
	 * @param  l   [description]
	 * @param  s   [description]
	 * @param  end [description]
	 * @return     [description]
	 */
	private static void AdjustHeap(SeqList l, int s, int end)
	{
		int temp = l.r[s];
		for(int i=s*2; i<=end; i*=2)
		{
			if(i<end && l.r[i]<l.r[i+1])//此处i<end必须有，若没有右儿子的情况，防止l.r[i+1]访问出错
			i++;
			if(temp>=l.r[i])//如果s值大于左右两个儿子，则退出循环
			break;
			l.r[s] = l.r[i];//大值向上移动
			s = i;//记录移动后空出来的位置i
		}
		l.r[s] = temp;//把空出来的位置赋予之前保存的临时值（开始的s值），如果没有交换，l.r[s]=temp;
	}

	/**
	 * 迭代归并 
	 * @param l [description]
	 */
	static void MergeSort1(SeqList sl, SeqList tl, int start, int end)
	{
		SeqList tmp = new SeqList();
		if(start == end)
		{
			tl.r[start] = sl.r[start];
		}
		else
		{
			int medium = (start+end)/2;
			MergeSort1(sl, tmp, start, medium);
			MergeSort1(sl, tmp, medium+1, end);
			Merge(tmp, tl, start, medium, end);
		}
	}

	private static void Merge(SeqList sl, SeqList tl, int start, int medium, int end)
	{
		int i=0,j=0,k=0;
		for(i=start, j=medium+1; start<=medium&&j<=end; i++)
		{
			if(sl.r[start] < sl.r[j])
				tl.r[i] = sl.r[start++];
			else
				tl.r[i] = sl.r[j++];
		}

		if(start<=medium)
		{
			for(int z=start; z<=medium; ++z)
				tl.r[i++] = sl.r[z];
		}

		if(j<=end)
		{
			for(int z=j; z<=end; ++z)
				tl.r[i++] = sl.r[z];
		}
	}

	/**
	 * 非迭代归并
	 * @param l [description]
	 */
	static void MergeSort2(SeqList l)
	{
		SeqList tmp = new SeqList();
		int len = l.len;
		int k=1;
		while(k<len)
		{
			MergePass(l, tmp, k, len);
			k=2*k;
			MergePass(tmp, l, k, len);
			k=2*k;
		}
	}

	private static void MergePass(SeqList l, SeqList tmp, int start, int end)
	{
		int k=1;
		while(k<=end-2*start+1)
		{
			Merge(l, tmp, k, k+start-1, k+2*start-1);
			k=k+2*start;
		}
		if(k<end-start+1)
			Merge(l,tmp,k,k+start-1,end);
		else
			for(int i=k; i<=end; i++)
				tmp.r[i] = l.r[i];
		}

	/**
	 * 快速排序
	 * @param l [description]
	 */
	static void QuickSort1(SeqList l, int start, int end)
	{
		int pivot;
		if(start<end)
		{
			pivot = Partition1(l, start, end);
			QuickSort1(l, start, pivot-1);
			QuickSort1(l, pivot+1, end);
		}
	}

	private static int Partition1(SeqList l, int start, int end)
	{
		int pivotkey=l.r[start];
		while(start<end)
		{
			while(start<end && l.r[end]>=pivotkey)
				end--;
			Swap(l, start, end);
			while(start<end && l.r[start]<=pivotkey)
				start++;
			Swap(l, start, end);
		}
		return start;
	}

	/* 用于快速排序时判断是否选用插入排序阙值 */
	private final static int MAX_LENGTH_INSERT_SORT = 7;
	static void QuickSort2(SeqList l, int start, int end)
	{ 
		int pivot;
		// if((end-start)>MAX_LENGTH_INSERT_SORT)
		if(true)
		{
			while(start<end)
			{
				pivot=Partition2(l,start,end); /*  将l->r[start..end]一分为二，算出枢轴值pivot */
				QuickSort2(l,start,pivot-1);		/*  对低子表递归排序 */
				/* QuickSort2(l,pivot+1,end);		/*  对高子表递归排序 */
				start=pivot+1;	/* 尾递归 */
			}
		}
		else
			InsertSort(l);
	}

	/* 快速排序优化算法 */
	private static int Partition2(SeqList l,int start,int end)
	{ 
		int pivotkey;

		int m = start + (end - start) / 2; /* 计算数组中间的元素的下标 */  
		if (l.r[start]>l.r[end])			
			Swap(l,start,end);	/* 交换左端与右端数据，保证左端较小 */
		if (l.r[m]>l.r[end])
			Swap(l,end,m);		/* 交换中间与右端数据，保证中间较小 */
		if (l.r[m]>l.r[start])
			Swap(l,m,start);		/* 交换中间与左端数据，保证左端较小 */

		pivotkey=l.r[start]; /* 用子表的第一个记录作枢轴记录 */
		l.r[0]=pivotkey;  /* 将枢轴关键字备份到l.r[0] */
		while(start<end) /*  从表的两端交替地向中间扫描 */
		{ 
			while(start<end && l.r[end]>=pivotkey)
				end--;
			l.r[start]=l.r[end];
			while(start<end && l.r[start]<=pivotkey)
				start++;
			l.r[end]=l.r[start];
		}
		l.r[start]=l.r[0];
		return start; /* 返回枢轴所在位置 */
	}

	/*自定义部分*/
	private static final int KB = 1024;
	// private static final int KB = 1024*1024;//测试内存用
	private static final int MB = 1024*1024;

	/**
	 * 交换函数
	 * @param l [description]
	 * @param a [description]
	 * @param b [description]
	 */
	private static void Swap(SeqList l, int a, int b)
	{
		int tmp=l.r[a];
		l.r[a]=l.r[b];
		l.r[b]=tmp;
	}

	/**
	 * 内部封装类
	 */
	private static class SeqList implements Cloneable{
		private final static int MAXSIZE=KB;
		int[] r = new int[MAXSIZE+1];//r[0]为哨兵，或者临时存储，真正的存储在1~len之间
		int len;

		public SeqList(){}
		
		public SeqList(int[] r, int len){
			if(len>MAXSIZE)
			{
				System.out.println("element number exceeds the limits: "+MAXSIZE);
				System.exit(0);
			}
			else
			{
				for(int i=0; i<len; ++i)
					this.r[i+1] = r[i];
				this.len = len;
			}
		}

		public Object clone(){
			SeqList o = null; 
			try{ 
				o = (SeqList)super.clone(); 
			}catch(CloneNotSupportedException e){ 
				e.printStackTrace(); 
			} 
			//r == o.r 此函数没有新生成数组
			// System.arraycopy(r, 0, o.r, 0, len);
			// System.out.println("\n==============="+(r==o.r));
			// 生成新的数组
			o.r = Arrays.copyOf(r, len+1);
			// System.out.println(o.r.length+".....");//测试clone复制了多长的数组
			// System.out.println("\n==============="+(r==o.r));
			return o;
		}

		/**
		 * 遍历
		 * @param flag 排序前输入false,后输入true
		 */
		public void toString(String method, boolean flag){
			if(!flag)
				System.out.print(method + "前：[");
			else
				System.out.print(method + "后：[");
			for(int i=1; i<len-1; ++i)
			{
				System.out.print(r[i] + ", ");
			}
			System.out.print(r[len]+"]\n");
		}
	}

	public static void main(String[] args)
	{
		//检测内存消耗
		Runtime rt_jmm = Runtime.getRuntime();
		long total_jmm = rt_jmm.totalMemory();
		long max_jmm = rt_jmm.maxMemory();
		int MB_JMM = 1024*1024;
		System.out.println("total : " + total_jmm/MB_JMM + " mb");
		System.out.println("max : " + max_jmm/MB_JMM + " mb");

		//初始空闲内存记录
		long free1_jmm = rt_jmm.freeMemory();
		
		/*小数据*/
		int[] d={50,10,90,30,70,40,80,60,20,33};
		int N=d.length;

		SeqList q0,q1,q2,q3,q4,q5,q6,q7,q8,q9,q10;
		q0 = new SeqList(d,N);
		q1 = (SeqList)q0.clone();//clone的时候，只clone了len长度的数组
		q2 = (SeqList)q0.clone();
		q3 = (SeqList)q0.clone();
		q4 = (SeqList)q0.clone();
		q5 = (SeqList)q0.clone();
		q6 = (SeqList)q0.clone();
		q7 = (SeqList)q0.clone();
		q8 = (SeqList)q0.clone();
		q9 = (SeqList)q0.clone();
		q10 = (SeqList)q0.clone();
		

		q0.toString("排序之前",false);
		BubbleSort0(q0);
		q0.toString("交换排序",true);
		BubbleSort1(q1);
		q1.toString("一般冒泡",true);
		BubbleSort2(q2);
		q2.toString("改进冒泡",true);
		SelectSort(q3);
		q3.toString("选择排序",true);
		InsertSort(q4);
		q4.toString("插入排序",true);
		ShellSort(q5);
		q5.toString("希尔排序",true);
		HeapSort(q6);
		q6.toString("哦堆排序",true);
		MergeSort1(q7,q7,1,N); System.gc();
		q7.toString("迭代归并",true);
		MergeSort2(q8);
		q8.toString("非迭归并",true);
		QuickSort1(q9,1,N);
		q9.toString("快速排序",true);
		QuickSort2(q10,1,N);
		q10.toString("改进快速",true);
		
		/*大数据*/
		int[] d1= new int[KB];//4KB
		for(int i=0; i<KB; ++i)
		{
			d1[i] = (int)(100*Math.random());
		}
		SeqList l1 = new SeqList(d1, KB);
		SeqList l2 = (SeqList)l1.clone();
		long free2_jmm = rt_jmm.freeMemory();
		// 在不用MergeSort的情况下，此处如果没有用q0，则内存被回收，输出7，加上下面的语句，则输出11
		// System.out.println(q0.r.length+",,,,");
		SeqList l3 = (SeqList)l1.clone();
		SeqList l4 = (SeqList)l1.clone();
		SeqList l5 = (SeqList)l1.clone();
		SeqList l6 = (SeqList)l1.clone();
		SeqList l7 = (SeqList)l1.clone();
		SeqList l8 = (SeqList)l1.clone();
		SeqList l9 = (SeqList)l1.clone();
		SeqList l10 = (SeqList)l1.clone();
		SeqList l11 = (SeqList)l1.clone();

		System.out.println("used : " + (free1_jmm-free2_jmm)/MB_JMM + " mb");
		
		long start1 = System.currentTimeMillis();
		BubbleSort0(l1);
		long end1 = System.currentTimeMillis();
		long time1 = end1 - start1;

		long start2 = System.currentTimeMillis();
		BubbleSort1(l2);
		long end2 = System.currentTimeMillis();
		long time2 = end2 - start2;

		long start3 = System.currentTimeMillis();
		BubbleSort2(l3);
		long end3 = System.currentTimeMillis();
		long time3 = end3 - start3;

		long start4 = System.currentTimeMillis();
		SelectSort(l4);
		long end4 = System.currentTimeMillis();
		long time4 = end4 - start4;

		long start5 = System.currentTimeMillis();
		InsertSort(l5);
		long end5 = System.currentTimeMillis();
		long time5 = end5 - start5;

		long start6 = System.currentTimeMillis();
		ShellSort(l6);
		long end6 = System.currentTimeMillis();
		long time6 = end6 - start6;

		long start7 = System.currentTimeMillis();
		HeapSort(l7);
		long end7 = System.currentTimeMillis();
		long time7 = end7 - start7;

		free1_jmm = rt_jmm.freeMemory();
		long start8 = System.currentTimeMillis();
		MergeSort1(l8, l8, 1, KB);
		long end8 = System.currentTimeMillis();
		long time8 = end8 - start8;
		free2_jmm = rt_jmm.freeMemory();
		System.out.println("迭代归并used : " + (free1_jmm-free2_jmm)/MB_JMM + " mb");

		free1_jmm = rt_jmm.freeMemory();
		long start9 = System.currentTimeMillis();
		MergeSort2(l9);
		long end9 = System.currentTimeMillis();
		long time9 = end9 - start9;
		free2_jmm = rt_jmm.freeMemory();
		System.out.println("非迭代归并used : " + (free1_jmm-free2_jmm)/MB_JMM + " mb");

		long start10 = System.currentTimeMillis();
		QuickSort1(l10,1,KB);
		long end10 = System.currentTimeMillis();
		long time10 = end10 - start10;

		long start11 = System.currentTimeMillis();
		QuickSort2(l11,1,KB);
		long end11 = System.currentTimeMillis();
		long time11 = end11 - start11;


		System.out.println(
			"冒泡1\t|冒泡2\t|冒泡3\t|选择\t|插入\t"+
			"|希尔\t|堆\t|迭归\t|非归\t|快排\t|改快");

		System.out.println(time1 + 
			"\t|"+ time2 + "\t|"+ time3 +
			"\t|"+ time4 + "\t|"+ time5 +
			"\t|"+ time6 + "\t|"+ time7 +
			"\t|"+ time8 + "\t|"+ time9 +
			"\t|"+ time10 + "\t|"+time11
			);
	}
}