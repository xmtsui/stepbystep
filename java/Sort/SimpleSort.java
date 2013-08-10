/**
 * 简单排序算法：冒泡，选择(选择最小交换)，插入(直接插入有序)
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
				if(l.r[j-1]>l.r[j])
				{
					Swap(l,j-1,j);
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

		SeqList q0,q1,q2,q3,q4;
		q0 = new SeqList(d,N);
		q1 = (SeqList)q0.clone();//clone的时候，只clone了len长度的数组
		q2 = (SeqList)q0.clone();
		q3 = (SeqList)q0.clone();
		q4 = (SeqList)q0.clone();
		
		q0.toString("交换排序",false);
		BubbleSort0(q0);
		q0.toString("交换排序",true);
		q1.toString("一般冒泡",false);
		BubbleSort1(q1);
		q1.toString("一般冒泡",true);
		q2.toString("改进冒泡",false);
		BubbleSort2(q2);
		q2.toString("改进冒泡",true);
		q3.toString("选择排序",false);
		SelectSort(q3);
		q3.toString("选择排序",true);
		q4.toString("插入排序",false);
		InsertSort(q4);
		q4.toString("插入排序",true);
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
		SeqList l5 = (SeqList)l1.clone();
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

		System.out.println("For big data, time1: " + time1 + 
			"| time2: "+ time2 + "| time3: "+ time3 +
			"| time4: "+ time4 + "| time5: "+ time5
			);
	}
}