class LocalInnerClass{
	static class A{
		void say()
		{
			System.out.println("hello static inner class A");
		}
	}

	class B{
		void say()
		{
			System.out.println("hello inner class B");
		}
	}
	public static void main(String[] args)
	{
		LocalInnerClass local = new LocalInnerClass();
		class A extends LocalInnerClass.A{
			void say()
			{
				super.say();
				System.out.println("hello local inner class A");
			}
		}

		System.out.println("=====1=静态内部类（嵌套类）=====");
		LocalInnerClass.A a = new LocalInnerClass.A();
		a.say();

		System.out.println("=====2=匿名内部类=====");
		a = new LocalInnerClass.A(){
			void say()
			{
				super.say();
				System.out.println("hello anonymous inner class A");
			}
		};
		a.say();

		System.out.println("=====3=局部内部类=====");
		a = new A();
		a.say();

		System.out.println("=====4=一般内部类=====");
		LocalInnerClass.B b = local.new B(){
			void say()
			{
				super.say();
				System.out.println("hello anonymous inner class B");
			}
		};
		b.say();
	}
}