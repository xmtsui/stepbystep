/**
 * java 隐藏了大小端问题
 */
class endian_test{
	public static void main(String[] args)
	{
		short word = 0xffffffff;
		System.out.println(word);
		Integer in = new Integer(1);
		System.out.println(Integer.highestOneBit(-1));
		System.out.println(Integer.lowestOneBit(3));
	}
}