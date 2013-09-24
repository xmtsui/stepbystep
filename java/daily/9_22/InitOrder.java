class InitOrder{
	public static int k =0 ;
	public static InitOrder t1 = new InitOrder("t1") ;
	public static InitOrder t2 = new InitOrder("t2") ;
	public static int i= print("i") ;
	public static int n=99 ;
	public int j=print("j") ;

	{
		print("构造块") ;
	}

	static {
		print("静态块") ;
	}

	public InitOrder(String str) {
		System.out.println((++k)+":"+str+"    i="+i+"   n="+n);
		++i; ++n;
	}

	public static int print(String str) {
		System.out.println((++k)+":"+str+"    i="+i+"   n="+n);
		++n;
		return ++i ;
	}
 /**
  * @param args
  */
 public static void main(String[] args) {
  // TODO Auto-generated method stub
 	InitOrder t = new InitOrder("init") ;
 }
}

