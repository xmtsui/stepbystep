/**
 * You have two numbers represented by a linked list, 
 * where each node contains a single digit. 
 * The digits are stored in reverse order, 
 * such that the 1’s digit is at the head of the list. 
 * Write a function that adds the two numbers and returns the sum as a linked list.
 * EXAMPLE
 * Input: (3 -> 1 -> 5) + (5 -> 9 -> 2)
 * Output: 8 -> 0 -> 8
 * 
 * @author xmtsui
 * @version v1.0
 */
import com.tsui.util.SinglyLinkedList;
class _2_4_IntegerAdd{
	static SinglyLinkedList<Integer> doAdd(SinglyLinkedList<Integer> l1, 
		SinglyLinkedList<Integer> l2)
	{
		int len = l1.size()>l2.size()?l1.size():l2.size();
		SinglyLinkedList<Integer> sum = new SinglyLinkedList<Integer>(new Integer[len]);
		SinglyLinkedList.Node<Integer> runner1 = l1.getHead();
		SinglyLinkedList.Node<Integer> runner2 = l2.getHead();
		SinglyLinkedList.Node<Integer> runner_sum = sum.getHead();
		boolean flag=false;
		int c = 0;
		while(runner1 != null && runner2 != null)
		{
			if(runner1.item + runner2.item + 1>= 10)//注意要加上进位来判断
			{
				if(flag)
					runner_sum.item = runner1.item + runner2.item - 10 + 1;
				else
					runner_sum.item = runner1.item + runner2.item - 10;
				flag = true;
			}
			else
			{
				if(flag)
					runner_sum.item = runner1.item + runner2.item + 1;
				else
					runner_sum.item = runner1.item + runner2.item;
				flag = false;
			}
			runner1 = runner1.next;
			runner2 = runner2.next;
			runner_sum = runner_sum.next;
			c++;
		}

		if(runner1 == null)
		{
			while(runner2 != null)
			{
				if(runner2.item + 1 == 10)
				{
					if(flag)
						runner_sum.item = runner2.item - 10 + 1;
					else
						runner_sum.item = runner2.item;
					flag = true;
				}
				else
				{
					if(flag)
						runner_sum.item = runner2.item + 1;
					else
						runner_sum.item = runner2.item;
					flag = false;
				}
				runner_sum = runner_sum.next;
				runner2 = runner2.next;
			}
		}

		if(runner2 == null)
		{
			while(runner1 != null)
			{
				if(runner1.item  == 9)//只需要判断9
				{
					if(flag)
					{
						runner_sum.item = runner1.item - 10 + 1;
						flag = true;
					}
					else
						runner_sum.item = runner1.item;
				}
				else
				{
					if(flag)
						runner_sum.item = runner1.item + 1;
					else
						runner_sum.item = runner1.item;
					flag = false;
				}
				runner_sum = runner_sum.next;
				runner1 = runner1.next;
			}
		}
		return sum;
	}

	//add by node
	static SinglyLinkedList.Node<Integer> doAdd1(SinglyLinkedList.Node<Integer> n1, 
		SinglyLinkedList.Node<Integer> n2, int carry)
	{
		if(n1 == null && n2 == null)
			return null;
		SinglyLinkedList.Node<Integer> result 
			= new SinglyLinkedList.Node<Integer>(carry,null);
		int tmp_res = carry;
		if(n1 != null)
			tmp_res += n1.item;
		if(n2 != null)
			tmp_res += n2.item;
		result.item = tmp_res % 10;
		SinglyLinkedList.Node<Integer> more = doAdd1(n1==null?null:n1.next
			,n2==null?null:n2.next
			,tmp_res>=10?1:0);//书上答案错误
		result.next = more;
		return result;
	}

	public static void main(String[] args)
	{
		Integer[] it1 = {1,9,5,9,3};
		Integer[] it2 = {2,3,4,5};
		SinglyLinkedList<Integer> l1 = new SinglyLinkedList<Integer>(it1);
		SinglyLinkedList<Integer> l2 = new SinglyLinkedList<Integer>(it2);
		SinglyLinkedList<Integer> sum = doAdd(l1, l2);
		SinglyLinkedList.Node<Integer> sum1 = doAdd1(l1.getHead(), l2.getHead(), 0);
		l1.doTraverse();
		l2.doTraverse();
		sum.doTraverse();
		while(sum1 != null)
		{
			System.out.print(sum1.item);
			sum1 = sum1.next;
		}
		System.out.print("\n");

		//----------------------------------------------
		Integer[] obj1 = new Integer[600];
		for(int i=0; i<obj1.length; i=i+2)
		{
			obj1[i] = 4;
			obj1[i+1] = 5;
		}
		SinglyLinkedList<Integer> l = new SinglyLinkedList<Integer>(obj1);
		long start1=System.currentTimeMillis();
		doAdd(l,l);
		long end1=System.currentTimeMillis();
		long time1=end1-start1;

		long start2=System.currentTimeMillis();
		doAdd1(l.getHead(),l.getHead(),0);
		long end2=System.currentTimeMillis();
		long time2=end2-start2;

		System.out.println("\nFor big data, time1: " + time1 + "| time2: "+ time2);
	}
}