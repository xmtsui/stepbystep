import java.util.List;
import java.util.LinkedList;
class Test{
	public static void main(String[] args)
	{
		List<String> l1 = new LinkedList<String>();
		List<Integer> l2 = new LinkedList<Integer>();
		System.out.println(l1 instanceof List);
		System.out.println(l2 instanceof List);
		// System.out.println(l1 instanceof List<String>);
		// System.out.println(l2 instanceof List<String>);
		// System.out.println(l1 instanceof List<Integer>);
		// System.out.println(l2 instanceof List<Integer>);
	}
}