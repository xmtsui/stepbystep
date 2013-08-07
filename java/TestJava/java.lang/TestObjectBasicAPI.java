class TestObjectBasicAPI{
	public static void main(String[] args)
	{
		Object o = new Sample();
		Object o1 = new Sample();
		System.out.println(o);
		System.out.println(o1);
		System.out.println(o.getClass());
		System.out.println(o.hashCode());
		System.out.println(o1.hashCode());
		System.out.println(o.equals(o1));
		System.out.println(o == o1);
	}
}