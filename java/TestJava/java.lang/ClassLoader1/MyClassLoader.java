import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoader extends URLClassLoader {

	public MyClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);

	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class ret = findLoadedClass(name);
		if (ret == null) {
			ret = super.findClass(name);
		}
		return ret;
	}
}
