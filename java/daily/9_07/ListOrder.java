public class ListOrder<T>{
	private Node<T> head;
	public static class Node<T>{
		T value;
		Node<T> next;
		
		Node(){}

		Node(T value, Node<T> next)
		{
			this.value = value;
			this.next = next;
		}
	}

	public void doTraverse()
	{
		if(head == null)
			System.out.println("NULL");
		Node<T> tmp = head;
		while(tmp != null)
		{
			System.out.print(tmp.value);
			tmp = tmp.next;
		}
		System.out.println();
	}

	public boolean doReverse()
	{
		if(head == null)
			return false;
		Node<T> newHead = head;
		head = head.next;
		newHead.next = null;
		if(head == null)
		{
			head = newHead;
			return true;
		}
		else
		{
			while(head.next != null)
			{
				Node<T> tmp = head.next;
				head.next = newHead;

				newHead = head;
				head = tmp;
			}
			head.next = newHead;
		}
		return true;
	}

	public int length()
	{
		if(head==null)
			return 0;
		int i=0;
		Node<T> tmp = head;
		while(tmp.next!=null)
		{
			i++;
			tmp = tmp.next;
		}
		return i;
	}

	public Node<T> getNode(int i)
	{
		if(head == null)
			return null;
		int len = length();
		if(i<0 || i>len)
			return null;
		int index=0;
		Node<T> node = head;
		while(index<i&&node.next!=null)
		{
			index++;
			node = node.next;
		}
		return node;
	}

	public boolean doOrder()
	{
		if(head==null)
			return false;
		int len = length();
		boolean flag = false;
		for(int i=0; i<len; ++i)
		{
			for(int j=len; j>i; --j)
			{
				if(getNode(j).value.compareTo((T)getNode(j-1).value)>0)
				{
					swap(getNode(j), getNode(j-1));
					flag = true;
				}
			}
			if(!flag)
				break;
		}
		return true;
	}

	private void swap(Node<T> i, Node<T> j)
	{
		T tmp = i.value;
		i.value = j.value;
		j.value = tmp;
	}

	public static void main(String[] args)
	{
		ListOrder<Integer> list = new ListOrder<Integer>();
		Node<Integer> A = new Node<Integer>(3,null);
		Node<Integer> B = new Node<Integer>(1,null);
		Node<Integer> C = new Node<Integer>(7,null);
		Node<Integer> D = new Node<Integer>(4,null);
		Node<Integer> E = new Node<Integer>(5,null);
		A.next = B;
		B.next = C;
		C.next = D;
		D.next = E;
		list.head = A;
		list.doTraverse();
		// list.doReverse();
		list.doOrder();
		list.doTraverse();
	}
}