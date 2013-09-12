/**
 * 
 * java里面
 * \\\\ 匹配反斜杠
 * \\- 匹配减号或者把减号放在最后
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class RegexTest{
	public static void main(String[] args)
	{
		System.out.println("com.jd.".replaceAll("\\.", "/") + "MyClass.class/n");
		
		Pattern p = Pattern.compile("[A-Za-z0-9$@#./_\\-%&?!:|\"=¬,;<>]");
		Pattern p1 = Pattern. compile("[\\\\]");
		Matcher matcher = p.matcher( "a");
		Matcher matcher1 = p1.matcher( "\\");
		System. out.println("matcher:" + matcher.find());
		System. out.println("matcher1:" + matcher1.find());
	}
}