import java.util.List;
import java.util.ArrayList;
class TestSomeThing1{
	//java static final顺序无影响
	//对比c 里面的 const int, int const
	private static final int MM = 0;
	private final static int NN = 1;
	public static void main(String[] args)
	{
		List<String> l = new ArrayList<String>();
		l.add("test");
		l.add(1,"1test");
		for(String item: l)
			System.out.println(item);
		test t = new test();
		System.out.println("MM: " + t.MM);
		System.out.println("NN: " + t.NN);
	}
}