/**
 * Test assertion
 */
class TestSomething3{
	public static void main(String[] args)
	{
		int head = 1;
		assert head == 2;
		int[] elements = {1,2,3,4};
		head = (head - 1) & (elements.length - 1);
		System.out.println(head);
	}
}