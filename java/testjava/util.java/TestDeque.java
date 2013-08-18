import java.util.List;
import java.util.LinkedList;
import java.util.Deque;
class TestDeque{
	public static void main(String[] args)
	{
		Deque<String> list = new LinkedList<String>();
		// list.offer("A");
		// list.offer("B");
		// list.offer("C");
		// System.out.println(list.poll());
		list.push("A");
		list.push("B");
		list.push("C");
		System.out.println(list.pop());

	}
}