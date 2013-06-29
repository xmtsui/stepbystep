import com.tsui.util.ArrayStack;
/**
 * 测试线性结构实现的Stack
 * @author xmtsui
 * @version v1.0
 */
class testArrayStack{
	public static void main(String[] args)
	{
		ArrayStack<String> s = new ArrayStack<String>();
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