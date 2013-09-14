import java.util.LinkedList;
class ReverseALinkedList{
	private static class Node<T> {
		T value;
		Node<T> next;
		Node(T value, Node<T> next)
		{
			this.value = value;
			this.next = next;
		}
	}

	static <T> void printReverse(Node<T> node)
	{
		if(node == null)
			return;
		else
		{
			printReverse(node.next);
			System.out.println(node.value);
		}
	}

	public static void main(String[] args)
	{
		Node<String> A = new Node<String>("A", null);
		Node<String> B = new Node<String>("B", null);
		Node<String> C = new Node<String>("C", null);
		Node<String> D = new Node<String>("D", null);
		Node<String> E = new Node<String>("E", null);
		Node<String> F = new Node<String>("F", null);
		Node<String> G = new Node<String>("G", null);

		A.next = B;
		B.next = C;
		C.next = D;
		D.next = E;
		E.next = F;
		F.next = G;
		printReverse(A);
	}
}