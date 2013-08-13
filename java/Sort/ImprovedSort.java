/**
 * 改进的算法
 *
 * @author xmtsui
 * @version v1.0
 */
import java.util.Arrays;
class ImprovedSort{
	/**
	 * 希尔排序
	 * @param l [description]
	 */
	public static void ShellSort(SeqList l)
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
	public static void HeapSort(SeqList l)
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
	 * 调整堆（大顶堆）
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
	static void MergeSort1(SeqList sl, int start, int end)
	{
		if(start == end)
			return;
		else
		{
			int medium = (start+end)/2;
			MergeSort1(sl, start, medium);
			MergeSort1(sl, medium+1, end);
			Merge(sl, start, medium, end);
		}
	}

	private static void Merge(SeqList sl, int start, int medium, int end)
	{
		SeqList tmp = (SeqList)sl.clone();
		int i=0,start1=0,start2=0;
		for(i=start, start1=start, start2=medium+1; start1<=medium&&start2<=end; i++)
		{
			if(sl.r[start1] < sl.r[start2])
				tmp.r[i] = sl.r[start1++];
			else
				tmp.r[i] = sl.r[start2++];
		}

		if(start1<=medium)
		{
			for(int z=start1; z<=medium; ++z)
				tmp.r[i++] = sl.r[z];
		}

		if(start2<=end)
		{
			for(int z=start2; z<=end; ++z)
				tmp.r[i++] = sl.r[z];
		}

		int z=start;
		while(z<=end)
		{
			sl.r[z] = tmp.r[z];
			z++;
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
			MergePass(l, k, len);
			k=2*k;
		}
	}

	private static void MergePass(SeqList l, int interval, int end)
	{
		int k=1;
		while(k<=end-2*interval+1)
		{
			Merge(l, k, k+interval-1, k+2*interval-1);
			k=k+2*interval;
		}
		if(k<end-interval+1)
			Merge(l, k, k+interval-1, end);
		else
			Merge(l, end, end, end);
	}
		
	/**
	 * 未优化的快速排序
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
	
	/**
	 * 优化的快速排序
	 * @param l     [description]
	 * @param start [description]
	 * @param end   [description]
	 */
	static void QuickSort2(SeqList l, int start, int end)
	{ 
		int pivot;
		if((end-start)>MAX_LENGTH_INSERT_SORT)
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

	/**
	 * 插入排序（插入已有序排序）
	 * @param l [description]
	 */
	private static void InsertSort(SeqList l)
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

	/*自定义部分*/
	private static final int KB = 1024;

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
			// System.out.println(o.r.length+".....");
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
			for(int i=1; i<=len-1; ++i)
			{
				System.out.print(r[i] + ", ");
			}
			System.out.print(r[len]+"]\n");
		}
	}

	public static void main(String[] args)
	{	
		/*小数据*/
		int[] d={50,10,90,30,70,40,80,60,20,33};
		int N=d.length;

		SeqList q0,q1,q2,q3,q4,q5;
		q0 = new SeqList(d,N);
		q1 = (SeqList)q0.clone();
		q2 = (SeqList)q0.clone();
		q3 = (SeqList)q0.clone();
		q4 = (SeqList)q0.clone();
		q5 = (SeqList)q0.clone();
		
		q0.toString("排序之前",false);
		ShellSort(q0);
		q0.toString("希尔排序",true);
		HeapSort(q1);
		q1.toString("大堆排序",true);
		MergeSort1(q2,1,N);
		q2.toString("迭代归并",true);
		MergeSort2(q3);
		q3.toString("非迭归并",true);
		QuickSort1(q4,1,N);
		q4.toString("快速排序",true);
		QuickSort2(q5,1,N);
		q5.toString("改进快速",true);
	}
}