/**
 * 题目：给你100000个长度不超过10的单词。
 * 对于每一个单词，我们要判断他出没出现过，如果出现了，
 * 求第一次出现在第几个位置。
 */
class CheckAWordExisted{
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

	public static int doCheck(String[] words, String word)
	{
		if(words == null)
			return -1;
		int len = words.length;
		SimpleHash simple = new SimpleHash(len, 100);
		StringHash[] sh = new StringHash[len];
		for(int i=0; i<len; ++i)
			sh[i] = new StringHash();
		for(int i=0; i<len; ++i)
		{
			// int index = indexFor(hash(words[i].hashCode()),len);
			int index = indexFor(simple.hash(words[i]),len);
			if(sh[index].pos == -1)
			{
				// if(index==1552)
					// System.out.println(words[index]);
				sh[index].pos = i;
				sh[index].value = words[i];
			}
			else
			{
				//若hash冲突，先检测是不是同一个引用,再检测内容是否相等
				if(words[i] == sh[index].value)
				{
					System.out.println("continue");
					continue;
				}
				else
				{
					// System.out.println("oho");
					StringHash tmp = sh[index];
					while(tmp.next!=null)
						tmp = tmp.next;
					tmp.next = new StringHash();
					tmp.next.pos = i;
					tmp.next.value = words[i];
				}
			}
		}

		// int index = indexFor(hash(word.hashCode()),len);
		int index = indexFor(simple.hash(word),len);
		System.out.println(word);
		System.out.println(index);
		System.out.println(sh[index].value);

		StringHash tmp = sh[index];
		if(tmp.pos!=-1)
		{
			while(tmp!=null)//查找链地址
			{
				System.out.println();
				System.out.println("he1: "+tmp.value);
				System.out.println("he1 hash: "+hash(tmp.value.hashCode()));
				System.out.println("he1 index: "+indexFor(hash(tmp.value.hashCode()),len));
				System.out.println("he2: "+word);
				System.out.println("he2 hash: "+hash(word.hashCode()));
				System.out.println("he2 index: "+indexFor(hash(word.hashCode()),len));
				if(tmp.value==word || tmp.value.equals(word))//同一个引用或者值相等
					return tmp.pos;
				else
				{
					System.out.println("else: "+tmp.value);
					tmp = tmp.next;
				}
			}
			return -1;
		}
		else
			return -1;//地址为空，查找失败
	}



	static int hash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	static int indexFor(int h, int length) {
		return h & (length-1);
	}

	static class StringHash{
		int pos = -1;
		String value;
		StringHash next;
	}

	public static void main(String[] args)
	{
		int KB = 1024;
		int MB = 1024*1024;
		int TenThousand = 100000;
		String[] words = new String[TenThousand];

		for(int i=0; i<TenThousand; ++i)
		{
			int rand = (int)(10*Math.random());
			words[i] = "abcd" + i;
			/*if(rand%2==0)
				words[i] = "abcd" + i;
			else
				words[i] = "bcda" + i;*/
		}

		long s = System.currentTimeMillis();
		System.out.println("\nfind: " + doCheck(words, "abcd10"));
		long e = System.currentTimeMillis();
		System.out.println("time consumed: " + (e-s));

		/*
		for(String item:words)
		{
			System.out.print(item+" ");
		}
		System.out.println();
		String a = "abc";
		String b = "你好";
		System.out.println(a.length());
		System.out.println(a.getBytes().length);
		System.out.println(b.length());
		System.out.println(b.getBytes().length);
		*/
	}
}
