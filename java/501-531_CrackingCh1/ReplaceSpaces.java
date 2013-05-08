/**
 * Write a method to replace all spaces in a string with ‘%20’.
 * @author xmtsui
 */
class ReplaceSpaces{

	//
	static String doReplace(String str)
	{
		if(str == null)
			return "";
		return str.replaceAll(" ", "%20");
	}

	//
	static String doReplace1(String str)
	{
		if(str == null)
			return "";

		int space_num=0;
		int len = str.length();

		char[] ch = str.toCharArray();
		for(char item : ch)
		{
			if(item==' ')
				space_num++;
		}
		int new_len = len+space_num*2;
		char[] ch_tmp = new char[new_len];

		for(int i=len-1; i>=0; --i)
		{
			if(ch[i] == ' ')
			{
				ch_tmp[new_len-1]='0';
				ch_tmp[new_len-2]='2';
				ch_tmp[new_len-3]='%';
				new_len-=3;
			}
			else
			{
				ch_tmp[new_len-1]=ch[i];
				new_len-=1;
			}
		}
		return new String(ch_tmp);
	}

	public static void main(String[] args)
	{
		String a=null;
		String b=" ";
		String c="hello world job";
		String d=" h j k l l ";

		System.out.println(doReplace(a) + "|" + doReplace1(a));
		System.out.println(doReplace(b) + "|" + doReplace1(b));
		System.out.println(doReplace(c) + "|" + doReplace1(c));
		System.out.println(doReplace(d) + "|" + doReplace1(d));


		StringBuilder sb = new StringBuilder();
		for(int i=0; i<500; ++i)
		{
			sb.append("a b c d");
		}
		String s = new String(sb);
		long start1=System.currentTimeMillis();		
		doReplace(s);
		long end1=System.currentTimeMillis();		
		long time1=end1-start1;

		long start2=System.currentTimeMillis();		
		doReplace1(s);
		long end2=System.currentTimeMillis();		
		long time2=end2-start2;

		System.out.println("For big data, time1: " + time1 + "| time2: "+ time2);
	}
}