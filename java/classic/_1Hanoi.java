/**
 * Hanoi resolved recursively
 *
 * @author xmtsui
 * @version v1.0
 */
class _1Hanoi{
	private static int count = 1;
	static void HanoiOper(int number, char from, char tmp, char to)
	{
		if(number == 1)
		{
			System.out.println("Step: " + count++ + ": From " + from + " to " + to);
		}
		else
		{
			HanoiOper(number-1, from, to, tmp);
			HanoiOper(1, from, tmp, to);
			HanoiOper(number-1, tmp, from, to);
		}
	}

	public static void main(String[] args)
	{
		HanoiOper(3, 'A','B','C');
	}
}