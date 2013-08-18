/**
 * 用于针对“范型数组”这个话题的测试
 * 重要的结论：java中不能使用范型数组（尽管可以定义成功）
 * 具体原因后面分析
 *
 * @author xmtsui
 * @version v1.0
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
class TestRawTypeArray{
	public static void main(String[] args)
	{	
		/*可以以这种方式声明,编译的时候有警告*/
		List<String>[] stringList = new ArrayList[10];
		/*不可以以这种方式声明,无法通过编译*/
		// List<String>[] stringList = new ArrayList<String>[10];
		stringList[0] = new ArrayList();
		stringList[0].add("test");
		System.out.println("output:" + stringList[0].get(0));

		/*分析范型数组不安全的原因*/
		Object[] newList = stringList;
		List<Integer> intList = new ArrayList<Integer>();
		intList.add(1);
		newList[0] = intList;
		/*获取数据的时候抛出java.lang.ClassCastException*/
		System.out.println(stringList[0].get(0));
		System.out.println(intList.get(0));
		
		/*因此数组的类型不可以是类型变量，除非是采用通配符的方式*/
		List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
		Object o = lsa;
		Object[] oa = (Object[]) o;
		List<Integer> li = new ArrayList<Integer>();
		li.add(new Integer(3));
		oa[1] = li; // Correct.
		//String s = (String) lsa[1].get(0); // Run time error, but cast is explicit.
		Integer s = (Integer) lsa[1].get(0); // Right
		System.out.println("s: " + s);


		/*instanceof操作符只能跟原生态类型*/
		Set<String> set = new HashSet<String>();
		set.add("1");
		set.add("2");
		set.add("3");
		set.add("4");
		set.add("5");
		testMethod(set);
	}
	
	/**
	 * testMethod
	 * @param collection [description]
	 */
	static void testMethod(Collection<?> collection) {
		/*这里instancdof后面跟的就是原生态类型*/
		/*而确定了类型之后就要将其转换为相应的泛型类型*/
		if (collection instanceof Set) {  
			Set<?> s = (Set<?>) collection;
			for (Object e : s) {  
				System.out.println(e);
			}
		}
	}
}