/**
 * Write a program to swap odd and even bits in an integer with
 * as few instructions as possible (e.g., bit 0 and bit 1 are 
 * swapped, bit 2 and bit 3 are swapped, etc).
 * 
 * solution:
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
		//0x1010 1010 1010 1010 1010 1010 1010 1010保存偶数位,右移一位放在奇数位置
		//0x0101 0101 0101 0101 0101 0101 0101 0101保存奇数位,左移一位放在偶数位置
		//最后逻辑或，合并返回
		return ( ((x & 0xaaaaaaaa) >> 1) | ((x & 0x55555555) << 1) );
	}
	public static void main(String[] args)
	{
		System.out.println(doSwap(1));
		System.out.println(doSwap(2));
		System.out.println(doSwap(4));
	}
}