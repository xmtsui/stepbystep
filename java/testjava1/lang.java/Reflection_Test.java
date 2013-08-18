import java.util.Date;
class Reflection_Test{
	public static void main(String[] args)
	{
		/*第一种获得Class类对象的方法：Object的getClass()方法，获得一个Class类型的实例*/
		System.out.println("CASE 1 : ");
		Employee e = new Employee("tsui");
		Manager m = new Manager("wendy");
		System.out.println(e.getClass() + " " + e.getName());
		/*getName()返回类的名字*/
		System.out.println(m.getClass().getName() + " " + m.getName());
		
		/*包名也作为类名的一部分*/
		System.out.println("\nCASE 2 : ");
		Date d = new Date();
		Class cl = d.getClass();
		String name = cl.getName();
		System.out.println(name);
		
		/*第二种方法：静态方法forName()获得类名对应的class对象*/
		System.out.println("\nCASE 3 : ");
		String className = "java.util.Date"
		// String className = "Date";//此处类名不全，报错。若一定要使用，需要新建立一个Date类
		try{
			Class cl1 = Class.forName(className);
			System.out.println(cl1); 
		}catch (ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}

		/*第三种方法, T任意类型，T.class将代表匹配的类对象
		注意这个类型不一定是类，如int
		int
		class [Ljava.lang.Double; [表示数组*/
		System.out.println("\nCASE 4 : ");
		Class cl2 = Date.class;
		Class cl3 = int.class;
		Class cl4 = Double[].class;
		System.out.println(cl2);
		System.out.println(cl3);
		System.out.println(cl4);

		/*虚拟机为每个类型管理一个Class对象，用＝＝可以比较两个类对象*/
		System.out.println("\nCASE 5 : ");
		if(d.getClass() == Date.class)
		{
			System.out.println("YES");
		}

		/*newInstance()，快速创建一个类的实例，必须有默认的构造器，
		否则抛出异常InstantiationException*/
		System.out.println("\nCASE 6 : ");
		try{
			String className1 = "TestReflection$Employee";
			Object o = Class.forName(className1).newInstance();
			String className2 = "TestReflection$Manager";
			String margs = "tsui";
			Object o1 = Class.forName(className2).newInstance(margs);
		}catch (ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}catch (InstantiationException e1)
		{//没有默认构造方法时抛出
			e1.printStackTrace();
		}catch (IllegalAccessException e1)
		{//构造方法访问权限不够时抛出
			e1.printStackTrace();
		}

	}

	private static class Staff{
		protected String name;
		Staff(){}
		Staff(String name)
		{
			this.name = name;
		}
		protected String getName()
		{
			return this.name;
		}
	}

	private static class Employee extends Staff{
		Employee(){}
		// private Employee(){}

		Employee(String name)
		{
			super(name);
		}
	}

	private static class Manager extends Staff{
		Manager(String name)
		{
			super(name);
		}
	}
}