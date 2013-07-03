/** 
 *  Implement a data structure SetOfStacks that mimics this. 
 *  SetOfStacks should be composed of several stacks,
 *  and should create a new stack once the previous one exceeds capacity. 
 *  SetOfStacks.push() and SetOfStacks.pop() 
 *  should behave identically to a single stack 
 *  (that is, pop() should return the same values as 
 *  it would if there were just a single stack).
 *  
 *  and more
 *  Implement a function popAt(int index)
 *  which performs a pop operation on a specific sub-stack.
 *
 * @author xmtsui
 * @version v1.0
 */
import com.tsui.util.ArrayList;
import com.tsui.util.ArrayStack;
class _3_3_SetOfStacks{
	
	private static final int MAXCAP = 3;
	private ArrayList<ArrayStack<Integer>> al = new ArrayList<ArrayStack<Integer>>();

	/**
	 * 超过某一个stack最大容量就新建另外一个stack
	 * @return [description]
	 */
	public boolean push(int element)
	{
		if(getLast().size() < MAXCAP)
			getLast().push(element);
		else
		{
			al.add(new ArrayStack<Integer>());
			getLast().push(element);
		}
		return true;
	}

	public int pop()
	{
		int oldValue = getLast().pop();
		int index = al.size();
		if(getLast().size() == 0)
			al.remove(index-1);
		return oldValue;
	}

	/**
	 * 获取最后的stack
	 * @return [description]
	 */
	private ArrayStack<Integer> getLast()
	{
		int index = al.size()-1;
		if(index < 0)
		{
			al.add(new ArrayStack<Integer>());
			return al.get(0);
		}
		else
		{	
			return al.get(index);
		}
	}

	public void printString()
	{
		for(int i=al.size()-1; i>=0; --i)
		{
			al.get(i).printString();
		}
	}

	public static void main(String[] args)
	{
		_3_3_SetOfStacks s = new _3_3_SetOfStacks();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		s.push(6);
		s.push(7);
		s.printString();
		System.out.println();
		s.pop();
		s.pop();
		s.pop();
		s.printString();
	}
}