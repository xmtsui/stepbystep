/**
 * 输入一个表示整数的字符串，把该字符串转换成整数并输出，
 * 例如输入字符串"345"，则输出整数345。
 * 请完成函数StrToInt，实现字符串转换成整数的功能。
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class String2Int{
	public static int StrToInt(String str)
	{
		str = str.trim();
		if(str == "")
		{
			return 0;
		}

		//trim后，开始一个或0个正负号，然后0个或多个空格，再是至少一个数字
		Pattern p1 = Pattern.compile("^(\\-|\\+)?\\s*[0-9]+");
		Matcher matcher1 = p1.matcher(str);
		
		if(matcher1.find())
		{
			str = str.replace("+", "");
			str = str.trim();
			Matcher matcher2 = p1.matcher(str);
			matcher2.find();
			int start = matcher2.start();
			int end = matcher2.end();
			str = str.substring(start,end);
			str = str.replace(" ", "");
		}
		else
			str="0";

		long value = Long.valueOf(str);
		int value_ret=0;
		if(value >= 2147483647L)
			value_ret=2147483647;
		else if(value <= -2147483648L)
			value_ret=-2147483648;
		else
			value_ret = (int)value;
		return value_ret;
	}
 
	public static void main(String args[]) 
	{ 
		String[] tmp = {"","1","+1","-1",
		"123","-123","010","+00131204", 
		"-01324000","2147483647","-2147483647","-2147483648",
		"2147483648","-2147483649","abc","-abc",
		"1a","23a8f","-3924x8fc","    321",
		"   -321","123 456","123   ","   - 321",
		"   +4488","  +  413"," ++c"," ++1",
		" --2","  -2"};
		for(String item : tmp)
			System.out.println(StrToInt(item));
	} 
}