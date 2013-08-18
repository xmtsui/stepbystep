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
import com.tsui.util.ArrayStack;
class _3_2_B_StackWithMin extends ArrayStack<Integer>{
	private ArrayStack<Integer> s = new ArrayStack<Integer>();
	/**
	 * 重写
	 * @param  element [description]
	 * @return         [description]
	 */
	public boolean push(int element)
	{
		//注意需要为<=，不能是<，否则有多个最小值的时候就出错
		if(element <= min())
			s.push(element);
		super.push(element);
		return true;
	}

	/**
	 * 重写
	 * @return [description]
	 */
	public synchronized Integer pop()
	{
		int tmp = super.pop();
		if(tmp == min())
			s.pop();
		return tmp;
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
			return s.peek();
	}

	/**
	 * 测试主函数
	 * @param  args [description]
	 * @return      [description]
	 */
	public static void main(String[] args)
	{
		_3_2_B_StackWithMin s = new _3_2_B_StackWithMin();
		s.push(30);
		s.push(2);
		s.push(10);
		s.push(20);
		s.push(-1);
		System.out.println(s.min()==-1);
		System.out.println(s.pop()==-1);
		System.out.println(s.min()==2);
		System.out.println(s.pop()==20);
		System.out.println(s.min()==2);
		System.out.println(s.pop()==10);
		System.out.println(s.min()==2);
		System.out.println(s.pop()==2);
		System.out.println(s.min()==30);
	}

}