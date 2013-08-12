import java.util.Arrays;
class ArrayType_Test{
	public static void main(String args[])
	{
		// int a[] = new int[10];
		int[] a = new int[10];
		Arrays.fill(a,0);
		for(int item: a)
		{
			System.out.print(item);
		}
		System.out.println();

		Object[] o1 = new Object[1];
		Object o2[] = new Object[1];
		Object o = new Object();
		//看看Object[]到底是不是属于Object类型
		//根据输出结果和instanceof的使用条件，我们可以得出，Object[] a 是属于Object b的子类；
		//换句话说，数组比较特殊，它是属于数组原本类型的一个特殊子类
		System.out.println(o1 instanceof Object);
		System.out.println(o1 instanceof Object[]);
		
		System.out.println(o2 instanceof Object);
		System.out.println(o2 instanceof Object[]);
		
		System.out.println(o instanceof Object);
		System.out.println(o instanceof Object[]);
	}
}