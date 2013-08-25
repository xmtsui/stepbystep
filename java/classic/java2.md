1. #interface
		interface不能多继承
		interface interface1 extends interface2, interface3{//报错
		void undo(int i);
		void process();
		}
		
		interface只能有常量，必须赋予初值
		interface interface1{
			String aa;//报错
			String bb="0";
		}
		
		interface的修饰符只能是public或默认
		方法自动变成public abstract
		字段自动变成public static final
		interface interface1{
			private void undo(int i);//报错
		}