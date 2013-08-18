/**
 * 测试BitSet数据结构
 * 
 * valueOf，以及toByteArray()方法都是1.7新加的
 * @author 转自http://blog.csdn.net/cpfeed/article/details/7342480
 * @version v1.0
 */
import java.util.BitSet;
class TestBitSet{
	private static int wordIndex(int bitIndex) {
        return bitIndex >> 6;
    }
	public static void main(String[] args)
	{
		BitSet bs = new BitSet();
		bs.set(0, true);
		bs.set(1, true);
		bs.set(2, true);
		bs.set(3, true);
		bs.set(4, true);
		System.out.println(bs.toString());

		BitSet bs1 = new BitSet();
		bs1.set(0, true);
		bs1.set(2, true);
		bs1.set(3, true);
		bs.andNot(bs1);
		// bs.set(10, true);
		// bs.set(20, false);
		System.out.println(bs.length());
		System.out.println(bs.toString());

		System.out.println("test >> 6 in worIndex:" + TestBitSet.wordIndex(63));

		Integer i=2147483647;
		// byte[] bb = 
		i>>=31;
		System.out.println(i + " " + i.toBinaryString(i));
	}

}