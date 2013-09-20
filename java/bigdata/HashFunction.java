class test{
	static int indexFor(int h, int length) {
		return h & (length-1);
	}
	static int hash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	public static class SimpleHash 
	{ 
		private int cap;
		private int seed;
		public SimpleHash(int cap, int seed)
		{
			this.cap = cap;
			this.seed = seed;
		}

		public int hash(String value)
		{ 
			int result=0;
			int len= value.length();
			for(int i= 0; i< len; i++)
			{
				result = seed* result + value.charAt(i);
			}
			return (cap - 1)& result;
		}
	}

	public static void main(String[] args)
	{
		String key = "nihao";
		int hash = hash(key.hashCode());
		System.out.println(hash);
		int i = indexFor(hash, 20);
		System.out.println(i);

		SimpleHash sh = new SimpleHash(20, 100);
		hash = sh.hash(key);
		i = indexFor(hash, 20);
		System.out.println(i);
	}
}
