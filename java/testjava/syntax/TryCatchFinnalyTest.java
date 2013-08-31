/**
 * 深入理解JVM p187页
 */
class TryCatchFinnalyTest{
	static int doit(){
		int x;
		try{
			x = 1;
			return x;
		}catch(Exception e){
			x = 2;
			return x;
		}finally{
			x = 3;
			System.out.println("First!" + x);
		}
	}

	public static void main(String[] args)
	{
		System.out.println(doit());
	}
}