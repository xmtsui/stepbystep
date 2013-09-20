class MyHanoi{
	public static void hanoi(int num, String a, String b, String c)
	{
		if(num==0)
			return;
		else
		{
			hanoi(num-1, a, c, b);
			System.out.println("from " +a+" to " +c);
			hanoi(num-1, b, a, c);
		}
	}

	public static void main(String[] args)
	{
		hanoi(3,"A","B","C");
	}
}