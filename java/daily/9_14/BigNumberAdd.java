import java.util.Scanner;
import java.math.BigInteger;
import static java.lang.System.*;
class BigNumberAdd{
	public static String addUsingUtil(String a, String b)
	{
		if(a == null || b == null)
			return null;
		BigInteger bia = new BigInteger(a.trim(), 10);
		BigInteger bib = new BigInteger(b.trim(), 10);
		return bia.add(bib).toString();
	}

	public static String add(String a, String b)
	{
		if(a==null || b==null)
			return null;
		char[] ca = a.trim().toCharArray();
		char[] cb = b.trim().toCharArray();
		int lena = ca.length;
		int lenb = cb.length;
		int lenres = lena>lenb ? lena+1 : lenb+1;
		char[] cres = new char[lenres];
		if(isPositive(a) && isPositive(b))
		{
			int carry = 0;
			int res = 0;
			for(int i=lena-1,j=lenb-1,k=0; i>=0&&j>=0&&k<lenres; --i,--j,++k)
			{
				if(carry==1)
				{
					res = ca[i]- '0' + cb[i]-'0' + carry;
					if(res>9)
					{
						carry = 1;
						cres[k] = (char)(res - 10 + '0');
					}
					else
					{
						carry = 0;
						cres[k] = (char)(res + '0');
					}
				}
				else
				{
					res = ca[i]-'0' + cb[i]-'0';
					if(res>9)
					{
						carry = 1;
						cres[k] = (char)(res - 10 + '0');
					}
					else
					{
						carry = 0;
						cres[k] = (char)(res + '0');
					}
				}
			}
			if(carry==1)
				cres[lenres-1] = (char)1+'0';
		}
		for(int i=lenres-1>>1; i>=0; --i)
		{
			// System.out.println("i: " + i + " " + cres[i] + " <-> j: " +(lenres-i-1) + " "+ cres[lenres-i-1]);
			if(i != lenres-i-1)//!!!注意中间点
			{
				cres[i] ^= cres[lenres-i-1];
				cres[lenres-i-1] ^= cres[i];
				cres[i] ^= cres[lenres-i-1];
			}
		}
		return new String(cres);
	}


	private static boolean isPositive(String a)
	{
		return a.trim().charAt(0)!='-' ? true : false;
	}

	public static void main(String[] args)
	{
		String[][] a ={
			{null,null},
			{"0","0"},
			{"1","1"},
			{"-1","-1"},
			{" 90000000000000000000", "90000000000000000000"},
			{"-10000000000000000000", "90000000000000000000"}
		};

		for(String[] item : a)
		{
			System.out.println("1:\t" + addUsingUtil(item[0], item[1]));
			System.out.println("2:\t" + add(item[0], item[1]));
			System.out.println();
		}

		long tmp1 = 9200000002200000000l;
		//long tmp1 = 9300000002200000000l;//溢出
		int tmp2 = 2100000000;
		//int tmp2 = 2200000000;//溢出
	}
}