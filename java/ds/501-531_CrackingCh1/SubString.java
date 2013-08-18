class SubString{
	static boolean isSubString(String str1, String str2)
	{
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();

		int len1 = ch1.length;
		int len2 = ch2.length;
		for(int i=0; i<len1; ++i)
		{
			if(i+len2 > len1)
				break;
			for(int j=0; j<len2; ++j)
			{
				if(ch1[i+j] == ch2[j])
				{  
					if(j==len2-1)
						return true;
					continue;
				}
				else
					break;
			}
		}
		return false;
	}

	public static void main(String[] args)
	{
		System.out.println(isSubString("aaaabcaaabc", "aaabc"));
	}
}