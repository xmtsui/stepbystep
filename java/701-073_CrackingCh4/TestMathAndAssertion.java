/**
 * 测试math包以及断言
 *
 * java -ea 打开断言
 * java -da 关闭断言
 *
 * @author xmtsui
 * @version v1.0
 */
class TestMathAndAssertion{
	public static void main(String[] args)
	{
		boolean t = true;
		assert t == false;
		if(true){
			System.exit(0);
		}
		System.out.println("pow:" + (int)(Math.pow(2,4)-1));
	}
}