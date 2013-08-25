/**
 * 不用加减乘除实现加法
 * 
 * @author xmtsui
 * @version v1.0
 */
class _5AddWithoutOperator{
	/**
	 * 注意移位的优先级高于按位与
	 * @param  a [description]
	 * @param  b [description]
	 * @return   [description]
	 */
	static int add1(int a, int b)
	{
		int res = a ^ b;
		int carry = (a & b) << 1;
		while(carry != 0)
		{
			int tmp = res;
			res = res ^ carry;
			carry = (tmp & carry) << 1;
		}
		return res;
	}

	/**
	 * do while注意最后的分号
	 * @param  a [description]
	 * @param  b [description]
	 * @return   [description]
	 */
	static int add2(int a, int b)
	{
		int carry=0;
		int res=0;
		do{
			res = a^b;
			carry = a & b << 1;
			a = res;
			b = carry;
		} while(carry != 0);
		return res;
	}

	public static void main(String[] args)
	{
		System.out.println(~-2);//1
		System.out.println(~-1);//0
		System.out.println(~0);//-1
		System.out.println(~1);//-2
		System.out.println(~2);//-3
		System.out.println(add1(1,2));
		System.out.println(add2(2,3));
		System.out.println(add1(10,20));
		System.out.println(add2(10,20));
	}
}