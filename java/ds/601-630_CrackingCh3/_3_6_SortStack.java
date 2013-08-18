/**
 * Write a program to sort a stack in ascending order. 
 * You should not make any assumptions about how the stack is implemented. 
 * The following are the only functions that 
 * should be used to write this program: push | pop | peek | isEmpty.
 */
import com.tsui.util.ArrayStack;
class _3_6_SortStack{
	public static void main(String[] args)
	{
		ArrayStack<Integer> as = new ArrayStack<Integer>();
		as.push(12);
		as.push(1);
		as.push(2);
		as.push(5);
		as = doSort(as);
		as.printString();

	}

	public static ArrayStack<Integer> doSort(ArrayStack<Integer> originStack)
	{	
		ArrayStack<Integer> sortStack = new ArrayStack<Integer>();
		while(!originStack.isEmpty())
		{
			int tmp = originStack.pop();
			while(!sortStack.isEmpty() && sortStack.peek() > tmp)
			{
				originStack.push(sortStack.pop());
			}
			sortStack.push(tmp);
		}
		return sortStack;
	}
}
