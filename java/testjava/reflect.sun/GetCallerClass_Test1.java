public class GetCallerClass_Test1 {
	public static void main(String[] args) {
		A.print();
		
	}
}
class A {
	public static void print() {
		System.out.println(sun.reflect.Reflection.getCallerClass(0));
		System.out.println(sun.reflect.Reflection.getCallerClass(1));
		System.out.println(sun.reflect.Reflection.getCallerClass(2));
		System.out.println(sun.reflect.Reflection.getCallerClass(3));
		System.out.println(sun.reflect.Reflection.getCallerClass(4));
	}
}