import java.io.*;
class ChineseLength{
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		System.out.println("中国".length());

		String s = "World";
		System.out.println(s);

		int a = 1;
		String s1 = "A";
		Object obj = "B";
		modifyInt(a);
		modifyString(s1);
		System.out.println(a);
		System.out.println(s1);
		modifyObject(obj);
		System.out.println(obj);

		String sa = "你好";
		System.out.println(sa.getBytes("UTF-8").length);
		System.out.println(sa.getBytes("GBK").length);
		System.out.println(sa.getBytes("ISO-8859-1").length);
		System.out.println(new String(sa.getBytes("ISO-8859-1"),"ISO-8859-1"));
		System.out.println(new String(sa.getBytes("GBK"),"GBK"));
	}

	public static void hello(String s)
	{
		s = "hello " + s;
	}

	public static void modifyInt(int i)
	{
		i++;
	}

	public static void modifyString(String s)
	{
		s = s + s;
	}

	public static void modifyObject(Object obj)
	{
		obj = "C";
	}
}