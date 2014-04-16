import java.util.Map;
import java.util.HashMap;
public interface Parser<T>{
	public Map<String,Object> parse(Map<String, T> map);
}