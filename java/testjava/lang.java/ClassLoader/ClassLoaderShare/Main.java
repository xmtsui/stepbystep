/**
 * 由osgi想到的如何让不同classloader加载的class能互相看见
 * 
 * @author http://hi.baidu.com/manfromearth/item/960df20fc2f0766dd45a11a7
 * @version v1.0
 */
import java.net.URL;
public class Main {
	public static void main(String[] args) throws Exception {
		//注意最后的斜杠
		String urlA = "file:/Users/saixiaomin/Documents/git_workspace/"+
		"stepbystep/java/testjava/lang.java/ClassLoaderShare/A/";
		String urlB = "file:/Users/saixiaomin/Documents/git_workspace/"+
		"stepbystep/java/testjava/lang.java/ClassLoaderShare/B/";
		
		/*case 1*/
		ClassLoader syscl = ClassLoader.getSystemClassLoader();
		//声明两个mycl去分别加载统一个类A,mycl的父类设置为系统类加载器
		MyClassLoader my1 = new MyClassLoader(new URL[] { new URL(urlA) }, syscl);
		MyClassLoader my2 = new MyClassLoader(new URL[] { new URL(urlA) }, syscl);
		System.out.println("--设置父cl为系统自定义的mgr, 现在查看my1 cl栈--");
		ClassLoader loader = my1;
		while (loader != null) {
			System.out.println(loader.toString());
			loader = loader.getParent();
		}
		
		System.out.println("\n--分别加载A--");
		Class c1 = my1.loadClass("A");
		Object o1 = c1.newInstance();
		Class c2 = my2.loadClass("A");
		Object o2 = c2.newInstance();

		System.out.println("o1 instanceof c1A:" + c1.isInstance(o1));
		System.out.println("o1 instanceof c2A:" + c2.isInstance(o1));
		System.out.println("o2 instanceof c1A:" + c1.isInstance(o2));
		System.out.println("o2 instanceof c2A:" + c2.isInstance(o2));
		
		//处理不用的引用
		loader=null; my1=null; my2=null; c1=null; o1=null; c2=null; o2=null;

		/*case 2*/
		//自定义的mgr，类似OSGi中的公共父加载器
		MyMgrClassLoader mgr = new MyMgrClassLoader(ClassLoader.getSystemClassLoader());
		//声明两个mycl去分别加载统一个类A,mycl的父类设置为系统类加载器
		//此处两个mycl分别只能看到自己url对应的类，不能看到对方的
		//如用my1去加载类B就会报错
		my1 = new MyClassLoader(new URL[] { new URL(urlA) }, mgr);
		my2 = new MyClassLoader(new URL[] { new URL(urlB) }, mgr);
		mgr.setA(my1);
		mgr.setB(my2);

		System.out.println("\n--设置父cl为系统自定义的mgr,现在查看my1 cl栈--");
		loader = my1;
		while (loader != null) {
			System.out.println(loader.toString());
			loader = loader.getParent();
		}
		System.out.println("\n--设置父cl为系统自定义的mgr,现在查看my2 cl栈--");
		loader = my2;
		while (loader != null) {
			System.out.println(loader.toString());
			loader = loader.getParent();
		}
		
		c1 = my1.loadClass("A");
		//c1 = my1.loadClass("B");//报错java.lang.ClassNotFoundException
		o1 = c1.newInstance();
		
		/*c2中找不到类A的定义，则代理给父类mgr去找，mgr也找不到，抛出异常
		异常处理中委托a的cl去找MyMgrClassLoader的17行代码是关键
		c2加载A正常加载*/
		// c2 = my2.loadClass("A");
		
		//B引用A, 正常加载，实现不同cl间共用已经加载的类的要求
		//大致思路就是：cl1,cl2都有一个公共的父cl，
		//当cl1已经加载了类A,cl2加载类A的时候，找不到A,
		//则向上代理给父cl去加载,父cl也找不到，则在异常处理的时候指定cl1去加载。
		//在父cl中要有cl1,cl2的引用
		c2 = my2.loadClass("B");
		o2 = c2.newInstance();

		/*如果用c2加载A,则之前的检测全为true
		/*因为我们改了c2的加载规则：c1,c2加载A用的都是c1*/
		// System.out.println("o1 instanceof c1A:" + c1.isInstance(o1));
		// System.out.println("o1 instanceof c2A:" + c2.isInstance(o1));
		// System.out.println("o2 instanceof c1A:" + c1.isInstance(o2));
		// System.out.println("o2 instanceof c2A:" + c2.isInstance(o2));
	}
}