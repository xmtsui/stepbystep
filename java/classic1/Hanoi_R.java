/**
 * Hanoi resolved recursively
 *
 * @author xmtsui
 * @version v1.0
 */
class Hanoi_R{
	private static int count = 1;
	static void HanoiOper(int number, char from, char to, char tmp)
	{
		if(number == 1)
		{
			System.out.println("Step: " + count++ + ": From " + from + " to " + to);
		}
		else
		{
			HanoiOper(number-1, from, tmp, to);
			HanoiOper(1, from, to, tmp);
			HanoiOper(number-1, tmp, to, from);
		}
	}

	public static void main(String[] args)
	{
		HanoiOper(3, 'A','C','B');
	}
}