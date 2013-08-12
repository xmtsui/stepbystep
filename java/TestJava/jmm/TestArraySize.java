/**
 * 测试数组最大长度
 *
 * 注意：int的范围与所能表示的最大存储容量之间的边界值
 * int max= 1024 * 1024 * 1024 * 2 - 1 	//2gb = 刚好等于int最大值
 *
 * 注意2:java -client / -server的堆内存不同
 * 
 * 备注：mac虚拟机默认jvm 128mb
 * 加上JAVA_OPTS=-Xms64m -Xmx512m 
 * 在{tomcat_home}/bin/catalina.sh的前面，加set JAVA_OPTS='-Xms64 -Xmx512' 
 */
class TestArraySize{
	public static void main(String[] args)
	{
		/*byte 		1byte*/
		/*short 	2byte*/
		/*int 		4byte*/
		/*long 		8byte*/

		/*float 	4byte*/
		/*double 	8byte*/

		/*char		2byte*/
		/*boolean	1byte*/

		// int max = 2147483647;
		// int max ~ 2000000000;
		
		// int kb = 1024;						//1kb = 1024 					字节byte
		int mb = 1024 * 1024;					//1mb = 1024*1024 				字节byte
		// int gb = 1024 * 1024 * 1024 			//1gb = 1024*1024*1024 			字节byte
		// int max= 1024 * 1024 * 1024 * 2 - 1 	//2gb = 刚好等于int最大值
		// int max= 1024 * 1024 * 1024 * 2 		//超过int范围
		// int tb = 1024 * 1024 * 1024 * 1024 	//1tb = 1024*1024*1024*1024 	字节byte
		// int pb = 1024 * 1024 * 1024 * 1024 * 1024
		// long max 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 8 - 1//1024*8pb-1 
		
		// int kb = 1000;						//1kb ~ 1000 					字节byte
		// int mb = 1000000;					//1mb ~ 1000*1000 			 	字节byte
		// int gb = 1000 * 1000 * 1000 			//1gb ~ 1000*1000*1000 			字节byte
		// int max~ 1000 * 1000 * 1000 * 2//刚好
		// int max~ 1000 * 1000 * 1000 * 3//超过int范围
		// int tb = 1000 * 1000 * 1000 * 1000 	//1tb ~ 1000*1000*1000*1000 	字节byte
		Runtime rt = Runtime.getRuntime();
		long total = rt.totalMemory();
		long max = rt.maxMemory();
		System.out.println("total : " + total/mb + " mb");
		System.out.println("max : " + max/mb + " mb");

		int MAX=1024*1024;//1mb
		// int MAX=1024*1024*10;//20mb
		
		long free1 = rt.freeMemory();
		boolean seq_big[] = new boolean[MAX];//1mb
		// boolean[] seq_big = new boolean[MAX];//1mb
		// char[] seq_big = new char[MAX];//2mb
		// int[] seq_big = new int[MAX];//4mb
		long free2 = rt.freeMemory();
		System.out.println("used : " + (free1-free2)/mb + " mb");
		
	}
}