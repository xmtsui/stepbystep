class SwapWithoutSpace{
	public static void main(String[] args)
	{
		char a = 'a';
		char b = 'a';
		System.out.println("a: " + a + " | b: " + b);
		//异或方法，不能交换自身
		// a ^= b;
		// b ^= a;
		// a ^= b;
		
		//加减方法
		a += b;
		b = (char) (a - b);
		a = (char) (a - b);
		
		System.out.println("a: " + a + " | b: " + b);
	}
}
