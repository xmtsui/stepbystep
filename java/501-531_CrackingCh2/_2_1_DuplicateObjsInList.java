/**
 * Write code to remove duplicates from an unsorted linked list. 
 * 
 * * FOLLOW UP
 * How would you solve this problem if a temporary buffer is not allowed?
 *
 * @author xmtsui
 * @version v1.0
 */
import java.util.HashMap;
class _2_1_DuplicateObjsInList{

	/**
	 * For simplicity, assume char set is ASCII
	 * (if not, we need to increase the storage size. 
	 * The rest of the logic would be the same).
	 * NOTE: This is a great thing to point out to your interviewer!
	 */

	//only for Character, assume all ascii, without null
	static void doRemove(SinglyLinkedList<Character> s)
	{
		boolean[] check = new boolean[256];

		SinglyLinkedList.Node<Character> pre = null;
		SinglyLinkedList.Node<Character> node = s.head;
		while(node!=null)
		{
			if(node.item == null)
			{
				System.out.println("---- Warning: null included! remove fail, use function doRemove1()");
				return;
			}
			if(check[node.item])
			{
				pre.next = node.next;
				s.size--; 
			}
			else//else 可以节省运算次数
			check[node.item] = true;
			pre = node;
			node = node.next;
		}
	}

	//used for Object, null included
	//and here use hashmap rather than hashtable, 
	//for any null object can not be used as a key or as a value in hashtable.
	static void doRemove1(SinglyLinkedList<Character> s)
	{
		HashMap<Character,Boolean> map = new HashMap<Character,Boolean>();
		SinglyLinkedList.Node<Character> pre = null;
		SinglyLinkedList.Node<Character> node = s.head;
		while(node!=null)
		{
			if(map.containsKey(node.item))
			{
				pre.next = node.next;
				s.size--;
			}
			else
			{
				map.put(node.item, true);
				//发现新值后再改变pre，不需要把head单独考虑，因为是从后面开始删除
				pre = node;
			}
			// pre = node;
			node = node.next;
		}
	}

	//use three references, pre, current, runner. No space consume
	static void doRemove2(SinglyLinkedList<Character> s)
	{
		SinglyLinkedList.Node<Character> pre = null;
		SinglyLinkedList.Node<Character> current = null;
		SinglyLinkedList.Node<Character> runner = null;
		pre = s.head;
		current = s.head.next;
		while(current != null)
		{	
			runner = s.head;
			while(runner != current)
			{
				if(runner.item == current.item)
				{
					pre.next = current.next;
					current = current.next;
					s.size--;
					break;
				}
				runner = runner.next;
			}
			if(runner == current)
			{
				pre = current;
				current = current.next;
			}
		}
	}

	static void doTraverse(SinglyLinkedList<Character> s){
		int len = s.size();
		System.out.print("[ ");
		for(int i=0; i<len; ++i)
		{
			System.out.print(s.get(i) + " ");
		}
		System.out.print("]\n");
	}

	public static void main(String[] args)
	{
		// SinglyLinkedList<String> l = new SinglyLinkedList<String>();	
		Character[] ch_obj = {'A','A','A','A','A','B','C','D',null,null,'A','B','A','B','C','D'};
		SinglyLinkedList<Character> l = new SinglyLinkedList<Character>(ch_obj);
		SinglyLinkedList<Character> l1 = new SinglyLinkedList<Character>(ch_obj);
		SinglyLinkedList<Character> l2 = new SinglyLinkedList<Character>(ch_obj);
		System.out.println("\nfunction 1:");
		doTraverse(l);
		doRemove(l);
		doTraverse(l);

		System.out.println("\nfunction 2:");
		doTraverse(l1);
		doRemove1(l1);
		doTraverse(l1);

		System.out.println("\nfunction 3:");
		doTraverse(l2);
		doRemove2(l2);
		doTraverse(l2);


		Character[] ch_obj1 = new Character[10000];
		for(int i=0; i<ch_obj1.length; i=i+2)
		{
			ch_obj1[i] = 'A';
			ch_obj1[i+1] = 'B';
		}

		SinglyLinkedList<Character> ll = new SinglyLinkedList<Character>(ch_obj1);
		SinglyLinkedList<Character> ll1 = new SinglyLinkedList<Character>(ch_obj1);
		SinglyLinkedList<Character> ll2 = new SinglyLinkedList<Character>(ch_obj1);
		long start1=System.currentTimeMillis();
		doRemove(ll);
		long end1=System.currentTimeMillis();
		long time1=end1-start1;

		long start2=System.currentTimeMillis();
		doRemove1(ll1);
		long end2=System.currentTimeMillis();
		long time2=end2-start2;

		long start3=System.currentTimeMillis();
		doRemove2(ll2);
		long end3=System.currentTimeMillis();
		long time3=end3-start3;
		System.out.println("\nFor big data, time1: " + time1 + "| time2: "+ time2 + "| time3: " + time3);
	}
}