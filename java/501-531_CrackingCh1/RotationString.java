/**
 * Assume you have a method isSubstring which checks if one word is a substring of another. 
 * Given two strings, s1 and s2, 
 * write code to check if s2 is a rotation of s1 using only one call to isSubstring 
 * (i.e., “waterbottle” is a rotation of “erbottlewat”).
 *
 * @author xmtsui
 */
class RotationString{
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

	static boolean isRotationString(String str1, String str2)
	{
		if(str1.length() == str2.length())
		{
			String str_concatenate = str1 + str1;
			return isSubString(str_concatenate, str2);
		}
		else
		{
			return false;
		}
	}
	public static void main(String[] args)
	{
		System.out.println(isRotationString("abc", "bca"));
	}
}