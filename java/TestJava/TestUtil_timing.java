/**
 * 计时用
 * 
 * @author xmtsui
 * @version v1.0
 */
class TestUtil_timing{
	public static void main(String[] args)
	{
		long start1 = System.currentTimeMillis();
		long end1 = System.currentTimeMillis();
		long time1 = end1 - start1;

		long start2 = System.currentTimeMillis();
		long end2 = System.currentTimeMillis();
		long time2 = end2 - start2;

		System.out.println("For big data, time1: " + time1 + "| time2: "+ time2);

		/*---------------------*/
		
		long start1 = System.currentTimeMillis();
		long end1 = System.currentTimeMillis();
		long time1 = end1 - start1;

		long start2 = System.currentTimeMillis();
		long end2 = System.currentTimeMillis();
		long time2 = end2 - start2;

		long start3 = System.currentTimeMillis();
		long end3 = System.currentTimeMillis();
		long time3 = end3 - start3;

		System.out.println("For big data, time1: " + time1 + "| time2: "+ time2, "| time3: "+ time3);

	}
}