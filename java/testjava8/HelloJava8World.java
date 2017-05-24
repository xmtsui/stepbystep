class HelloJava8World{
	// (int x, int y) -> x + y
	static Runnable s = () -> System.out.println("HelloJava8World");
	public static void main(String[] args){
		s.run();
	}
}