class Test{
	public static void main(String[] args)
	{
		Long l = Long.MAX_VALUE;
		Integer i = Integer.MAX_VALUE;
		System.out.println(l);
		long ayear = 365*24*60*60*1000;
		System.out.println(ayear+" ms");
		System.out.println(l/ayear - 1970);
		
		System.out.println(i);
		System.out.println(i/ayear);
	}
}