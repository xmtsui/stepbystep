import java.util.Arrays;
class ImprovedSortTest{
	static int[] doTest()
	{
		int[] tmp = {1,2,3};
		return tmp;
	}
	public static void main(String[] args)
	{
		int[] tmp = doTest();
		for(int item : tmp)
			System.out.println(item);

		int[] tmp1 = new int[10];
		Arrays.fill(tmp1, -1);
		for(int item : tmp1)
			System.out.println(item);

		System.out.println("----: " + (2^31));
		System.out.println(foo(2^31-3));
	}

	static int foo(int x) {
		return x&-x;
	}
}