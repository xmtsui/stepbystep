class TestOperator{
	public static void main(String[] args){
		int i = 9;
		int j = 10;
		System.out.println(i/j);
		System.out.println(j/i);

		System.out.println(190%91);
		System.out.println(j%i);

		StringBuilder test = new StringBuilder("(test,test,)");
		test.replace(test.length()-2, test.length(), ")");
		System.out.println(test.toString());
	}
}