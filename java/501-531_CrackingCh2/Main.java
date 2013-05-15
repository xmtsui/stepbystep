public class Main{
	public static void main(String[] args)
	{
		ArrayList<String> al = new ArrayList();
		java.util.ArrayList<String> a = new java.util.ArrayList();
		al.add("test");
		al.add("123");
		System.out.println(al.get(0));
		System.out.println(al.size());
		System.out.println(al.isEmpty());
		System.out.println(al.indexOf("test"));
		System.out.println(al.set(1,"234"));
		System.out.println(al.getClass());
	}
}