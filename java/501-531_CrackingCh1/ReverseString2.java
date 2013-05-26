class ReverseString2{
	static void doReverse(char[] str_ch){
		int len = str_ch.length;
		int start=0;
		int end=len-1;
		for(int i=0; i<len; ++i)
		{
			if(str_ch[i] == ' ' || i == len-1)
			{
				if(i==len-1)
					end = i;
				else
					end = i-1;
				int lentmp = end - start + 1;
				if(lentmp == 1)
				{
					start = i+1;
					continue;
				}
				for(int j=(lentmp-1) >> 1; j>=0; --j)
				{
					if((start+j) == (start+lentmp-j-1))
						continue;
					str_ch[start+j] ^= str_ch[start+lentmp-j-1];
					str_ch[start+lentmp-j-1] ^= str_ch[start+j];
					str_ch[start+j] ^= str_ch[start+lentmp-j-1];
				}
				start = i+1;
			}
		}
	}
	
	static void doReverse1(char[] str_ch){

	}

	public static void main(String[] agrs){
		String t = "i am a student";
		String tt = ReverseString.doReverse(t);
		char[] t_ch = tt.toCharArray();
		doReverse(t_ch);
		System.out.println(new String(t_ch));
		// System.out.println(doReverse1(t_ch));
	}
}