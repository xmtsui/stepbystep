/**
 * Mask all odd bits with 10101010 in binary (which is 0xAA), 
 * then shift them left to put them in the even bits. Then, 
 * perform a similar operation for even bits. 
 * This takes a total 5 instructions.
 *
 * @author xmtsui
 * @version v1.0
 */
class _5_6_Swap_Odd_Even_Bits{

	static int doSwap(int x)
	{
		return ( ((x & 0xaaaaaaaa) >> 1) | ((x & 0x55555555) << 1) );
	}
	public static void main(String[] args)
	{
		System.out.println(doSwap(1));
		System.out.println(doSwap(2));
		System.out.println(doSwap(4));
	}
}