/**
 * <- 左边定义一个val, 右边是一个生成器表达式generator expression
 * 注解：val定义的变量不可变， var定义的变量可变。
 * 这里不是指变量所引用的实例，而是变量本身不能改变
 * 如val buffer = new StringBuffer()
 * 就不能把buffer指向其他的引用，
 * 但我们可以使用append之类的方法来修改StringBuffer实例
 *
 * 注意 val str = "hello" 定义了一个String实例，就不能再修改了，因为String本身也是不可变的
 * 所以
 * 要想一个类的实例不可变，可以把所有字段都定义为val，然后只提供读取实例状态的方法，不提供修改的方法
 * 在scala里，尽量优先使用val，而非var,这可以提升不变性和函数式风格
 *
 * 以上这些同java, java primitive, String 不可变（因为本身就不可变，引用也不可变）
 *  Object 引用不可变
 * @author xmtsui
 * @version v1.0
 */
for(i <- 1 to 3){
	print(i + ",")
}
println("Scala rocks!!!")

//如果方法有0或1个参数，点和括号是可以丢掉的
//如果方法参数多于一个，就必须使用括号，但点是可选的
//a+b a.+(b)
//1 to 3 1.to(3)
// def turn(direction: String)
// car turn "right"

for(i <- 1.until(3)){
	print(i + ".")
}
println("Scala rocks!!!")

//Range类的foreach()方法，以一个函数值作为参数，
//＝》将左边的参数列表和右边的实现分离开来
(1 to 3).foreach(i => print(i + ","))
println("Scala rocks!!!")

//java 中有primitive, 也有object
//scala中全都是object
//java中自动装箱可以实现为对象方法传递基本类型，但不支持在基本类型上调用方法
//如：2.toString()

class ScalaInt{
	def playWithInt(){
		// val capacity = 10
		val capacity : Int = 10
		val list = new java.util.ArrayList[String]
		list.ensureCapacity(capacity)
	}
}

def getPersonInfo(primaryKey : Int) = {
	("tsui", "uestc", "tsui.uestc@gmail.com")
}

val (name, school, email) = getPersonInfo(1)
println("name : " + name)
println("school : " + school)
println("email : " + email)

val info = getPersonInfo(1)
println(info._2)

val str = """

	|你好，我是"tsui",来自电子科技大学

"""
println(str)
println(str.reverse)
println(str.stripMargin)//|对管道符起作用
