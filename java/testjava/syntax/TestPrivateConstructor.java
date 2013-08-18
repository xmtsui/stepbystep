class TestPrivateConstructor{
	public static void main(String[] args)
	{
		B b = new B();
	}
}

class A{
	private A(){};
}

class B extends A{

}