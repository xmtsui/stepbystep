import java.util.*;
class TestClassCastException{
	public static void main(String[] args)
	{
		Object x = new Stack<String>();
		System.out.println((Vector)x);

		/*jdk实例*/
		Object x = new Integer(0);
		System.out.println((String)x);//抛出异常
	}
}