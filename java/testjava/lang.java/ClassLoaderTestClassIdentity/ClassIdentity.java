/**
 * 测试classloader
 * 判断类对象相等
 * instanceof(或者isInstance())的行为
 *
 * 关键点：
 * 如果class与本类放在同一个目录下，使用的都是系统类加载器
 * 放在不同的目录下才能使用各自的类加载器
 *
 * 分析原因：在当前目录的时候由系统类加载器在当前目录下查找，
 * 若找不到，才使用自定义类加载器到指定的目录查找。
 * 
 * @author xmtsui
 * @version v1.0
 */
import java.lang.reflect.Method;
public class ClassIdentity {

	public static void main(String[] args) {
		new ClassIdentity().testClassIdentity();
	}
	
	public void testClassIdentity() {
		System.out.println("--现在查看原始的classloader--");
		
		//若把class放在本类运行的目录下，则根据向上代理的原则，类只会被系统类加载器加载
		//因为系统类加载器根据classpath来查找类，classpath包括了当前目录, .号表示
		//若类定义在其他目录，则系统类加载器找不到，就只能是用我们自己定义的cl去加载，
		//这样就能使用不同的类加载器加载同一个类。
		//有一点要注意的：eclipse中运行无法看到实验结果，估计跟eclipse的类加载机制有关
		String classDataRootPath = "/Users/saixiaomin/ws/step/" +
		"java/testjava/lang.java/ClassLoaderTestClassIdentity/bin";
		FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
		System.out.println("initial fscl1: " + fscl1);
		FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
		System.out.println("initial fscl2: " + fscl2);

		System.out.println("--现在查看fscl1的cl栈--");
		ClassLoader loader = fscl1;
		while (loader != null) {
			System.out.println(loader.toString());
			loader = loader.getParent();
		}

		System.out.println("--现在查看fscl2的cl栈--");
		loader = fscl2;
		while (loader != null) {
			System.out.println(loader.toString());
			loader = loader.getParent();
		}

		System.out.println("--现在查看fscl1与fscl2对比--");
		try {
			String className = "Sample";
			//保存当前线程cl
			ClassLoader clold = Thread.currentThread().getContextClassLoader();
			System.out.println("intial old thread context cl:\t " + clold + "\n");
			
			//设置当前线程cl为fscl1
			Thread.currentThread().setContextClassLoader(fscl1);
			ClassLoader clnew = Thread.currentThread().getContextClassLoader();
			System.out.println("thread context cl after set fscl1:\t " + clnew);

			//用fscl1加载类定义
			Class<?> class1 = fscl1.loadClass(className);
			
			//生成fscl1加载的Sample类对象
			Object obj1 = class1.newInstance();
			System.out.println("now obj1 is :\t\t\t\t " + obj1);
			System.out.println("now class1 cl is :\t\t\t " + class1.getClassLoader());
			
			//设置当前线程cl为fscl2
			Thread.currentThread().setContextClassLoader(fscl2);
			clnew = Thread.currentThread().getContextClassLoader();
			System.out.println("thread context cl after set fscl2:\t " + clnew);
			
			//用fscl2加载类定义
			Class<?> class2 = fscl2.loadClass(className);
			
			//生成fscl2加载的Sample类对象
			Object obj2 = class2.newInstance();
			System.out.println("now obj2 is :\t\t\t\t " + obj2);
			System.out.println("now class2 cl is :\t\t\t " + class2.getClassLoader());
			
			//恢复当前线程cl
			Thread.currentThread().setContextClassLoader(clold);
			clnew = Thread.currentThread().getContextClassLoader();
			System.out.println("\nback to old thread cl:\t\t\t " + clnew);
			Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
			// 抛出ClassCastException，不同类加载器加载的类生成的对象，不能强转
			// setSampleMethod.invoke(obj1, obj2);
			
			System.out.println("--现在测试instanceof: instanceof --");
			Method testInstanceOfMethod = class1.getMethod("testInstanceOf", java.lang.Object.class);
			Method testInstanceOfMethod1 = class2.getMethod("testInstanceOf", java.lang.Object.class);
			System.out.println(testInstanceOfMethod.invoke(obj1, obj1));
			System.out.println(testInstanceOfMethod.invoke(obj1, obj2));
			System.out.println(testInstanceOfMethod1.invoke(obj2, obj1));
			System.out.println(testInstanceOfMethod1.invoke(obj2, obj2));
			
			System.out.println("--现在测试instanceof: isInstanceof--");
			System.out.println(class1.isInstance(obj1));
			System.out.println(class1.isInstance(obj2));
			System.out.println(class2.isInstance(obj1));
			System.out.println(class2.isInstance(obj2));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
