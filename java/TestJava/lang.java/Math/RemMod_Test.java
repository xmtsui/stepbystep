import java.math.BigInteger;
class RemMod_Test{
	public static void main(String[] args)
	{
		int i=0 + (100-0)*(50-0)/(100-0);
		System.out.println(i);

		BigInteger j = BigInteger.valueOf(1024);
		System.out.println("j is: " + j);
		
		BigInteger o = BigInteger.valueOf(10);
		System.out.println("o is: " + j);

		BigInteger p = j.divide(o);
		System.out.println("j/o is : " + p);//102
		System.out.println("j/o is : " + 1024/10);//102
		
		p = j.remainder(o);//4
		System.out.println("j rem o is : " + p);//4
		System.out.println("j rem o is : " + 1024%10);//4
		
		p = j.mod(o);//4
		System.out.println("j mod o is : " + p);
		System.out.println("j mod o is : " + 1024%10);
		
		p = j.negate().mod(o);//4
		System.out.println("j.negate() mod o is : " + p);
		System.out.println("-j mod o is : " + (-1024%10));//10*(-102)更接近
		System.out.println("j mod -o is : " + (1024%-10));//-10*(-102)更接近
		System.out.println("j mod -o is : " + (-1024%-10));//-10*102更接近
	}
}