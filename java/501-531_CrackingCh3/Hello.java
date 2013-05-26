/**
 * 先编译好库
 * 再跨包编译
 * javac -cp [directory/jar/zip] xx.java
 * java -cp .:[directory/jar/zip] xx
 */
import com.tsui.util.HelloUtil;
class Hello{
	public static void main(String[] args)
	{
		HelloUtil hu = new HelloUtil();
		hu.sayHello();
	}
}