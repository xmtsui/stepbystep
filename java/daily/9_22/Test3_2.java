/**
 * Longest-Common-Substring
 * 
 * 这个LCS跟前面说的最长公共子序列的LCS不一样，不过也算是LCS的一个变体，
 * 在LCS中，子序列是不必要求连续的，而子串则是“连续”的。即：
 * 题：给定两个字符串X，Y，求二者最长的公共子串，例如X=[aaaba]，Y=[abaa]。
 * 二者的最长公共子串为[aba]，长度为3。
 */
class Test3_2{
	public static int doFind(String a)
	{
		if(a==null)
			return 0;
		int len = a.length;
		char[] tmp = a.toCharArray();
		for(int i=0; i<len; ++i)
		{
		}
	}

	public static void main(String[] args)
	{

	}
}