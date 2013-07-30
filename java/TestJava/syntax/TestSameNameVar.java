/**
 * 测试父类子类，接口中的同名变量
 * 注意变量不存在覆写
 * @author xmtsui
 * @version v1.0
 */

class A{
	int i=0;
}
interface B{
	int i=0;
}
class C extends A implements B
{
     //int i=0;
}
class TestSameNameVar{
	public static void main(String[] args)
	{
		C c = new C();
		c.i++;//访问失败
	}
}