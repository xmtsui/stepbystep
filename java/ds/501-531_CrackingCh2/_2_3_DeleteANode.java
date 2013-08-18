/**
 * Implement an algorithm to delete a node in the middle of a single linked list, 
 * given only access to that node.
 * * EXAMPLE:
 * Input: the node ‘c’ from the linked list a->b->c->d->e
 * Result: nothing is returned, but the new linked list looks like a->b->d->e
 *
 * @author xmtsui
 * @version v1.0
 */
import com.tsui.util.SinglyLinkedList;
class _2_3_DeleteANode{
	static boolean doDelete(SinglyLinkedList list, SinglyLinkedList.Node<Character> node)
	{
		if(node == null)
		{
			return false;
		}
		if(node.next == null)
		{
			node = null;
			list.decreaseSize(); 
			return true;
		}
		SinglyLinkedList.Node<Character> tmp = node.next;
		node.item = tmp.item;
		node.next = tmp.next;
		list.decreaseSize(); 
		return true;
	}

	public static void main(String[] args)
	{
		// Character[] ch = {'A','B','C','D','E','A'};
		Character[] ch = {'A','B','C','D','E',null,null,'A'};
		SinglyLinkedList<Character> list = new SinglyLinkedList<Character>(ch);
		SinglyLinkedList.Node<Character> node = list.getNode(6);
		list.doTraverse();
		doDelete(list,node);
		list.doTraverse();
		
		// System.out.println(node.item + node.next.item); // not work
		System.out.println(node.item);// work
		System.out.println(node.next.item);// work
	}
	
}