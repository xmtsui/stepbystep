/**
 * 测试int[] i = new int[0];
 *
 * @author xmtsui
 * @version v1.0
 */
class TestIntArray0{
	private static int[] j;
	private int[] h;
	public static void main(String[] args)
	{
		int[] i = new int[0];
		int[] k = null ;
		System.out.println(i == null);
		System.out.println(j == null);
		System.out.println(k == null);
		TestIntArray0 t = new TestIntArray0();
		System.out.println(t.h == null);

		int[] o = {1,2,3,4,5};
		i = o;
		System.out.println(i[0] == 1);
	}
}