/**
 * 有关范型程序设计的测试
 *
 * 范型方法定义和使用
 * @author xmtsui
 * @version v1.0 2013-08-04 
 */
class TestGenericMethod{
	public static void main(String[] args)
	{
		TestGenericMethod test = new TestGenericMethod();
		test.<String,String,String>getName("--a1","++b2","==c2");
		
		Generic<String,String,String> g = new Generic<String,String,String>();
		System.out.println(g.getName("--a","++b","==c"));

		Rule rule = getValue("tsui");
		rule.say();

	}
		
	/*范型方法,可以定义在普通类或者范型类中*/
	public static <A,B,C> A getName(A a, B b, C c)
	{
		System.out.println(a + " " + b + " " + c);
		return a;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getValue(String key)
	{
		return (T) new Rule(key);
	}

	public static class Rule {
		public String s;
		public Rule(String key)
		{
			s = key;
		}
		public void say()
		{
			System.out.println("Hello " + s);
		}
	}

	/*范型类*/
	private static class Generic<A,B,C>{
		private String name="test";
		public A getName(A a, B b, C c)
		{
			System.out.println(name + a + b + c);
			return a;
		}
	}

}