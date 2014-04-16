import java.util.Map;
import java.util.HashMap;
class RuleParser<T> implements Parser<T>
{
	public Map<String,Object> parse(Map<String, T> map)
	{
		Map<String, Object> newmap = new HashMap<String, Object>();
		Integer i = new Integer(2);
		newmap.put("1", i);
		return newmap;
	}
}