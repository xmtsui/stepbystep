class test{
	static final boolean t;
	static boolean f;
	static{
		t=false;
	}
	test(){
		// f=false;
	}

	public static void main(String[] args)
	{
		System.out.println(t);
		System.out.println(new test().f);

		int count=0;
		while(true)
		{
			try{
				if(count++ == 1){
					throw new Exception1();
				}else if(count == 3){
					throw new Exception2();
				}
				System.out.println("no exception thrown");
			}catch (Exception1 e){
				System.out.println("Exception1 thrown");
			}catch (Exception2 e){
				System.out.println("Exception2 thrown");
				break;
			}finally{
				System.out.println("finally");
			}
		}


		int x=4;
		switch(x){
			case 1: System.out.println("Test 1 "); break;
			case 2:
			case 3: System.out.println("Test 2 "); break;
			default: System.out.println("end");
		}
	}
}

class Exception1 extends Throwable{

}
class Exception2 extends Throwable{

}