import com.tsui.util.Stack;
/**
 * 测试线性结构实现的Stack
 * @author xmtsui
 * @version v1.0
 */
class testStack{
	public static void main(String[] args)
	{
		Stack<String> s = new Stack<String>();
		s.push("a");
		s.push("b");
		s.push("c");
		s.printString();
		System.out.println(s.pop());
		s.printString();
		System.out.println(s.isEmpty());
		System.out.println(s.peek());
		System.out.println(s.isEmpty());
		s.clear();
		System.out.println(s.isEmpty());


	}
}