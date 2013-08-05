/**
 * Fabonacci problem solved recursively
 *
 * @author xmtsui
 * @version v1.0
 */
class Fabonacci_R{
	static int cal(int n)
	{
		if(n == 0)
			return 0;
		else if(n == 1)
			return 1;
		else
		{
			System.out.println((n-1) + " + " + (n-2) + " = " + (n-1+n-2));
			return cal(n-1) + cal(n-2);
		}
	}

	public static void main(String[] args)
	{
		System.out.println("result: " + cal(3));
	}
}