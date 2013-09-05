import java.util.RandomAccess;
class TestGenericExtends{
	public static void main(String[] args)
	{
		//数组的ArrayStoreException异常
		Object[] o = new String[10];
		// o[0] = new Integer("1");//java.lang.ArrayStoreException
		System.out.println(o[0]);

		//类型参数是父子关系，但范型类型没有继承关系
		Pair<Employee> pe = new Pair<Employee>();
		Pair<Manager> pm = new Pair<Manager>();
		// pe = pm;//不能互相赋值，不是子类型

		pe.setFirst(new Manager());
		System.out.println(pe.getFirst());

		//测试范型数组的不安全性
		Employee[] ems = new Employee[10];
		Pair<Employee> pe1 = new Pair<Employee>(ems);

		Pair2<Employee> pair2 = new Pair2<Employee>();
	}

	static class Employee implements RandomAccess{

	}
	
	static class Manager extends Employee implements RandomAccess{

	}

	static class Pair2<T extends Employee & RandomAccess>{
		T t1;
		T t2;
		Pair2(){}

		Pair2(T t1, T t2)
		{
			this.t1 = t1;
			this.t2 = t2;
		}

		T getFirst()
		{
			return t1;
		}

		T getSecond()
		{
			return t2;
		}

		void setFirst(T t1)
		{
			this.t1 = t1;
		}

		void setSecond(T t2)
		{
			this.t2 = t2;
		}
	}
	
	static class Pair<z> {
		z t1;
		z t2;
		// List<z> tt;
		z[] tt;
		Pair(){}

		Pair(z[] tt){
			Object o[] = tt;
			o[0] = new String("haha");//编译通过，但运行报错
			System.out.println(o[0]);
		}

		Pair(z t1, z t2)
		{
			this.t1 = t1;
			this.t2 = t2;
		}

		z getFirst()
		{
			return t1;
		}

		z getSecond()
		{
			return t2;
		}

		void setFirst(z t1)
		{
			this.t1 = t1;
		}

		void setSecond(z t2)
		{
			this.t2 = t2;
		}
	}
}