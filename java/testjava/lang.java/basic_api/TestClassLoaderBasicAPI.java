/**
 * 测试ClassLoader的基本API
 *
 * @author xmtsui
 * @version v1.0
 */
class TestClassLoaderBasicAPI{
	public static void main(String[] args)
	{
		ClassLoader system_tmp = ClassLoader.getSystemClassLoader();
		System.out.println(system_tmp.getResource("Sample.class"));
		while(system_tmp!=null)
		{
			System.out.println(system_tmp);
			system_tmp=system_tmp.getParent();
		}
	}
}