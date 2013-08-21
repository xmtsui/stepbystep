class StackAllocation{
	public static void main(String[] args)
	{
		int kb=1024;
		int mb=kb<<10;
		Runtime rt = Runtime.getRuntime();

		long total = rt.totalMemory();
		long max = rt.maxMemory();
		System.out.println("total: \t"+total+" \tb| "+total/mb+" \tmb");
		System.out.println("max: \t"+max+" \tb| "+max/mb+" \tmb");

		long free1 = rt.freeMemory();
		System.out.println("free1: \t"+free1+" \tb| "+free1/mb+" \tmb");
		
		int[] test = new int[2];
		// Object[] test = new Object[10];
		long free2 = rt.freeMemory();
		System.out.println("free2: \t"+free2+" \tb| "+free2/mb+" \tmb");

		System.out.println("used: \t"+(free1-free2)+" \tb| "+(free1-free2)/mb+" \tmb");
		try{
			Thread.sleep(1000*100);
		}catch (java.lang.InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}