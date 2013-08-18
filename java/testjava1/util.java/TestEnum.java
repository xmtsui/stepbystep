import java.util.Scanner;
class TestEnum{
	/*每一个成员都是一个实例
	public static final TestEnum$Size SAMLL;
	比较两个枚举类型的值时，永远不需要调equals
	==就可以
	*/
	enum Size {SAMLL, MEDIUM, LARGE, EXTRA_LARGE};

	enum Size_1 {
		SMALL("S"),MEDIUM("M"),LARGE("L"),EXTRA_LARGE("E");
		private Size_1(String abbreviation){
			this.abbreviation = abbreviation;
		}
		public String getAbbreviation(){
			return abbreviation;
		}

		private String abbreviation;
	}
	public static void main(String[] args)
	{
		Size s = Size.MEDIUM;
		System.out.println(s);
		System.out.println(s.ordinal());

		/*toString, valueOf互为逆操作*/
		System.out.println(Size.MEDIUM.toString());
		Size s_ = (Size) Enum.valueOf(Size.class, "SAMLL");
		System.out.println(s_); 

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a size: (SMALL, MEDIUM, LARGE, EXTRA_LARGE)");
		String input = sc.next().toUpperCase();
		Size_1 s_1 = Enum.valueOf(Size_1.class, input);
		System.out.println("size=" + s_1);
		System.out.println("abbreviation=" + s_1.getAbbreviation());
		if(s_1 == Size_1.EXTRA_LARGE)
			System.out.println("Good!");
	}
}