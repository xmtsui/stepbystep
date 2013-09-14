/**
 * 测试各种生成随机数的方法
 *
 * @author xmtsui
 * @version v1.0
 */
import java.util.Random;
class TestRandom{
	private static int DELAY = 10;
	public static void main(String[] args) throws InterruptedException
	{
		// Random r1 = new Random();
		/*使用种子数构造，种子数只是随机算法的起源数字，和生成的随机数字的区间无关。*/
		Random r = new Random(100);
		System.out.println(r.nextBoolean());//生成true和false的值几率相等50%
		System.out.println(r.nextInt(3));//生成[0,3);即[0,2]
		System.out.println(Math.abs(r.nextInt() % 3));//[0,3),[0,2]
		System.out.println(r.nextInt(2)+1);//生成[1,3);即[1,2]
  		System.out.println(r.nextDouble());//[0,1.0)
  		System.out.println(r.nextDouble() * 5);//[0,5.0)
  		System.out.println(r.nextDouble() * 1.5 + 1);//[1,2.5)
  		//几率,概率
  		int n = r.nextInt(100);
		int m; //结果数字
		
		//55个数字的区间，55%的几率
		if(n < 55)
		{
			m = 1;
		}
		else if(n < 95)
		{
			m = 2;
		}
		else
		{
			m = 3;
		}

		//强制类型转化
		int toAccount = (int) (10 * Math.random());
		System.out.println(toAccount);
		double amount = 10.0 * Math.random();
		Thread.sleep((int) (DELAY * Math.random()));
	}//end of main
}//end of class