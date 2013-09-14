public class SinglyLinkedList<T>{
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

	public static void main(String[] args)
	{
		SinglyLinkedList<String> list = new SinglyLinkedList<String>();
		Node<String> A = new Node<String>("A",null);
		Node<String> B = new Node<String>("B",null);
		Node<String> C = new Node<String>("C",null);
		Node<String> D = new Node<String>("D",null);
		Node<String> E = new Node<String>("E",null);
		A.next = B;
		B.next = C;
		C.next = D;
		D.next = E;
		list.head = A;
		list.doTraverse();
		list.doReverse();
		list.doTraverse();
	}
}