/**
 * test default method and static method of interface
 * 
 */
public class DefaultAndStaticMethodOfInterface {
	public static void main(String[] args){
		InterfaceTest.staticMethod();
		ImplTest implTest = new ImplTest();
		implTest.defaultMethod();
		
		InterfaceAnother.staticMethod();
		ImplAnother implAnother = new ImplAnother();
		implAnother.defaultMethod();
	}
}


interface InterfaceTest {
	static void staticMethod() {
		System.out.println("this is a static method");
	}

	default void defaultMethod() {
		System.out.println("this is a default method");
	}
}

class ImplTest implements InterfaceTest {
}


interface InterfaceAnother {
	static void staticMethod() {
		System.out.println("this is another static method");
	}

	default void defaultMethod() {
		System.out.println("this is another default method");
	}
}

class ImplAnother implements InterfaceTest, InterfaceAnother {
	/**
	 * here must override, because same method conflict in {@link InterfaceTest} and {@link InterfaceAnother}
	 */
	@Override
	public void defaultMethod() {
		System.out.println("this is Override default method");
	}
}