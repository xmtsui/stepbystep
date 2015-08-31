/**
 * 深入理解JVM p187页
 */
class TryCatchFinnalyTest{
	static int doit(){
		int x=0;
		try{
			x = 1;
			return x;
		}catch(Exception e){
			x = 2;
			return x;
		}finally{
			x = 3;
			System.out.println("First!" + x);
			// return x;
		}
	}

	static Person doit_again(){
		Person p = new Person();
		try{
			p.age = 11;
			return p;
		}catch(Exception e){
			p.age = 12;
			return p;
		}finally{
			p.age = 13;
			System.out.println("First!" + p.age);
			// return p;
		}
	}

	static class Person{
		String name = "a";
		int age = 10;
	}

	public static void main(String[] args)
	{
		System.out.println(doit());
		System.out.println(doit_again().age);
	}
}