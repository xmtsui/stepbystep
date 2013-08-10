import static java.lang.Math.log;
import static java.lang.Math.log10;
import static java.lang.Math.floor;
import static java.lang.Math.ceil;
class TestMathBasicAPI{
	public static void main(String[] args)
	{
		//logx(y)
		double r1 = log((double)11)/log((double)2);
		System.out.println(floor(r1));
		System.out.println(ceil(r1));
		double r2 = log10((double)11)/log10((double)2);
		System.out.println((int)r2);
	}
}