import java.util.Scanner;
import java.math.BigInteger;
class From1ToN {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<size; ++i)
			sb.append('9');
		BigInteger bi = new BigInteger(sb.toString());
		System.out.println(bi.toString());
	}
}