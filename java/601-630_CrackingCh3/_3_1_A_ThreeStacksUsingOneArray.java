/**
 * Implement three stacks using one and only one array
 * 在array一定的情况下，每个栈指定了有限空间，不能超过该限制
 * 为了方便测试，只支持int
 * @author xmtsui
 * @version v1.0
 */
class _3_1_A_ThreeStacksUsingOneArray{

	private final static int MAXSIZE = 30;
	private int[] stack = new int[MAXSIZE * 3];
	private int[] stack_pointer = {0, 0, 0};

	private void rangeCheck(int stack_num)
	{
		if(stack_num > 2 || stack_num < 0)
			throw new StackNumInvalidException("Stack number can only be 0, 1, 2");
	}
	/**
	 * 压入指定的栈指定的值
	 * @param  stack_num 栈编号，0，1，2
	 * @param  item		 内容 
	 * @return           压栈成功返回true
	 */
	public boolean push(int stack_num, int item)
	{
		rangeCheck(stack_num);
		stack[MAXSIZE*stack_num + stack_pointer[stack_num]] = item;
		stack_pointer[stack_num]++;
		return true;
	}

	/**
	 * 弹出指定栈的栈定元素
	 * @param  stack_num 栈编号
	 * @return           弹出成功返回栈定值，失败返回0
	 *                   失败情况包括：栈标号错误／栈空
	 */
	public int pop(int stack_num)
	{
		rangeCheck(stack_num);
		if(stack_pointer[stack_num] == 0)
			return 0;
		int oldValue = stack[MAXSIZE*stack_num + stack_pointer[stack_num] - 1];
		stack[MAXSIZE*stack_num + stack_pointer[stack_num] - 1] = 0;
		stack_pointer[stack_num]--;
		return oldValue;
	}

	/**
	 * 返回指定栈的栈顶元素
	 * @param  stack_num [description]
	 * @return           [description]
	 */
	public int peek(int stack_num)
	{
		rangeCheck(stack_num);
		if(stack_pointer[stack_num] == 0)
			return 0;
		return stack[MAXSIZE*stack_num + stack_pointer[stack_num] - 1];
	}

	/**
	 * [size description]
	 * @param  stack_num [description]
	 * @return           [description]
	 */
	public int size(int stack_num)
	{
		return stack_pointer[stack_num];
	}

	/**
	 * [isEmpty description]
	 * @param  stack_num [description]
	 * @return           [description]
	 */
	public boolean isEmpty(int stack_num)
	{
		return stack_pointer[stack_num] == 0;
	}

	/**
	 * 测试用遍历函数,全部
	 * @param  stack_num [description]
	 * @return           [description]
	 */
	public void toString1(int stack_num)
	{
		for(int i=0; i<MAXSIZE; ++i)
		{
			System.out.print(stack[MAXSIZE*stack_num + i]);
		}
		System.out.println();
	}
	/**
	 * 测试用遍历函数，根据长度打印
	 * @param  stack_num [description]
	 * @return           [description]
	 */
	public void toString2(int stack_num)
	{
		for(int i=0; i<stack_pointer[stack_num]; ++i)
		{
			System.out.print(stack[MAXSIZE*stack_num + i]);
		}
		System.out.println();
	}

	/**
	 * 自定义RuntimeException
	 */
	private class StackNumInvalidException extends IllegalArgumentException{
		StackNumInvalidException()
		{
			super();
		}

		StackNumInvalidException(String s)
		{
			super(s);
		}
	}

	public static void main(String[] args)
	{
		_3_1_A_ThreeStacksUsingOneArray s = new _3_1_A_ThreeStacksUsingOneArray();
		s.push(0,1);
		s.push(0,2);
		s.push(0,3);
		s.push(1,4);
		s.push(1,5);
		s.push(1,6);
		s.push(2,7);
		s.push(2,8);
		s.push(2,9);
		s.toString1(0);
		s.toString1(1);
		s.toString1(2);
		s.toString2(0);
		s.toString2(1);
		s.toString2(2);
		System.out.println(s.peek(0));
		System.out.println(s.peek(1));
		System.out.println(s.peek(2));
		s.toString1(0);
		s.toString1(1);
		s.toString1(2);
		System.out.println(s.pop(0));
		System.out.println(s.pop(1));
		System.out.println(s.pop(2));
		s.toString1(0);
		s.toString1(1);
		s.toString1(2);
		s.toString2(0);
		s.toString2(1);
		s.toString2(2);
		System.out.println(s.size(0));
		System.out.println(s.size(1));
		System.out.println(s.size(2));
		System.out.println(s.isEmpty(0));
		System.out.println(s.isEmpty(1));
		System.out.println(s.isEmpty(2));
		System.out.println(s.pop(0));
		System.out.println(s.pop(1));
		System.out.println(s.pop(2));
		System.out.println(s.pop(0));
		System.out.println(s.pop(1));
		System.out.println(s.pop(2));
		s.toString1(0);
		s.toString1(1);
		s.toString1(2);
		System.out.println(s.pop(0));
		System.out.println(s.pop(1));
		System.out.println(s.pop(2));
		s.toString1(0);
		s.toString1(1);
		s.toString1(2);
	}
}