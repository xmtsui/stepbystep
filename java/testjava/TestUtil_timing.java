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

		System.out.println("For big data, time1: " + time1 + "\t| time2: "+ time2 + "\t| time3: "+ time3);

		/*---------------------*/
		Runtime rt_jmm = Runtime.getRuntime();
		long total_jmm = rt_jmm.totalMemory();
		long max_jmm = rt_jmm.maxMemory();
		int MB_JMM = 1024*1024;
		int KB_JMM = 1024;
		System.out.println("total : " + total_jmm/MB_JMM + " mb");
		System.out.println("max : " + max_jmm/MB_JMM + " mb");

		
		long free1_jmm = rt_jmm.freeMemory();
		long free2_jmm = rt_jmm.freeMemory();
		System.out.println("used : " + (free1_jmm-free2_jmm)/MB_JMM + " mb");

		/*---------------------*/
		printf("%s\n","开始");
		time_t ts1,te1;
		ts1=time(NULL);
		te1=time(NULL);
		printf("%.20f\n",difftime(te1,ts1));

	}
}