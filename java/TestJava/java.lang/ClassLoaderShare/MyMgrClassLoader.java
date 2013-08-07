public class MyMgrClassLoader extends ClassLoader {
	private MyClassLoader a;

	private MyClassLoader b;

	public MyMgrClassLoader(ClassLoader parent) {
		super(parent);

	}

	@Override
	protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		Class ret = null;
		try {
			ret = super.loadClass(name, resolve);
		} catch (Exception e) {
			ret = a.findClass(name);
		}
		return ret;
	}

	public MyClassLoader getA() {
		return a;
	}

	public void setA(MyClassLoader a) {
		this.a = a;
	}

	public MyClassLoader getB() {
		return b;
	}

	public void setB(MyClassLoader b) {
		this.b = b;
	}
}