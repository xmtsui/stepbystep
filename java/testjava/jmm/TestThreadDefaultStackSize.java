/**
 * java线程,1.5之后默认1m
 * 
 * 一个进程总的线程数根据系统设置有关
 * ulimit -s
 * @author xmtsui
 * @version v1.0
 */
class TestThreadDefaultStackSize{
	public static void main(String[] args)
	{
		// int kb = 1024;						//1kb = 1024 					字节byte
		int mb = 1024 * 1024;					//1mb = 1024*1024 				字节byte
		// int gb = 1024 * 1024 * 1024 			//1gb = 1024*1024*1024 			字节byte
		// int max= 1024 * 1024 * 1024 * 2 - 1 	//2gb = 刚好等于int最大值
		// int max= 1024 * 1024 * 1024 * 2 		//超过int范围
		// int tb = 1024 * 1024 * 1024 * 1024 	//1tb = 1024*1024*1024*1024 	字节byte
		// int pb = 1024 * 1024 * 1024 * 1024 * 1024
		// long max 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 8 - 1//1024*8pb-1 
		Runtime rt = Runtime.getRuntime();
		long total = rt.totalMemory();
		long max = rt.maxMemory();
		System.out.println("total : " + total/mb + " mb");
		System.out.println("max : " + max/mb + " mb");

		long free1 = rt.freeMemory();
		Thread t = new Thread();
		long free2 = rt.freeMemory();
		System.out.println("used : " + (free1-free2)/mb + " mb");
	}
	
}