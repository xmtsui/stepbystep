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

	static void MergeSort(SeqList l)
	{

	}

	static void QuickSort(SeqList l)
	{

	}

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
		private final static int MAXSIZE=1024;
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
		/*小数据*/
		int[] d={50,10,90,30,70,40,80,60,20,33};
		int N=d.length;

		SeqList q0,q1,q2,q3;
		q0 = new SeqList(d,N);
		q1 = (SeqList)q0.clone();
		q2 = (SeqList)q0.clone();
		q3 = (SeqList)q0.clone();
		
		q0.toString("排序之前",false);
		ShellSort(q0);
		q0.toString("希尔排序",true);
		HeapSort(q1);
		q1.toString("哦堆排序",true);
		MergeSort(q2);
		q2.toString("归并排序",true);
		QuickSort(q3);
		q3.toString("快速排序",true);
	}
}