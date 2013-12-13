/**
 * http://www.importnew.com/6997.html
 * ==在拆箱装箱时的作用
 *
 * @author xmtsui
 * @version v1.0
 */
class TestIntegerCache{
	public static void main(String[] args)
	{
		// Integer i1 = 260;
		// Integer i2 = 260;
		Integer i1 = 100;
		Integer i2 = 100;
		if (i1 == i2)
			System.out.println("i1 and i2 is equal");
		else
			System.out.println("i1 and i2 is not equal ");
	}
}