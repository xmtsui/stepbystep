/**
 * 自然数连续子序列和为n
 */
class Test2{
	public static void FindContinuousSequence(int n)
	{
		assert(n >= 3);

		int small = 1; 
		int big = 2;
		int mid = (1+n)/2;
		int sum = small+big;
		while(small < mid)
		{
			if(sum == n)
			{
				System.out.println(small + "-" + big);
				sum -= small;
				small++;
			}
			else if(sum > n)
			{
				sum -= small;
				small++;
			}
			else
			{
				big++;
				sum += big;
			}
		}
	}
	public static void main(String[] args)
	{
		FindContinuousSequence(6);
		System.out.println(1+"test".charAt(0));
	}
}
