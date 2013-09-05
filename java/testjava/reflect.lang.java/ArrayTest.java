import java.lang.reflect.Array;
class ArrayTest{
	public static void main(String[] args)
	{
		Object array = Array.newInstance(String.class, 10); // 等 价 于 new String[10]
		Array.set(array, 0, "Hello"); //等价于array[0] = "Hello" 
		Array.set(array, 1, "World"); //等价于array[1] = "World" 
		System.out.println(Array.get(array, 0)); //等价于array[0]
	}
}