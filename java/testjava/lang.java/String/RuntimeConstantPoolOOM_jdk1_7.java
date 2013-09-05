class RuntimeConstantPoolOOM_jdk1_7{
	public static void main(String[] args)
	{
		String s = "计算机软件";
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
		String str3 = new StringBuilder("RuntimeConstantPoolOOM_jdk1_7").append("").toString();
		System.out.println(str3.intern() == str3);
	}
}