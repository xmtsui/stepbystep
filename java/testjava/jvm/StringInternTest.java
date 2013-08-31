class StringInternTest{
	public static void main(String[] args)
	{
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
		System.out.println("=================");

		String s1 = "ja";
		String s2 = "va";
		String s3 = s1 + s2;
		String s4 = "java";
		String s5 = "ja" + "va";
		System.out.println("s3==s4: " + (s3==s4));
		System.out.println("s4==s5: " + (s4==s5));
		System.out.println("s3.()==s4.(): " + (s3.intern()==s4.intern()));
		System.out.println("s4.()==s5.(): " + (s4.intern()==s5.intern()));

		String ss1 = "java";
		String ss2 = new StringBuilder("java").toString();
		System.out.println("ss1 == ss2: " + (ss1 == ss2));
		System.out.println("ss1.intern() == ss2.intern(): " + (ss1.intern() == ss2.intern()));
		System.out.println("=================");

		String s_zh_1 = "计算机";
		String s_zh_2 = "软件";
		String s_zh_3 = s_zh_1 + s_zh_2;
		String s_zh_4 = "计算机软件";
		String s_zh_5 = "计算机" + "软件";
		System.out.println("s_zh_3==s_zh_4: " + (s_zh_3==s_zh_4));
		System.out.println("s_zh_4==s_zh_5: " + (s_zh_4==s_zh_5));
		System.out.println("s_zh_3.()==s_zh_4.(): " + (s_zh_3.intern()==s_zh_4.intern()));
		System.out.println("s_zh_4.()==s_zh_5.(): " + (s_zh_4.intern()==s_zh_5.intern()));

		String ss_zh_1 = "计算机软件";
		String ss_zh_2 = new StringBuilder("计算机软件").toString();
		System.out.println("ss_zh_1 == ss_zh_2: " + (ss_zh_1 == ss_zh_2));
		System.out.println("ss_zh_1.intern() == ss_zh_2.intern(): " + (ss_zh_1.intern() == ss_zh_2.intern()));
	}
}