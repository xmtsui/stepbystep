/**
 * 逆转字符串各类题型
 * http://www.ahathinking.com/archives/16.html#more-16
 * 
 * @author xmtsui
 * @version v1.0
 */
import java.util.Arrays;
class ReverseAString{
	static String doReverse(String s)
	{
		if(s==null)
			return null;
		char[] tmp = s.toCharArray();
		int len = tmp.length;
		for(int i=0; i<len/2; ++i)
		{
			char temp = tmp[i];
			tmp[i] = tmp[len-i-1];
			tmp[len-i-1] = temp;
		}
		return new String(tmp);
	}

	/**
	 * 反转内存时，可以两个指针同时走，也可以一个指针
	 * @param  s [description]
	 * @return   [description]
	 */
	static void doReverse1(char[] tmp)
	{
		if(tmp==null)
			return;
		int len = tmp.length;
		for(int i=0, j=len-1; i<j; ++i, --j)
		{
			tmp[i] ^= tmp[j];
			tmp[j] ^= tmp[i];
			tmp[i] ^= tmp[j];
		}
	}

	/**
	 * String a = "tsuiwendy";
	 * 反转后变为wendytsui
	 * @param  s [description]
	 * @return   [description]
	 */
	static String doReverseAjacentMemory(String s, int index)
	{
		if(s==null)
			return null;
		char[] tmp = s.toCharArray();
		char[] tmp1 = Arrays.copyOfRange(tmp, 0, index);
		// char[] tmp1 = new char[2];s.getChars(0,index,tmp1,0);//使用getChars都需要提前分配数组大小
		char[] tmp2 = Arrays.copyOfRange(tmp, index, tmp.length);
		doReverse1(tmp1);
		doReverse1(tmp2);
		System.arraycopy(tmp1,0,tmp,0,tmp1.length);
		System.arraycopy(tmp2,0,tmp,index,tmp2.length);
		doReverse1(tmp);
		return new String(tmp);
	}

	/**
	 * String a = "tsui 123 456 wendy"
	 * res: a = "wendy 123 456 tsui"
	 * @param  s     [description]
	 * @param  index [description]
	 * @return       [description]
	 */
	static String doReverseNonAjacentMemory(String s, String subs)
	{
		char[] tmp = s.toCharArray();
		String[] tmps = s.split(subs);
		char[] tmp1 = tmps[0].toCharArray();
		char[] tmp2 = subs.toCharArray();
		char[] tmp3 = tmps[1].toCharArray();
		doReverse1(tmp1);
		doReverse1(tmp2);
		doReverse1(tmp3);
		System.arraycopy(tmp1,0,tmp,0,tmp1.length);
		System.arraycopy(tmp2,0,tmp,tmp1.length,tmp2.length);
		System.arraycopy(tmp3,0,tmp,tmp1.length+tmp2.length,tmp3.length);
		doReverse1(tmp);
		return new String(tmp);
	}

	/**
	 * http://www.ahathinking.com/archives/17.html
	 * @param  s       [description]
	 * @param  delimit [description]
	 * @return         [description]
	 */
	static String doReverseByDelimiter(String s, String delimit)
	{
		char[] tmpall = s.toCharArray();
		String[] tmps = s.split(delimit);
		int pos=0;
		for(String item : tmps)
		{
			char[] tmp = item.toCharArray();
			doReverse1(tmp);
			System.arraycopy(tmp,0,tmpall,pos,tmp.length);
			pos+=tmp.length;
			if((pos+delimit.length())<=tmpall.length)
				System.arraycopy(delimit.toCharArray(),0,tmpall,pos,delimit.length());
			pos+=delimit.length();
		}
		doReverse1(tmpall);
		return new String(tmpall);
	}

	public static void main(String[] args)
	{
		String s = "nihaoa, wo shi cuixiaomin";
		System.out.println(doReverse(s));

		String res = doReverseAjacentMemory("tsuiwendy",4);
		System.out.println(res);
		
		res = doReverseNonAjacentMemory("tsui123 456wendy","123 456");
			System.out.println(res);

		res = doReverseByDelimiter("tsui 123 uuu 456 wendy"," ");
			System.out.println(res);
	}
}