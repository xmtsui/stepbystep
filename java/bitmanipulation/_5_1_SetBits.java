/**
 * You are given two 32-bit numbers, N and M, and two bit positions,
 * i and j. Write a method to set all bits between i and j in N 
 * equal to M (e.g., M becomes a substring of N located at i and 
 * starting at j).
 * EXAMPLE:
 * Input: N = 10000000000, M = 10101, i = 2, j = 6
 * Output: N = 10001010100
 *
 * @author xmtsui
 * @version v1.0
 */
class _5_1_SetBits{
	static int setBits(int n, int m, int i, int j)
	{
		int max = ~0;/*all 1's*/
		int left1s = max - ((1<<j)-1);
		int right1s = (1<<i) - 1;
		int mask = left1s | right1s;
		return (n & mask) | (m << i);
	}

	public static void main(String[] args)
	{
		System.out.println(setBits(2,5,2,3));
		System.out.println(setBits(5,2,2,3));
	}
}