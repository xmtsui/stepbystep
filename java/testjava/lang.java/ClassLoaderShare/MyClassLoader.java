import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoader extends URLClassLoader {
	private static int i = 0;
	public MyClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		System.out.println("加载类["+name+"]ing....");
		i++;
		Class ret = findLoadedClass(name);
		if (ret == null) {
			ret = super.findClass(name);
			System.out.println("尚没有加载，现在加载\t: " + i + " [" + ret + "] 当前cl是：" + ret.getClassLoader());
		}
		else
		{
			System.out.println("已经加载，使用加载过的类: " + i + " [" + ret + "] 当前cl是：" + ret.getClassLoader());
		}
		System.out.println("加载结束....");
		return ret;
	}
}
