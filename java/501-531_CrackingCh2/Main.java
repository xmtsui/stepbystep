public class Main{
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
		SinglyLinkedList<Character> list = new SinglyLinkedList<Character>(ch);
		list.doTraverse();
		//test: Query Operations
		//public int size()
		System.out.println("\ntest1 | size(): " + list.size());//3
		//public boolean isEmpty()
		System.out.println("\ntest2 | isEmpty(): " + list.isEmpty());//false
		//public boolean contains(Object o)
		System.out.println("\ntest3 | contains(A): " + list.contains('A'));//true
		System.out.println("\ntest4 | contains(a): " + list.contains('a'));//false

		//test: Modification Operations
		//public boolean add(E element)
		System.out.println("\ntest5 | add(Z): ");
		list.doTraverse();
		list.add('Z');
		list.doTraverse();

		//public boolean remove(Object o)
		System.out.println("\ntest6 | remove(new Character('C')): ");
		list.doTraverse();
		list.remove(new Character('C'));
		list.doTraverse();
		
		//test: Positional Access Operations
		
		//public E get(int index)
		System.out.println("\ntest 7 | get(1): " + list.get(1));
		
		//public E set(int index, E element)
		System.out.println("\ntest 8.1 | set(0, 'K'): ");
		list.doTraverse();
		System.out.println(list.set(0,'K'));
		list.doTraverse();
		
		System.out.println("\ntest 8.2 | set(1, 'K'): ");
		list.doTraverse();
		System.out.println(list.set(1,'K'));
		list.doTraverse();
		
		//public void add(int index, E element)
		System.out.println("\ntest 9.1 | add(0, 'L'): ");
		list.doTraverse();
		list.add(0,'L');
		list.doTraverse();

		System.out.println("\ntest 9.2 | add(1, 'L'): ");
		list.doTraverse();
		list.add(1,'L');
		list.doTraverse();
		
		System.out.println("\ntest 9.3 | add(size, 'L'): ");
		list.doTraverse();
		list.add(list.size(),'L');
		list.doTraverse();
		
		//public E remove(int index)
		System.out.println("\ntest 10.1 | remove(0): ");
		list.doTraverse();
		System.out.println(list.remove(0));
		list.doTraverse();

		System.out.println("\ntest 10.2 | remove(1): ");
		list.doTraverse();
		System.out.println(list.remove(1));
		list.doTraverse();

		//test: Search Operations
		//public int indexOf(Object o)
		System.out.println("\ntest 11 | indexOf('A'): ");
		System.out.println(list.indexOf('A'));
		//public int lastIndexOf(Object o)
		System.out.println("\ntest 12 | lastIndexOf('A'): ");
		System.out.println(list.lastIndexOf('A'));

		//test: Bulk operation
		//public void clear()
		System.out.println("\ntest 13 | clear(): ");
		list.doTraverse();
		list.clear();
		list.doTraverse();

	}
}
