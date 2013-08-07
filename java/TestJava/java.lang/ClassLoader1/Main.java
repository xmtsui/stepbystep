import java.net.URL;

public class Main {
	public static void main(String[] args) throws Exception {
		MyMgrClassLoader mgr = new MyMgrClassLoader(ClassLoader.getSystemClassLoader());
		//注意最后的斜杠
		MyClassLoader my1 = new MyClassLoader(new URL[] { new URL("file:/Users/saixiaomin/Documents/git_workspace/stepbystep/java/TestJava/java.lang/ClassLoader1/A/") }, mgr);
		MyClassLoader my2 = new MyClassLoader(new URL[] { new URL("file:/Users/saixiaomin/Documents/git_workspace/stepbystep/java/TestJava/java.lang/ClassLoader1/B/") }, mgr);
		mgr.setA(my1);
		mgr.setB(my2);
		Class c1 = my1.loadClass("A");
		Object o1 = c1.newInstance();
		Class c2 = my2.loadClass("B");
		Object o2 = c2.newInstance();
		System.out.println(o1);
		System.out.println(o2);

	}
}