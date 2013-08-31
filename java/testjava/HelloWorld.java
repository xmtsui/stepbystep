class HelloWorld implements HelloInterface{
	public int x = 0;

	@Override
	public void sayHello()
	{
		System.out.println("sayHello(): hello world!");
	}
	public static void main(String[] args)
	{
		System.out.println("hello world!");
	}
}
