/**
 * 一是局部变量声明周期的问题
 * 二是避免匿名类修改局部变量值造成数据不统一假象的问题。
 * 
 * @author xmtsui
 * @version v1.0
 */
public class Anonymous{
	private int x = 0;
	public int func(final int x)
	{
		new Anonymous(){
			{
				x=10;//不能改变值
				System.out.println(x);//注意：不访问局不变量的话，如果没加final，也不会提示错误
			}
		};
		return x;
	}
	public static void main(String[] args)
	{
		Anonymous an = new Anonymous();
		an.func(an.x);
		System.out.println(an.x);
	}
}