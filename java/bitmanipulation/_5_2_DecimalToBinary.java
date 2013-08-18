/**
 * Given a (decimal - e.g. 3.72) number that is passed in as a string, 
 * print the binary rep- resentation. If the number can not be 
 * represented accurately in binary, print “ERROR”.
 * 
 * 注意：若32位小数仍无法表示则认为该数无法用二进制准确表示。
 * 更为准确的是判断标准为：在转换的任意阶段，若小数部分的最后一个数字非5，
 * 在该数无法被二进制数准确表示。
 *
 * @author xmtsui
 * @version v1.0
 */
class _5_2_DecimalToBinary{
	static String doChange(String adecimal)
	{
		int intPart = Integer.parseInt(
			adecimal.substring(0, adecimal.indexOf('.'))
			);

		double decPart = Double.parseDouble(
			adecimal.substring(adecimal.indexOf('.'), adecimal.length())
			);

		StringBuilder int_string = new StringBuilder();
		while(intPart > 0)
		{
			int r = intPart % 2;
			intPart >>= 1;
			int_string.insert(0, r);//整数部分取余后是在前面insert
		}

		StringBuilder dec_string = new StringBuilder();
		while(decPart > 0)
		{
			if(adecimal.length()>32)
				return "ERROR";
			double r = decPart * 2;
			if(r > 1)
			{
				return "ERROR";
			}
			else if(r == 1)
			{
				dec_string.append(1);//小数部分是apend
				decPart = r - 1;
			}
			else
			{
				dec_string.append(0);
				decPart = r;
			}
		}
		return int_string.append(".").append(dec_string).toString();
	}

	public static void main(String[] args)
	{
		System.out.println(doChange("0.1"));
		System.out.println(doChange("1.4"));
		System.out.println(doChange("1.25"));
		System.out.println(doChange("1.125"));
		System.out.println(doChange("1.0625"));
	}
}