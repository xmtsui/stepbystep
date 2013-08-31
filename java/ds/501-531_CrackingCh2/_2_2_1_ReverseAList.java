import com.tsui.util.SinglyLinkedList;
class _2_2_1_ReverseAList{
	static void doReverse(SinglyLinkedList<String> list)
	{
		if(list == null)
		{
			System.out.println("ERROR: LIST NULL!");
			return;
		}

		// SinglyLinkedList.Node<String> head = list.head;//严重注意！不能使用任何值拷贝，必须使用本身的引用才能产生变化
		SinglyLinkedList.Node<String> newhead = null;//反转链表的开头
		SinglyLinkedList.Node<String> prev = null;
		if(list.head.next == null)
		{
			newhead = list.head;
		}
		else
		{
			while(list.head != null)
			{
				SinglyLinkedList.Node<String> next = list.head.next;
				list.head.next = prev;
				prev = list.head;
				list.head = next;
			}
			list.head = prev;
		}
	}

	public static void main(String[] args)
	{
		//初始化一个长度为7的单链表
		String[] normal = {"A","B","C","D","E","F","G"};
		SinglyLinkedList<String> normalList = new SinglyLinkedList<String>(normal);
		normalList.doTraverse();
		SinglyLinkedList<String> singleNodeList = new SinglyLinkedList<String>();
		singleNodeList.add("only one");
		singleNodeList.doTraverse();
		
		//legal test
		System.out.println("do reverse...");
		doReverse(normalList);
		normalList.doTraverse();
		
		doReverse(singleNodeList);
		singleNodeList.doTraverse();

		//illegal test
		doReverse(null);
	}
}