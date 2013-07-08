/**
 * 用于测试新的for循环功能
 *
 * @author xmtsui
 * @version v1.0
 */
import java.util.ArrayList;
class TestForEach{
	public static void main(String[] args)
	{
		ArrayList<String> al = new ArrayList<String>();
		al.add("test");
		al.add("test");
		al.add("test");
		// al=null;
		// for循环中al不能为null
		for(String item : al)
		{
			System.out.println(item);
		}

		String[] strs = {"test", " test", "  test"};
		for(String item : strs)
		{
			System.out.println(item);
		}
	}
}