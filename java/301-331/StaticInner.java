/**
 * Nested Class （一般是C++的说法），Inner Class (一般是JAVA的说法)。
 * 
 */
class StaticInner{
	Inner1 inner;
	int j = 0;//非静态属性不能被静态内部类使用

	StaticInner(){
		inner = new Inner1(1);
		inner.say();
		System.out.println(inner.i);
	}

	static class Inner{//编译后没有外部类的引用 final Outerr this$0;
		void say()
		{
			System.out.println("Out with static inner.");
			// System.out.println(Outer.this.t);//静态内部类实例没有外部类实例的引用
			// Outer ou = new Outer();
			// ou.j=1;//多此一举，不如直接把Inner声明为non static
		}
	}

	//同非静态，
	//编译后会产生Outer$1.class，并有相应的构造函数
	private static class Inner1{
		int i = 0;//可以有非静态变量，实例化后可以调用
		Inner1(int ilsls){

		}
		
		void say()//可以有非静态函数
		{
			System.out.println("Out with private static inner." + i);
		}
	}

	public static void main(String[] args)
	{
		// inner.say();
		StaticInner out = new StaticInner();
	}
}