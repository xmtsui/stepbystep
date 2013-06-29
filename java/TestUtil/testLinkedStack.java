import com.tsui.util.LinkedStack;
/**
 *
 * @author xmtsui
 * @version v1.0
 */
class testLinkedStack{
	public static void main(String[] args)
	{
		LinkedStack<String> ls = new LinkedStack<String>();
		ls.push("a");
		ls.push("b");
		ls.push("c");
		System.out.println(ls.size()==3);
		System.out.println(ls.toString());
		System.out.println(ls.peek().equals("c"));
		System.out.println(ls.pop().equals("c"));
		System.out.println(ls.size()==2);
		System.out.println(ls.isEmpty()==false);
		System.out.println(ls.toString());
	}
}