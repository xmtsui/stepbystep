public class Main{
	public static void main(String[] args)
	{
		ArrayList<String> al = new ArrayList();
		al.add("test");
		al.add("123");
		System.out.println(al.get(0));
		System.out.println(al.size());
		System.out.println(al.isEmpty());
		System.out.println(al.indexOf("test"));
		System.out.println(al.set(1,"234"));
		System.out.println(al.getClass());
		
		SinglyLinkedList<String> ll = new SinglyLinkedList();
		ll.add("ll test");
		ll.add("ll 123");
		ll.add("aaa");
		ll.add("aa");
		ll.add(null);
		System.out.println(ll.size());//3
		System.out.println(ll.isEmpty());//false
		System.out.println(ll.indexOf("aaa"));//2
		System.out.println(ll.contains("aaa"));//true
		System.out.println(ll.contains("aa"));//false
		ll.doTraverse();
		ll.remove(null);
		ll.doTraverse();
		System.out.println(ll.size());//2
		ll.set(0,"ll tttt");
		System.out.println(ll.get(0));//ll tttt
		ll.clear();
		System.out.println(ll.size());//0


	}
}
