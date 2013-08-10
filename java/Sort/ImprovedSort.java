/**
 * 改进的算法
 *
 * @author xmtsui
 * @version v1.0
 */
class ImprovedSort{
	static void ShellSort(SeqList l)
	{

	}

	static void HeapSort(SeqList l)
	{

	}

	static void MergeSort(SeqList l)
	{

	}

	static void QuickSort(SeqList l)
	{

	}

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
			System.out.println(o.r.length+".....");
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

		SeqList q0,q1,q2,q3;
		q0 = new SeqList(d,N);
		q1 = (SeqList)q0.clone();
		q2 = (SeqList)q0.clone();
		q3 = (SeqList)q0.clone();
		
		q0.toString("希尔排序",false);
		ShellSort(q0);
		q0.toString("希尔排序",true);
		q1.toString("哦堆排序",false);
		HeapSort(q1);
		q1.toString("哦堆排序",true);
		q2.toString("归并排序",false);
		MergeSort(q2);
		q2.toString("归并排序",true);
		q3.toString("快速排序",false);
		QuickSort(q3);
		q3.toString("快速排序",true);

		/*大数据*/
		int[] d1= new int[KB];//4KB
		for(int i=0; i<KB; ++i)
		{
			d1[i] = (int)(100*Math.random());
		}

		SeqList l1 = new SeqList(d1, KB);
		SeqList l2 = (SeqList)l1.clone();
		//此处如果没有用q0，则内存被回收，输出7，加上下面的语句，则输出11
		// System.out.println(q0.r.length+",,,,");
		long free2_jmm = rt_jmm.freeMemory();
		SeqList l3 = (SeqList)l1.clone();
		SeqList l4 = (SeqList)l1.clone();
		System.out.println("used : " + (free1_jmm-free2_jmm)/MB_JMM + " mb");

		long start1 = System.currentTimeMillis();
		ShellSort(l1);
		long end1 = System.currentTimeMillis();
		long time1 = end1 - start1;

		long start2 = System.currentTimeMillis();
		HeapSort(l2);
		long end2 = System.currentTimeMillis();
		long time2 = end2 - start2;

		long start3 = System.currentTimeMillis();
		MergeSort(l3);
		long end3 = System.currentTimeMillis();
		long time3 = end3 - start3;

		long start4 = System.currentTimeMillis();
		QuickSort(l4);
		long end4 = System.currentTimeMillis();
		long time4 = end4 - start4;

		System.out.println("For big data, time1: " + time1 + 
			"| time2: "+ time2 + "| time3: "+ time3 +
			"| time4: "+ time4 );
	}
}