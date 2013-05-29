class TestInner{
	public static void main(String[] args)
	{
		//非静态内部类
		NonStaticInner.Inner in = new NonStaticInner().new Inner();
		
		//或者
		// NonStaticInner out = new NonStaticInner();
		// NonStaticInner.Inner in = out.new Inner();
		
		in.say();

		//静态内部类
		StaticInner.Inner static_in = new StaticInner.Inner();
		static_in.say();
	}
}