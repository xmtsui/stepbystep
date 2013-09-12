/**
 * Object o = 0 自动转型为Integer
 */
class IntAutoWrap {
	public static void main(String[] args)
	{
		Object o = 0;
		String s = "0";
		Integer i = 0;
		System.out.println("o instanceof Integer: " + (o instanceof Integer));
		System.out.println("o instanceof String: " + (o instanceof String));
		System.out.println("o instanceof Object: " + (o instanceof Object));

		//false
		//相当于一个String.equals(Integer)
		if (s.equals(0)) {
			System.out.println("yes");
		}
		else {
			System.out.println("no");
		}

		//false
		if (s.equals('0')) {
			System.out.println("yes");
		}
		else {
			System.out.println("no");
		}

		//true
		if (s.equals("0")) {
			System.out.println("yes");
		}
		else {
			System.out.println("no");
		}

		//true
		if (i.equals(0)) {
			System.out.println("yes");
		}
		else {
			System.out.println("no");
		}
	}
}