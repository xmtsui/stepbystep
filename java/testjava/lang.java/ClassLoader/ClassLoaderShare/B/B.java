public class B{
	static {
		System.out.println("Now load B...");
	}
	static A a = new A();
	static {
		System.out.println("A loaded successfully : " + A.class.getClassLoader());
	}
}