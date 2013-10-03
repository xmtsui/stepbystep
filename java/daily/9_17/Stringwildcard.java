/**
 * unfinished
 * 匹配* 0
 */
import java.util.Scanner;
class Stringwildcard{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String b = sc.nextLine();
		System.out.println(a+"\n"+b);
		if(a!=null && b!=null)
		{
			int l1 = a.length;
			int l2 = b.length;
			char[] c1 = a.toCharArray();
			char[] c2 = b.toCharArray();
			for(int i=0; i<l1; i++)
			{
				for(int j=0; j<l2; j++)
				{
					if(a[j]!=a[j] && a[i]!='?')
						break;
				}
			}
		}
		else
		{
			System.out.println(-1);
		}
	}
}