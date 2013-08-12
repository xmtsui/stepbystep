/**
 *   关于时钟的好文章
 *   https://blogs.oracle.com/dholmes/entry/inside_the_hotspot_vm_clocks
 *   
 *   测试nanoTime();
 *   * <p> For example, to measure how long some code takes to execute:
     *  <pre> {@code
     * long startTime = System.nanoTime();
     * // ... the code being measured ...
     * long estimatedTime = System.nanoTime() - startTime;}</pre>
     *
     * <p>To compare two nanoTime values
     *  <pre> {@code
     * long t0 = System.nanoTime();
     * ...
     * long t1 = System.nanoTime();}</pre>
     *
     * one should use {@code t1 - t0 < 0}, not {@code t1 < t0},
     * because of the possibility of numerical overflow.
 */
class SystemBasicAPI_Test{
	static void test() {
		System.out.println("currentTimeMillis: "+System.currentTimeMillis());
		System.out.println("nanoTime         : "+System.nanoTime());
		System.out.println();
		testNano(false);// to sync with currentTimeMillis() timer tick
		for(int xa=0; xa<10; xa++) {
			testNano(true);
		}
	}

	static void testNano(boolean shw) {
		long strMS=System.currentTimeMillis();
		long strNS=System.nanoTime();
		long curMS;
		while((curMS=System.currentTimeMillis()) == strMS) 
		{
			if(shw) 
				System.out.println("Nano: "+(System.nanoTime()-strNS));
		}
		if(shw)
			System.out.println("Nano: "+(System.nanoTime()-strNS)+", Milli: "+(curMS-strMS)); 
	}

	public static void main(String[] args)
	{
		// System.out.println(System.currentTimeMillis()+"ms");
		// System.out.println(System.nanoTime()+"ns");
		test();
	}
}