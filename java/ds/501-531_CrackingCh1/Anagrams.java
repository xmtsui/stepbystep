/**
 * 
 */
import java.util.Arrays;
class Anagrams{

	//
	static boolean doAnagramsCompare(String s1, String s2)
	{
		if(s1==null || s2==null)
		{
			if(s1==s2)
				return true;
			else
				return false;
		}

		char[] ch1 = s1.toCharArray();
		char[] ch2 = s2.toCharArray();

		int len1 = ch1.length;
		int len2 = ch2.length;
		
		if(len1 != len2)
			return false;

		int flag=0;

		for(int i=0; i<len1; ++i)
		{
			for(int j=0; j<len2; ++j)
			{
				if(ch2[j] == ch1[i])
				{
					flag++;
					ch2[j] = 0;
					break;
				}

			}
		}

		if(flag == len1)
			return true;
		else
			return false;

	}

	//assume ascii;
	static boolean doAnagramsCompare1(String s1, String s2)
	{
		if(s1==null || s2==null)
		{
			if(s1==s2)
				return true;
			else
				return false;
		}

		int len1=s1.length();
		int len2=s2.length();
		
		if(len1 != len2)
			return false;

		int[] letters = new int[256];
		int unique_ch_num=0;
		int already_confirmed=0;
		
		for(char item:s1.toCharArray())
		{
			if(letters[item] == 0)
				unique_ch_num++;
			letters[item]++;
		}

		for(int i=0; i<len2; ++i)
		{
			int tmp = (int)s2.charAt(i);
			
			if(letters[tmp]==0)
				return false;
			
			letters[tmp]--;
			
			if(letters[tmp]==0)
			{
				already_confirmed++;
				if(already_confirmed == unique_ch_num)
				{
					return true;
					// return i==len2-1;
				}
			}
		}
		return false;
	}

	static boolean doAnagramsCompare2(String s1, String s2)
	{
		char[] ch1 = s1.toCharArray();
		char[] ch2 = s2.toCharArray();
		int len1=ch1.length;
		int len2=ch2.length;
		Arrays.sort(ch1);
		Arrays.sort(ch2);
		for(int i=0; i<len1; i++)
		{
			if(ch1[i] != ch2[i])
				return false;
		}
		return true;

	}

	public static void main(String[] args)
	{
		String a = "null";
		String b = "al";
		
		String c = "abc";
		String d = "cba";
		
		String e = "aabbba";
		String f = "aabbab";

		String h = "abbcc";
		String i = "babcc";

		String j = "abcdde";
		String k = "bcaddd";

		System.out.println(doAnagramsCompare(a,b) + "|" + doAnagramsCompare1(a,b) + "|" + doAnagramsCompare2(a,b));
		System.out.println(doAnagramsCompare(c,d) + "|" + doAnagramsCompare1(c,d) + "|" + doAnagramsCompare2(c,d));
		System.out.println(doAnagramsCompare(e,f) + "|" + doAnagramsCompare1(e,f) + "|" + doAnagramsCompare2(e,f));
		System.out.println(doAnagramsCompare(h,i) + "|" + doAnagramsCompare1(h,i) + "|" + doAnagramsCompare2(h,i));
		System.out.println(doAnagramsCompare(j,k) + "|" + doAnagramsCompare1(j,k) + "|" + doAnagramsCompare2(j,k));

		StringBuilder ba = new StringBuilder();
		StringBuilder bb = new StringBuilder();
		for(int idx=0; idx<500; idx++)
		{
			ba.append("abcd");
			bb.append("dcba");
		}
		String bas=ba.toString();
		String bbs=bb.toString();
		
		long start1=System.currentTimeMillis();		
		doAnagramsCompare(bas,bbs);
		long end1=System.currentTimeMillis();		
		long time1=end1-start1;

		long start2=System.currentTimeMillis();		
		doAnagramsCompare1(bas,bbs);
		long end2=System.currentTimeMillis();		
		long time2=end2-start2;
		
		long start3=System.currentTimeMillis();		
		doAnagramsCompare2(bas,bbs);
		long end3=System.currentTimeMillis();		
		long time3=end3-start3;

		System.out.println("For big data, time1: " + time1 + "| time2: "+ time2 + "| time3: " + time3);
	}
}