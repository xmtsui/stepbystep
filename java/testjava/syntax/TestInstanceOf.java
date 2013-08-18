/**
 * 测试instanceof运算符的功能
 *
 * buglist:
 * 阿里电面问题：不同classloader加载同一个类，
 * newInstance()之后分别调用instanceof的行为
 *
 * @author xmtsui
 * @version v1.0 2013-08-06
 */
class TestInstanceOf{
	public static void main(String[] args)
	{
		A a = new A();
		a.say();//a
		B b = new B();
		b.say();//b

		A a1 = new B();
		//b 根据事例化的对象类型判断调用哪个函数，而不是根据引用类型，
		//即根据运行时指向的对象决定
		//对比c++中的虚函数与普通函数
		//普通函数：根据指针或者引用的类型决定
		//虚函数：跟java一样
		a1.say();


		System.out.println(a instanceof A);
		System.out.println(a instanceof B);//false
		System.out.println(b instanceof A);
		System.out.println(b instanceof B);
		System.out.println(a1 instanceof A);
		System.out.println(a1 instanceof B);

	}
}

class A{
	public void say()
	{
		System.out.println("yes a");
	}
}

class B extends A{
	public void say()
	{
		System.out.println("yes b");
	}
}