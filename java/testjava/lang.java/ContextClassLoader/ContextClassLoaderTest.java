// import java.lang.reflect.*;
// import java.util.*;
import A.A;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.MalformedURLException;
class ContextClassLoaderTest{
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException,InstantiationException,IllegalAccessException
	{
		/*初始化类加载器，父加载器空*/
		ClassLoader boot_cl = Object.class.getClassLoader();
		ClassLoader app_cl = ContextClassLoaderTest.class.getClassLoader();

		Thread current_thread = Thread.currentThread();
		ClassLoader oldcl = current_thread.getContextClassLoader();
		System.out.println("当前线程上下文类加载器为: " + oldcl);
		
		MyClassLoader mcl = new MyClassLoader(new URL[]{new URL("file:/Users/saixiaomin/ws/step/java/testjava/lang.java/ContextClassLoader/")}, null);
		System.out.println("自定义线程上下文类加载器为: " + mcl);
		
		/*设置线程上下文类加载器*/
		current_thread.setContextClassLoader(mcl);

		ClassLoader newcl = current_thread.getContextClassLoader();
		System.out.println("设置后，线程上下文类加载器为: " + newcl);

		/*java.lang.SecurityException: Prohibited package name: java.lang*/
		// Class c = newcl.loadClass("java.lang.Object1");

		A a = new A();
		System.out.println(A.class.getClassLoader());
		
		// Class c = newcl.loadClass("A.A");
		// Object o = c.newInstance();
		// System.out.println(c.getClassLoader());

		// System.out.println(o1 instanceof Object);
	}

	static class MyClassLoader extends URLClassLoader{
		public MyClassLoader(URL[] urls, ClassLoader parent) {
			super(urls, parent);
		}

		public Class<?> findClass(String name)
		{
			Class ret = findLoadedClass(name);
			try{
				if (ret == null) {
					ret = super.findClass(name);
				}
			}catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			return ret;
		}
	}
}