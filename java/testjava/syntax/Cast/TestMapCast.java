import java.util.Map;
import java.util.HashMap;
class TestMapCast{
	public static void main(String[] args)
	{
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("1", "2");
		// Map<String, Object> map2 = testPut(map1);
		Map<String, Object> map2;
		Parser<String> p = new RuleParser<String>();
		map2 = p.parse(map1);
		System.out.println(map2.get("1").getClass());
	}

	// static <T> Map<String, Object> testPut(Map<String, T> map)
	// {
	// 	System.out.println(map.get("1"));
	// 	Integer i = new Integer(2);
	// 	Map<String, Object> map2 = new HashMap<String, Object>();
	// 	map2.put("1", i);
	// 	return map2;
	// }
}