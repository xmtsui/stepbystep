/**
 * 问题描述：
 *      从十万个数中找出前一百个较大的数，让你来实现你会如何实现？
 *算法：
 *      一般人们很容易想到的算法是，在程序中定义一个大数组，将这十万个数进行排序，
 *      再定义一个元素为一百的数组将排在前一百是数选出放在这个数组中。
 *改进算法：
 *       只要停下来想想很容易发现，在上面的算法中存在一个很大的问题，做了很多的无用功，
 *       我们要的是前一百一个数，后面的九万九千九百个数的排序都的多余的，换句话说那是在浪费时间，
 *       是算法低效的原因所在。
 *       现在我们可以换一中思维，假设在这十万个数中前一百个就是其中最大的，将其取出。
 *       再对其排序。用一个指针指向这一百个元素中最小的那个。
 *       将这个最小的元素和后面的九万九千九百个数比较，
 *       如果后面的数比这个小数大，那就交换。并将前一百个数重新排序，
 *       再将里面最小的和后面的比较。一直这样循环，直到和后面的数比较完。
 *       这样只要都大数组扫描一边就可以完成，可以很大的改善程序的效率。
 */
class _7TopKBig{
	public static void main(String[] args)
	{
		int kb = 1024;
		int mb = 1024*1024;
		int K = 100;
		int[] a = new int[mb];//4mb
		for(int i=0; i<mb; ++i)
		{
			//int seed = (int); 
			int r = (int) (1000000 * Math.random());
			a[i] = r;
		}
		int[] heap = new int[K];
		System.arraycopy(a, 0, heap, 0, K);
		
		/*构建初始堆*/
		for(int i=(K>>1); i>=0; --i)
			adjustHeap(heap, i, K-1);


		/*查找topK*/
		for(int i=K; i<mb; ++i)
		{
			if(a[i]>heap[0])
			{
				heap[0] = a[i];
				adjustHeap(heap, 0, K-1);
			}
		}

		/*堆排序*/
		for(int i=K-1; i>=0; --i)
		{
			int tmp = heap[0];
			heap[0] = heap[i];
			heap[i] = tmp;
			adjustHeap(heap, 0, i-1);
		}

		/*打印*/
		for(int i=0; i<K; ++i)
		{
			System.out.print(heap[i] + "\t");
		}
		System.out.print("\n");
	}

	private static void adjustHeap(int[] a, int s, int e)
	{
		if(a==null)
			return;
		int len = a.length;
		if(s<0||e>=len||s>e)
			return;
		int tmp = a[s];
		for(int i=(2*s+1); i<=e; i=(2*i+1))
		{
			if(i<=e-1 && a[i]>=a[i+1])
				i++;
			if(tmp < a[i])
				break;
			a[s] = a[i];
			s=i;
		}
		a[s] = tmp;
	}
}