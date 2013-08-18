/**
 * 测试重写函数的时候返回值为子类
 * 重写时参数不能为子类，如果这样做了，就是新定义一个方法
 *
 * @author xmtsui
 * @version v1.0
 */
class TestOverride{
	public static void main(String[] args)
	{
		B b = new B();
		b.test(b);
		b.test("b");
	}
}

class A{
	Object test(Object o){
		System.out.println("A");
		return o;
	}
}

class B extends A{
	@Override
	String test(Object o)
	{
		System.out.println("test return type is sub");
		return "B";
	}

	//overload
	String test(String o){
		System.out.println("test return type and parm all sub");
		return "B";
	}
}