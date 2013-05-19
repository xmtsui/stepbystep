/**
 * Implement an algorithm to find the nth to last element of a singly linked list.
 *
 * @author xmtsui
 * @version v1.0
 */
class _2.2_TheNth2Last{
	static void doFind(SinglyLinkedList s, int theNth)
	{
		if(s == null)
		{
			System.out.println("Error1: List null!");
			return;
		}
		if(theNth >= s.size() || theNth < 0)
		{
			System.out.println("Error2: Index outofbounds!");
			return;
		}
		SinglyLinkedList.Node runner1 = s.head;
		SinglyLinkedList.Node runner2 = s.head;
		for(int i=0; i<theNth; ++i)
		{
			runner2 = runner2.next;
		}
		while(runner2.next != null)
		{
			runner1 = runner1.next;
			runner2 = runner2.next;
		}
		System.out.println("the " + theNth + "th is: " + runner1.item);
	}

	public static void main(String[] args)
	{
		//初始化一个长度为7的单链表
		String[] t = {"A","B","C","D","E","F","G"};
		SinglyLinkedList<String> l = new SinglyLinkedList<String>(t);
		l.doTraverse();
		//legal test
		doFind(l,0);
		doFind(l,1);
		doFind(l,3);
		doFind(l,6);

		//illegal test
		doFind(l,7);
		doFind(l,8);
		doFind(l,-1);
		doFind(null,-1);
	}
}