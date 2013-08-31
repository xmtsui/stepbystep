/**
 * 外观模式
 *
 * 举例1: tomcat针对Request,Response类提供外观类
 * Request除了实现了HttpServletRequest，还有一些自身定义的方法
 * Response实现了HttpServletResponse，也有一些自身定义的方法
 * 这些方法不能对外暴露。
 * 所以又实现了RequestFacade, ResponseFacade，也分别实现相应的接口。
 * 只提供接口规定的方法
 * 在service调用的时候，传入Facade类。
 *
 * 自己的理解，在facade中需要有被引用类的引用,并私有化
 * 然后提供自定义方法
 * 
 */
class FacadeTest{
	public static void main(String[] args)
	{
		Facade facade = new Facade();
		facade.MethodA();
		facade.MethodB();
	}

	static class Facade {
		SubSystemOne one = new SubSystemOne();
		SubSystemTwo two = new SubSystemTwo();
		SubSystemThree three = new SubSystemThree();
		SubSystemFour four = new SubSystemFour();

		public void MethodA()
		{
			System.out.println("-----方法组a");
			one.MethodOne();
			two.MethodTwo();
			four.MethodFour();
		}

		public void MethodB()
		{
			System.out.println("-----方法组b");
			two.MethodTwo();
			four.MethodFour();
		}
	}

	static class SubSystemOne{
		public void MethodOne()
		{
			System.out.println("Method in SubSystem one");
		}
	}

	static class SubSystemTwo{
		public void MethodTwo()
		{
			System.out.println("Method in SubSystem two");
		}
	}

	static class SubSystemThree{
		public void MethodThree()
		{
			System.out.println("Method in SubSystem three");
		}
	}

	static class SubSystemFour{
		public void MethodFour()
		{
			System.out.println("Method in SubSystem four");
		}
	}
}