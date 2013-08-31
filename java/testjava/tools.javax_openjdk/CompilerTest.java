import static java.lang.System.*;
// import com.sun.tools.javac.main.JavaCompiler;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
class CompilerTest{
	public static void main(String[] args)
	{
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		out.println(compiler);
		int result = compiler.run(null, System.out, System.err, "-sourcepath", "src", "test.java");
	}
}