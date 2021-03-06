/**
 * 先看一个题目：给你一堆西安市的电话号码列表，数量大概在千万级，
 *要求从中找出所有重复的电话号码，需要时间复杂度尽可能小。
 *目前西安市的电话号码大概都以8开头，为8位，也就是类似于82678578这样子
 *二重暴力搜索时间复杂度太高，这里我们不予考虑。
 *容易想到的办法就是建立一个标志数组，int boolean都行，用相应的位置值来代替这个号码是否出现
 *根据数组的可直接存取特性，来提高效率。
 *但是你是否想过或测试过
 *int[] a = new int[100000000];
 *boolean[] a = new boolean[100000000];
 *这样类似的语句是否可以通过编译并且执行。
 *
 *再仔细思考下，就会发现，int型的字段太过于占空间，我们只需要知道这个号码存在与否，
 *所以最简单的0和1就够用了，能表示0和1的最小存储单位是什么呢？是内存中的一位。OK，这就是bitmap的思想。
 *将西安市的电话号码去掉开头的8，就可以将其映射到一个1到10000000的数组中。
 *8bit是1byte,1024byte是1kb,1024kb是1mb
 *所以10000000个bit占用的空间为
 *10000000/8/1024/1024mb大概为1mb多些，
 *这对于现在大家动不动几G的内存来说，完全是小菜一碟。
 *同时，java中也有对应的实现，java.util.BitSet,
 *完全是为这个量身定做的java类。
 *这个类从jdk1.0开始就有了，不过其中的某些方法是jdk1.4以后才有的，
 *大家用的时候要当心。
 *另外BitSet是非线程安全的，需要外部同步。
 *下面的简单代码给出了BitSet的例子：
 *
 *另外一个例子http://luozhong915127.iteye.com/blog/1460411
 *bit map查找重复数据
 */
class _4Bitmap{
	public static void main(String[] args)
	{
		//创建一个具有10000000位的bitset 初始所有位的值为false
		java.util.BitSet bitSet = new java.util.BitSet(10000000);
		//将指定位的值设为true
		bitSet.set(9999, true);
		//输出指定位的值
		System.out.println(bitSet.get(9999));
		System.out.println(bitSet.get(9998));

		Runtime rt_jmm = Runtime.getRuntime();
		long total_jmm = rt_jmm.totalMemory();
		long max_jmm = rt_jmm.maxMemory();
		int MB_JMM = 1024*1024;
		int KB_JMM = 1024;
		System.out.println("total : " + total_jmm/MB_JMM + " mb");
		System.out.println("max : " + max_jmm/MB_JMM + " mb");

		long free1_jmm = rt_jmm.freeMemory();

		int[] nums = new int[KB_JMM];
		for(int i=KB_JMM-1; i>=0; --i)
		{
			int rand = (int)(1000*Math.random());
			nums[i] = rand;
			// nums[i] = i;
		}

		java.util.BitSet bits = new java.util.BitSet();
		for(int i=0; i<KB_JMM; ++i)
		{
			if(!bits.get(nums[i]))
				bits.set(nums[i], true);
			else
				System.out.print("the same"+ i);
		}

		int flag=0;
		for(int i=0; i<KB_JMM; ++i)
		{
			if(bits.get(i))
			{
				flag++;
				System.out.print(i + " ");
			}
		}
		System.out.println(flag);

		long free2_jmm = rt_jmm.freeMemory();
		System.out.println("used : " + (free1_jmm-free2_jmm)/MB_JMM + " mb");
	}
}