/**
 * Hanoi resolved recursively
 *
 * @author xmtsui
 * @version v1.0
 */
class Hanoi_R{
	private static int count = 1;
	static void HanoiOper(int number, char a, char b, char c)
	{
		if(number == 1)
		{
			System.out.println("Step: " + count++ + ": From " + a + " to " + c);
		}
		else
		{
			HanoiOper(number-1, a, c, b);
			HanoiOper(1, a, b, c);
			HanoiOper(number-1, b, a, c);
		}
	}

	public static void main(String[] args)
	{
		HanoiOper(4, 'A','B','C');
	}
}