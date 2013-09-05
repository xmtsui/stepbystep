class MyString implements MyProxy, Comparable<String>{
	private String s;
	public MyString(String s)
	{
		this.s = s;
	}
	public void say()
	{
		System.out.println("MyString:["+s+"]");
	}
	public int compareTo(String s)
	{
		return this.s.compareTo(s);
	}
}