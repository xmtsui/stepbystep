import com.tsui.util.Vector;
/**
 * 测试Vector
 * @author xmtsui
 * @version v1.0
 */
public class testVector{
	public static void main(String[] args)
	{
		Vector<String> al = new Vector<String>();
		al.add("test");
		al.add("123");
		al.doTraverse();
		System.out.println(al.get(0));
		System.out.println(al.size());
		System.out.println(al.isEmpty());
		System.out.println(al.indexOf("test"));
		System.out.println(al.set(1,"234"));
		System.out.println(al.getClass());
		System.out.println(al.remove("test"));
		System.out.println(al.size()==1);
		al.clear();
		System.out.println(al.size()==0);

	}
}
