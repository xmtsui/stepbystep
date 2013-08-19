/**
 * Factorial problem solved recursively
 * 
 * 数字>1000后
 * 非递归的实现更高效
 * @author xmtsui
 * @version v1.0
 */
import java.math.BigInteger;
class _3Factorial{
	/**
	 * 小数字
	 * @param  n [description]
	 * @return   [description]
	 */
	static long cal(long n)
	{
		if(n==0)
			return 1;
		else if(n==1)
			return 1;
		else
			return n*cal(n-1);
	}

	/**
	 * 大数递归实现
	 * 数字太大会溢出，如10000
	 * java.lang.StackOverflowError
	 * @param  n [description]
	 * @return   [description]
	 */
	static BigInteger cal(BigInteger n)
	{

		if(n.compareTo(BigInteger.ONE)==0) 
			return BigInteger.ONE; 
		return n.multiply(cal(n.subtract(BigInteger.ONE))); 
	}

	/**
	 * 大数非递归实现
	 * 不会溢出
	 * @param  n [description]
	 * @return   [description]
	 */
	public static BigInteger cal_nr(BigInteger n) 
	{ 
		BigInteger result = BigInteger.ONE; 
		for(BigInteger i = BigInteger.ONE;
			i.compareTo(n)<=0;
			i=i.add(BigInteger.ONE)) 
			result = result.multiply(i); 
		return result; 
	} 

	public static void main(String[] args)
	{
		
		System.out.println("Result small integer: " + cal(new Long(10)));

		long start1 = System.currentTimeMillis();
		System.out.println("Result big integer: " + cal(BigInteger.valueOf(2000)));
		long end1 = System.currentTimeMillis();
		long time1 = end1 - start1;

		long start2 = System.currentTimeMillis();
		System.out.println("Result big integer: " + cal_nr(BigInteger.valueOf(10000)));
		long end2 = System.currentTimeMillis();
		long time2 = end2 - start2;

		System.out.println("For big data, time1: " + time1 + "| time2: "+ time2);
	}
}