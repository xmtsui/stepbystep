/**
 * Implement a MyQueue class which implements a queue using two stacks.
 *
 * @author xmtsui
 * @version v1.0
 */
import com.tsui.util.ArrayStack;
class _3_5_QueueUsing2Stacks{
	public static void main(String[] args)
	{
		StackQueue sq = new StackQueue();
		sq.offer(1);
		sq.offer(2);
		sq.offer(3);
		sq.offer(4);
		sq.printString();
		sq.poll();
		sq.printString();
		sq.offer(5);
		sq.printString();
	}
}

class StackQueue{
	private ArrayStack<Integer> s1;
	private ArrayStack<Integer> s2;
	
	/**
	 * [StackQueue description]
	 * @return [description]
	 */
	public StackQueue(){
		s1 = new ArrayStack<Integer>();
		s2 = new ArrayStack<Integer>();
	}

	/**
	 * 获取队列大小
	 * @return [description]
	 */
	public int size()
	{
		return s1.size() + s2.size();
	}

	/**
	 * 入队
	 * @param  e [description]
	 * @return   [description]
	 */
	public boolean offer(int e)
	{
		s1.push(e);
		return true;
	}

	/**
	 * 出队
	 * @return [description]
	 */
	public int poll()
	{
		if(!s2.isEmpty())
			return s2.pop();
		else{
			while(s1.isEmpty())
				s2.push(s1.pop());
			return s2.pop();
		}
	}

	/**
	 * 获取队首
	 * @return [description]
	 */
	public int peekFirst()
	{
		if(!s2.isEmpty())
			return s2.peek();
		else{
			while(s1.isEmpty())
				s2.push(s1.peek());
			return s2.peek();
		}
	}

	/**
	 * 打印
	 */
	public void printString()
	{
		System.out.print("s1:");
		s1.printString();
		if(!s2.isEmpty())
			{
				System.out.print("s2:");
				s2.printString();
			}
			else{
				while(!s1.isEmpty())
					s2.push(s1.pop());
				System.out.print("s2:");
				s2.printString();
			}
		}

	}
