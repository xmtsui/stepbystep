class FinalStaticTest{
	final int x = 100;//定义的时候必须初始化,因为有final，加了final就是常量
	static int y;//定义的时候可以不初始化,因为static会先自动初始化0值，后面人为没有指定，就是自动初始化的值
	final static int z = 100;//
	int xx = 0;

	//字面常量：如;1,'A',"hello" 等这些
	//有名常量：如:final int a = 5; 
	//final char ch = 'b'; 
	//String s = "hello";
	//像这些a 、 ch、s就是有名常量

	static String s1 = "abc";

	public static void main(String[] args)
	{
		// FinalStaticTest fst = new FinalStaticTest();
		// FinalExtendsTest fet = new FinalExtendsTest();
		// System.out.println(fst.x);
		// System.out.println(fet.x);
		// System.out.println(FinalStaticTest.y);
		// System.out.println(FinalExtendsTest.y);
		// System.out.println(FinalStaticTest.z);
		// System.out.println(FinalExtendsTest.z);
		
		// System.out.println(fst.s1);
		// System.out.println(fet.s1);
		// System.out.println(fst.s1 == fet.s1);//继承的是同一个
	}
}

class FinalExtendsTest extends FinalStaticTest{

}