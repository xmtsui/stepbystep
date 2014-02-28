import java.util.Set;
import java.util.TreeSet;
class TestTreeMap{
	public static void main(String[] args)
	{
		Set<String> sortSet = new TreeSet<String>();
		String test = "test2,test1,test3,test4,test2,test3";
		String[] test_ = test.split(",");
		for(String item : test_)
			sortSet.add(item);

		System.out.println(sortSet.toString());
		String[] test__ = sortSet.toArray(new String[0]);
		for(String item : test__)
			System.out.print(item+",");
	}
}