import com.tsui.util.List;
import com.tsui.util.ArrayList;
/**
 * 测试数组List
 * 
 * @author xmtsui
 * @version v1.0
 */
public class testArrayList{
	public static void main(String[] args)
	{
		List<String> al = new ArrayList<String>();
		al.add("test");
		al.add("123");
		al.doTraverse();
		System.out.println(al.get(0));
		System.out.println(al.size());
		System.out.println(al.isEmpty());
		System.out.println(al.indexOf("test"));
		System.out.println(al.set(1,"234"));
		System.out.println(al.getClass());
	}
}