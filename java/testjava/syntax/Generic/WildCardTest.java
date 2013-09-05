/**
 * Wildcard test
 * 
 * extends 可用于的返回类型限定，不能用于参数类型限定。
 * super 可用于参数类型限定，不能用于返回类型限定。
 * 
 * 带有super超类型限定的通配符可以向泛型对易用写入，
 * 带有extends子类型限定的通配符可以向泛型对象读取。
 * 
 * http://sharewind.iteye.com/blog/1622164
 */
import java.util.*;
class WildCardTest{
	static class Food{}
	static class Fruit extends Food{}
	static class Apple extends Fruit{}
	static class RedApple extends Apple{}
	
	public static void main(String[] args)
	{
		List<? extends Fruit> flist1 = new ArrayList<Apple>();
		// complie error:
		// 编译器无法确定List所持有的类型，
		// 所以无法安全的向其中添加对象
		// flist1.add(new Apple());
		// flist1.add(new Fruit());
		// flist1.add(new Object());
		// 可以添加null,因为null 可以表示任何类型
		flist1.add(null); // only work for null 

		Fruit fruit = flist1.get(0);//返回的值都能强制转换成Fruit
		Apple apple = (Apple)flist1.get(0);
		Fruit apple1 = (Fruit)flist1.get(0);
		RedApple apple2 = (RedApple)flist1.get(0);

		flist1.contains(new Fruit());
		flist1.contains(new Apple());

		List<? super Fruit> flist2 = new ArrayList<Fruit>();
		flist2.add(new Fruit());
		flist2.add(new Apple());
		flist2.add(new RedApple());
		
		//ok
		List<? super Fruit> flist3 = new ArrayList<Food>();
		
		// compile error:
		// List<? super Fruit> flist4 = new ArrayList<Food>();
		
		// compile error:
		// 编译器无法确定get返回的对象类型是Fruit,还是Fruit的父类Food 或 Object.
		// Fruit item = flist2.get(0);
	}
}