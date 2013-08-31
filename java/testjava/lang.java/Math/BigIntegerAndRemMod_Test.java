/**
 * mod 取余
 * 基本运算里面只有整除（／）与取余（％）
 * 
 * BigInteger里区分了divide, mod, remainder
 * mod 与 remainder的java里的区别是：
 *  mod(): modulus不能小于等于0，始终返回非负值
 * @author xmtsui
 * @version v1.0
 */
import java.math.BigInteger;
class BigIntegerAndRemMod_Test{
	public static void main(String[] args)
	{
		System.out.println();
		System.out.println("********case 1********");
		//大端模式
		//0111 1111 0000 0001
		byte[] by = {127,1};
		BigInteger bigint1 = new BigInteger(-1, by);
		System.out.println(bigint1);

		System.out.println("********case 2********");
		//指定字符串以及radix（进制）来构造，radix [1~36]
		BigInteger bigint2 = new BigInteger("10", 2);
		System.out.println(bigint2);

		System.out.println("********case 3********");
		//Decimal String
		BigInteger bigint3 = new BigInteger("10");
		System.out.println(bigint3);
		System.out.println(bigint3.nextProbablePrime());

		System.out.println("********case 4********");
		//Static Factory Methods
		BigInteger bigint1_static = BigInteger.valueOf(0x0000000000000010l);
		System.out.println(bigint1_static);
		System.out.println(bigint1_static.shiftLeft(1));
		System.out.println(bigint1_static.shiftRight(1));

		
		System.out.println("********case 5********");
		int a1 = 5, b1 = 3;
		BigInteger a = BigInteger.valueOf(a1);
		BigInteger b = BigInteger.valueOf(b1);
		doDivide(a, b);
		doDivide(a1,b1);
		System.out.println("********case 6********");
		doRemainder(a, b);
		doRemainder(a1,b1);
		System.out.println("********case 7********");
		doMod(a, b);
		doMod(a1,b1);
	}

	static void doDivide(BigInteger a, BigInteger b)
	{
		System.out.printf("%d divide %d = %d\t", a.intValue(), b.intValue(),
			a.divide(b).intValue());
		System.out.printf("%d divide -%d = %d\t", a.intValue(), b.intValue(),
			a.divide(b.negate()).intValue());
		System.out.printf("-%d divide %d = %d\t", a.intValue(), b.intValue(),
			a.negate().divide(b).intValue());
		System.out.printf("-%d divide -%d = %d\t\n", a.intValue(), b.intValue(),
			a.negate().divide(b.negate()).intValue());
	}

	static void doDivide(int a, int b)
	{
		System.out.printf("%d divide %d = %d\t", a, b, a/b);
		System.out.printf("%d divide -%d = %d\t", a, b, a/-b);
		System.out.printf("-%d divide %d = %d\t", a, b, -a/b);
		System.out.printf("-%d divide -%d = %d\t\n", a, b, -a/-b);
	}

	static void doRemainder(BigInteger a, BigInteger b)
	{
		System.out.printf("%d remainder %d = %d\t", a.intValue(), b.intValue(),
			a.remainder(b).intValue());
		System.out.printf("%d remainder -%d = %d\t", a.intValue(), b.intValue(),
			a.remainder(b.negate()).intValue());
		System.out.printf("-%d remainder %d = %d\t", a.intValue(), b.intValue(),
			a.negate().remainder(b).intValue());
		System.out.printf("-%d remainder -%d = %d\t\n", a.intValue(), b.intValue(),
			a.negate().remainder(b.negate()).intValue());
	}

	static void doRemainder(int a, int b)
	{
		System.out.printf("%d remainder %d = %d\t", a, b, a%b);
		System.out.printf("%d remainder -%d = %d\t", a, b, a%-b);
		System.out.printf("-%d remainder %d = %d\t", a, b, -a%b);
		System.out.printf("-%d remainder -%d = %d\t\n", a, b, -a%-b);
	}

	static void doMod(BigInteger a, BigInteger b)
	{
		System.out.printf("%d mod %d = %d\t", a.intValue(), b.intValue(),
			a.mod(b).intValue());
		System.out.printf("%d mod -%d = E\t", a.intValue(), b.intValue());
		// System.out.printf("%d mod -%d = %d\t", a.intValue(), b.intValue(),
		 // a.mod(b.negate()).intValue());
		System.out.printf("-%d mod %d = %d\t", a.intValue(), b.intValue(),
			a.negate().mod(b).intValue());
		// System.out.printf("-%d mod -%d = %d\t", a.intValue(), b.intValue(),
		 // a.negate().mod(b.negate()).intValue());
		System.out.printf("-%d mod -%d = E\t\n", a.intValue(), b.intValue());
	}

	static void doMod(int a, int b)
	{
		System.out.printf("%d mod %d = %d\t", a, b, a%b);
		System.out.printf("%d mod -%d = %d\t", a, b, a%-b);
		System.out.printf("-%d mod %d = %d\t", a, b, -a%b);
		System.out.printf("-%d mod -%d = %d\t\n", a, b, -a%-b);
	}

}