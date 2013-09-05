class GenericWithWildcard_Test{
	public static void main(String[] args)
	{
		Pair<Employee> pe = new Pair<Employee>();
		Pair<Manager> pm = new Pair<Manager>();

		// pe = pm;
		pe.setFirst(new Manager());
		System.out.println(pe.getFirst());
	}

	static class Employee{

	}
	
	static class Manager extends Employee{

	}

	static class Pair<? extends Employee> {
		? t1;
		? t2;
		Pair(){}

		Pair(? extends Employee t1, ? extends Employee t2)
		{
			this.t1 = t1;
			this.t2 = t2;
		}

		? extends Employee getFirst()
		{
			return t1;
		}

		? extends Employee getSecond()
		{
			return t2;
		}

		void setFirst(? extends Employee t1)
		{
			this.t1 = t1;
		}

		void setSecond(? extends Employee t2)
		{
			this.t2 = t2;
		}
	}	
}