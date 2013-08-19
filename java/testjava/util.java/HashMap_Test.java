class HashMap_Test{
	public static void main(String[] args)
	{
		System.out.println(hash(0));
		System.out.println(hash(1));
		System.out.println(hash(8));
		System.out.println(hash(1024));
	}

	static int hash(int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	static int indexFor(int h, int length) {
        return h & (length-1);
    }
}