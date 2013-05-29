class NonStaticInner{
	private static int t;//如果在内部类使用了，则会在Outerr.class生成静态访问方法
	transient Inner1 inner;
	NonStaticInner(){
		inner = new Inner1();
	}

	//编译后不会出现Outerr$1.class,
	//外部调用时需要有Outerr实例
	class Inner{
		void say()
		{
			System.out.println("hello"+t);
			System.out.println(NonStaticInner.this.t);
		}
	}

	//如果外部类使用了private类，(使用是指堆上分配了内存，仅仅声明引用不算)
	//编译后会出现Outerr$1.class(对应java语法糖，条件编译)
	//使用Outerr$Inner1(Outerr, Outerr$1);包可见构造器来构造新的类
	//如果外部类没有调用private类,则编译后的Outerr$Inner1没有构造函数
	//因为不必要
	private class Inner1{
		void say()
		{
			System.out.println("hello"+t);
		}
	}
}