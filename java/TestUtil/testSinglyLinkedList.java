import com.tsui.util.List;
import com.tsui.util.SinglyLinkedList;
/**
 * 测试单链表
 * @author xmtsui
 * @version v1.0
 */
public class testSinglyLinkedList{
	public static void main(String[] args)
	{
		// ArrayList<String> al = new ArrayList();
		// al.add("test");
		// al.add("123");
		// System.out.println(al.get(0));
		// System.out.println(al.size());
		// System.out.println(al.isEmpty());
		// System.out.println(al.indexOf("test"));
		// System.out.println(al.set(1,"234"));
		// System.out.println(al.getClass());

		Character[] ch = {'A','B','C','D','E',null,null,'A'};
		
		//test: public SinglyLinkedList(E[] element)
		List<Character> list = new SinglyLinkedList<Character>(ch);
		list.doTraverse();
		//test: Query Operations
		//public int size()
		System.out.println("\ntest1 \nsize(): " + list.size());
		//public boolean isEmpty()
		System.out.println("\ntest2 \nisEmpty(): " + list.isEmpty());
		//public boolean contains(Object o)
		System.out.println("\ntest3 \ncontains(A): " + list.contains('A'));
		System.out.println("\ntest4 \ncontains(a): " + list.contains('a'));

		//test: Modification Operations
		//public boolean add(E element)
		System.out.println("\ntest5 \nadd(Z): ");
		list.doTraverse();
		list.add('Z');
		list.doTraverse();

		//public boolean remove(Object o)
		System.out.println("\ntest6 \nremove(new Character('C')): ");
		list.doTraverse();
		list.remove(new Character('C'));
		list.doTraverse();
		
		//test: Positional Access Operations
		
		//public E get(int index)
		System.out.println("\ntest 7 \nget(1): " + list.get(1));
		
		//public E set(int index, E element)
		System.out.println("\ntest 8.1 \nset(0, 'K'): ");
		list.doTraverse();
		System.out.println(list.set(0,'K'));
		list.doTraverse();
		
		System.out.println("\ntest 8.2 \nset(1, 'K'): ");
		list.doTraverse();
		System.out.println(list.set(1,'K'));
		list.doTraverse();
		
		//public void add(int index, E element)
		System.out.println("\ntest 9.1 \nadd(0, 'L'): ");
		list.doTraverse();
		list.add(0,'L');
		list.doTraverse();

		System.out.println("\ntest 9.2 \nadd(1, 'L'): ");
		list.doTraverse();
		list.add(1,'L');
		list.doTraverse();
		
		System.out.println("\ntest 9.3 \nadd(size, 'L'): ");
		list.doTraverse();
		list.add(list.size(),'L');
		list.doTraverse();
		
		//public E remove(int index)
		System.out.println("\ntest 10.1 \nremove(0): ");
		list.doTraverse();
		System.out.println(list.remove(0));
		list.doTraverse();

		System.out.println("\ntest 10.2 \nremove(1): ");
		list.doTraverse();
		System.out.println(list.remove(1));
		list.add('A');
		list.doTraverse();

		//test: Search Operations
		//public int indexOf(Object o)
		System.out.println("\ntest 11 \nindexOf('A'): ");
		System.out.println(list.indexOf('A'));
		//public int lastIndexOf(Object o)
		System.out.println("\ntest 12 \nlastIndexOf('A'): ");
		System.out.println(list.lastIndexOf('A'));

		//test: Bulk operation
		//public void clear()
		System.out.println("\ntest 13 \nclear(): ");
		list.doTraverse();
		list.clear();
		list.doTraverse();
	}
}
