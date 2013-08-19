/**
 * What does it mean if A and B == 0
 * It means that A and B never have a 1 bit in the same place. 
 * So if n and (n-1)==0,thennand n-1 never share a 1.
 *
 * @author xmtsui
 * @version v1.0
 */
class _5_4_IsPowerof2{
	/**
	 * 判断是2的指数或者0
	 * @param  n
	 * @return
	 */
	static boolean isPowerOf2(int n)
	{
		return ((n & (n-1)) == 0);
	}
	
	public static void main(String[] args)
	{
		System.out.println(isPowerOf2(0));
		System.out.println(isPowerOf2(1));
		System.out.println(isPowerOf2(2));
		System.out.println(isPowerOf2(3));
		System.out.println(isPowerOf2(4));
	}
}