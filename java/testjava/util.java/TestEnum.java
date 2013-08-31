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

		String SYSPROP_BING3_API = "bing3.key";
		String appid = System.getProperty(SYSPROP_BING3_API);
		System.out.println(appid);
	}

	/**
	 * The data to include in the response.
	 */
	public enum RequestType
	{
		/** Page, documents and clusters */
		FULL(true),

		/** Only page */
		PAGE(false),

		/** Only documents */
		DOCUMENTS(true),

		/** Only clusters */
		CLUSTERS(true),

		/** Documents and clusters in Carrot2 standard format */
		CARROT2(true),

		/** Documents in Carrot2 standard format */
		CARROT2DOCUMENTS(true),

		/** Error page */
		ERROR(false),

		/** Information about document sources */
		SOURCES(false),

		/** Information about attributes of a document source */
		ATTRIBUTES(false),

		/** Simple statistics page */
		STATS(false);

		/** True when Carrot2 processing is required */
		public final boolean requiresProcessing;

		private RequestType(boolean requiresProcessing)
		{
			this.requiresProcessing = requiresProcessing;
		}
	}

}