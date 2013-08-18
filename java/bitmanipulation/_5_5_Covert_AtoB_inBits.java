/**
 * Write a function to determine the number of bits required 
 * to convert integer A to integer B.
 * Input: 31, 14 Output: 2
 *
 * @author xmtsui
 * @version v1.0
 */
class _5_5_Covert_AtoB_inBits{
	static int bitSwapRequired(int a, int b)
	{
		int count = 0;
		for(int c=a^b; c!=0; c=c>>1){
			count += c&1;//依次计算
		}
		return count;
	}

	public static void main(String[] args)
	{
		System.out.println(bitSwapRequired(1,2));
		System.out.println(bitSwapRequired(31,14));
	}
}