/**
 * 按照delimiter逆转字符串
 * 要求，空间复杂度O(1)
 * 
 * @author xmtsui
 * @version v1.0
 */
class ReverseString2{
	//局部根据分隔符逆转字符串，本例为空格
	static void doReverse(char[] str_ch){
		int len = str_ch.length;
		int start=0;
		int end=len-1;
		for(int i=0; i<len; ++i)
		{
			if(str_ch[i] == ' ' || i == len-1)
			{
				if(i==len-1)//最后一个子串单独处理
					end = i;
				else
					end = i-1;
				
				int lentmp = end - start + 1;
				
				for(int j=(lentmp-1) >> 1; j>=0; --j)
				{
					if((start+j) == (start+lentmp-j-1))//中间点继续
						continue;
					str_ch[start+j] ^= str_ch[start+lentmp-j-1];
					str_ch[start+lentmp-j-1] ^= str_ch[start+j];
					str_ch[start+j] ^= str_ch[start+lentmp-j-1];
				}
				start = i+1;
			}
		}
	}

	public static void main(String[] agrs){
		String t = "i am a student of 电子科大";
		String tt = ReverseString.doReverse(t);//调用整串不考虑结束符的逆转函数
		
		char[] t_ch = tt.toCharArray();
		doReverse(t_ch);
		System.out.println(new String(t_ch));
	}
}