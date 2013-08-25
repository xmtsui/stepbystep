class A{
	public void printValue(){
		System.out.print("A");
	}
}

class S extends A{
	public void printValue(){
		System.out.print("S");
	}
}

public class Test {
	public static void main(String[] args){
		S s=new S();
		s.printValue();
		A as=(A)s;
		as.printValue();
		as=new A();
		as.printValue();

		// String s1="java";
		// String s2="java";
		// System.out.println(s1==s2);
		// System.out.println(s1.equals(s2));

		String s_="ja";
		String s1=s_+"va";
		String s2="java";
		System.out.println(s1==s2);
		System.out.println(s1.equals(s2));
	}
}