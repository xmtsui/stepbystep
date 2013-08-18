/**
 * Hanoi resolved recursively
 *
 * @author xmtsui
 * @version v1.0
 */
class _3_4_A_HanoiRecursive{
	private static int count = 1;
	static void HanoiOper(int number, String src, String des, String buf)
	{
		if(number == 1)
		{
			System.out.println("Step: " + count++ + ": From " + src + " to " + des);
		}
		else
		{
			HanoiOper(number-1, src, buf, des);
			HanoiOper(1, src, des, buf);
			HanoiOper(number-1, buf, des, src);
		}
	}

	public static void main(String[] args)
	{
		HanoiOper(3, "1","3","2");
	}
}