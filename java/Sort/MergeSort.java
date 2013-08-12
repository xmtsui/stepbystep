import java.util.Arrays;
class MergeSort{
	/**
	 * 迭代归并 
	 * 按需要建立临时区域
	 * @param l [description]
	 */
	static void MergeSort1(SeqList sl, int start, int end)
	{
		// SeqList tmp = new SeqList();
		if(start == end)
		{
			// tl.r[start] = sl.r[start];
			return;
		}
		else
		{
			int medium = (start+end)/2;
			MergeSort1(sl, start, medium);
			MergeSort1(sl, medium+1, end);
			Merge1(sl, start, medium, end);
		}
	}

	private static void Merge1(SeqList sl, int start, int medium, int end)
	{
		// SeqList tmp = (SeqList)sl.clone();
		int[] tmp = new int[sl.r.length];
		// SeqList tmp = new SeqList();
		int i=0,start1=0,start2=0;
		for(i=start, start1=start, start2=medium+1; start1<=medium&&start2<=end; i++)
		{
			if(sl.r[start1] < sl.r[start2])
				tmp[i] = sl.r[start1++];
			else
				tmp[i] = sl.r[start2++];
		}

		if(start1<=medium)
		{
			for(int z=start1; z<=medium; ++z)
				tmp[i++] = sl.r[z];
		}

		if(start2<=end)
		{
			for(int z=start2; z<=end; ++z)
				tmp[i++] = sl.r[z];
		}

		int z=start;
		while(z<=end)
		{
			sl.r[z] = tmp[z];
			z++;
		}
	}

	/**
	 * 迭代归并 
	 * 建立整个临时区域
	 * @param l [description]
	 */
	static void MergeSort1_1(SeqList sl, int start, int end)
	{
		// SeqList tmp = new SeqList();
		if(start == end)
		{
			// tl.r[start] = sl.r[start];
			return;
		}
		else
		{
			int medium = (start+end)/2;
			MergeSort1_1(sl, start, medium);
			MergeSort1_1(sl, medium+1, end);
			Merge1_1(sl, start, medium, end);
		}
	}

	private static void Merge1_1(SeqList sl, int start, int medium, int end)
	{
		// SeqList tmp = (SeqList)sl.clone();
		SeqList tmp = new SeqList();
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
	 * 迭代归并 
	 * 临时区域在外面
	 * @param l [description]
	 */
	static void MergeSort2(SeqList sl, SeqList tl, int start, int end)
	{
		SeqList tmp = new SeqList();
		if(start == end)
		{
			tl.r[start] = sl.r[start];
			return;
		}
		else
		{
			int medium = (start+end)/2;
			MergeSort2(sl, tmp, start, medium);
			MergeSort2(sl, tmp, medium+1, end);
			Merge2(tmp, tl , start, medium, end);
		}
	}

	private static void Merge2(SeqList sl, SeqList tl, int start, int medium, int end)
	{
		int i=0,start1=0,start2=0;
		for(i=start, start1=start, start2=medium+1; start1<=medium&&start2<=end; i++)
		{
			if(sl.r[start1] < sl.r[start2])
				tl.r[i] = sl.r[start1++];
			else
				tl.r[i] = sl.r[start2++];
		}

		if(start1<=medium)
		{
			for(int z=start1; z<=medium; ++z)
				tl.r[i++] = sl.r[z];
		}

		if(start2<=end)
		{
			for(int z=start2; z<=end; ++z)
				tl.r[i++] = sl.r[z];
		}

		int z=start;
		while(z<=end)
		{
			sl.r[z] = tl.r[z];
			z++;
		}
	}

	/**
	 * 非递归
	 * @param l [description]
	 */
	static void MergeSort3(SeqList l)
	{
		int len = l.len;
		int k=1;
		while(k<len)
		{
			MergePass(l, k, len);
			k=2*k;
			// MergePass(l, k, len);
			// k=2*k;
		}
	}

	private static void MergePass(SeqList l, int interval, int end)
	{
		int k=1;
		while(k<=end-2*interval+1)
		{
			Merge1(l, k, k+interval-1, k+2*interval-1);
			k=k+2*interval;
		}
		if(k<end-interval+1)
			Merge1(l, k, k+interval-1, end);
		// else
			// return;
	}

	private final static int KB=1024*10;

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
			o.r = Arrays.copyOf(r, len+1);
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
		//检测内存消耗
		Runtime rt_jmm = Runtime.getRuntime();
		long total_jmm = rt_jmm.totalMemory();
		long max_jmm = rt_jmm.maxMemory();
		int MB_JMM = 1024*1024;
		System.out.println("total : " + total_jmm/MB_JMM + " mb");
		System.out.println("max : " + max_jmm/MB_JMM + " mb");
		
		/*小数据*/
		int[] d={50,10,90,30,70,40,80,60,20,33};
		int N=d.length;

		SeqList q0 = new SeqList(d,N);
		SeqList q1 = (SeqList)q0.clone();//clone的时候，只clone了len长度的数组
		SeqList q1_1 = (SeqList)q0.clone();//clone的时候，只clone了len长度的数组
		SeqList q2 = (SeqList)q0.clone();//clone的时候，只clone了len长度的数组

		MergeSort1(q0,1,N);
		q0.toString("迭代归并1",true);
		MergeSort1_1(q1_1,1,N);
		q0.toString("迭代归并1_1",true);
		MergeSort2(q1,q1,1,N);
		q1.toString("迭代归并2",true);
		MergeSort3(q2);
		q2.toString("非迭代归并",true);

		/*大数据*/
		int[] d1= new int[KB];//4KB
		for(int i=0; i<KB; ++i)
		{
			d1[i] = (int)(100*Math.random());
		}
		SeqList l1 = new SeqList(d1, KB);
		SeqList l2 = (SeqList)l1.clone();
		SeqList l3 = (SeqList)l1.clone();
		SeqList l1_1 = (SeqList)l1.clone();

		long free1_jmm = rt_jmm.freeMemory();
		MergeSort1(l1,1,KB);
		long free2_jmm = rt_jmm.freeMemory();
		MergeSort1_1(l1_1,1,KB);
		long free3_jmm = rt_jmm.freeMemory();
		MergeSort2(l2,l2,1,KB);
		long free4_jmm = rt_jmm.freeMemory();
		MergeSort3(l3);
		long free5_jmm = rt_jmm.freeMemory();

		System.out.println("迭代归并1used : " + (free1_jmm-free2_jmm)/MB_JMM + " mb");
		System.out.println("迭代归并1_1used : " + (free2_jmm-free3_jmm)/MB_JMM + " mb");
		System.out.println("迭代归并2used : " + (free3_jmm-free4_jmm)/MB_JMM + " mb");
		System.out.println("非迭代used : " + (free4_jmm-free5_jmm)/MB_JMM + " mb");
	}
}