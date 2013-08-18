/**
 * 测试Class类的基本API
 *
 * @author xmtsui
 * @version v1.0
 */
class TestClassBasicAPI{
	public static void main(String[] args)
	{
		try{
			//throws ClassNotFoundException
			Class c = Class.forName("Sample");
			System.out.println(c);
			//throws InstantiationException,IllegalAccessException
			Object o = c.newInstance();
			System.out.println(o);
		}catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}catch (InstantiationException e)
		{
			e.printStackTrace();
		}catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}
}