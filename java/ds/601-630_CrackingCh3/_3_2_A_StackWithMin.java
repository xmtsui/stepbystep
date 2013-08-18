/**
 * How would you design a stack which, 
 * in addition to push and pop, 
 * also has a function min which returns the minimum element? 
 * Push, pop and min should all operate in O(1) time.
 * 
 * implement a stack with min() function
 *
 * @author xmtsui
 * @version v1.0
 */
import com.tsui.util.LinkedStack;
class _3_2_A_StackWithMin extends LinkedStack<NodeWithMin>{

	/**
	 * 重写
	 * @param  element [description]
	 * @return         [description]
	 */
	public boolean push(int element)
	{
		int newMin = Math.min(element, min());
		super.push(new NodeWithMin(element, newMin));
		return true;
	}

	/**
	 * 获取最小值
	 * @return [description]
	 */
	private int min()
	{
		if(this.isEmpty())
			return Integer.MAX_VALUE;
		else
			return this.peek().min;
	}

	/**
	 * 测试主函数
	 * @param  args [description]
	 * @return      [description]
	 */
	public static void main(String[] args)
	{
		_3_2_A_StackWithMin s = new _3_2_A_StackWithMin();
		s.push(30);
		s.push(2);
		s.push(10);
		s.push(20);
		s.push(-1);
		System.out.println(s.min()==-1);
		System.out.println(s.pop().element==-1);
		System.out.println(s.min()==2);
		System.out.println(s.pop().element==20);
		System.out.println(s.min()==2);
		System.out.println(s.pop().element==10);
		System.out.println(s.min()==2);
		System.out.println(s.pop().element==2);
		System.out.println(s.min()==30);
	}
}

/**
 * 带最小值的结点
 */
class NodeWithMin{
	int element;
	int min;
	NodeWithMin(int element, int min)
	{
		this.element = element;
		this.min = min;
	}
}