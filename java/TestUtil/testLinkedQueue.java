import com.tsui.util.LinkedQueue;
/**
 *
 * @author xmtsui
 * @version v1.0
 */

class testLinkedQueue{
	public static void main(String[] args)
	{
		LinkedQueue<String> lq = new LinkedQueue<String>();
		lq.enqueue("a");
		lq.enqueue("b");
		lq.enqueue("c");
		System.out.println(lq.size()==3);
		System.out.println(lq.toString1());
		System.out.println(lq.dequeue().equals("a"));
		System.out.println(lq.peekFirst().equals("b"));
		System.out.println(lq.size()==2);
		System.out.println(lq.isEmpty()==false);
		System.out.println(lq.toString1());

		lq = new LinkedQueue<String>();
		System.out.println(lq.offer("d"));
		System.out.println(lq.offer("e"));
		System.out.println(lq.offer("f"));
		System.out.println(lq.toString2());
		System.out.println(lq.poll()=="d");
	}
}