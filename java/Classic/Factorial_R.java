/**
 * Factorial problem solved recursively
 * @author xmtsui
 * @version v1.0
 */
class Factorial_R{
	private static long result = 1;
	static void cal(long n)
	{
		if(n>0)
		{	
			result *= n;
			cal(n-1);
		}
	}

	public static void main(String[] args)
	{
		cal(new Long(1));
		System.out.println("Result: " + result);
	}
}