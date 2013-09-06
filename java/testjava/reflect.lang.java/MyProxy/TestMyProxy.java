import java.lang.reflect.*;
import java.util.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.MalformedURLException;

/**
 * This program demonstrates the use of proxies.
 * Java代理的对象和被代理有相同的接口
 * @author xmtsui
 * @version 1.0
 */
public class TestMyProxy
{
   public static void main(String[] args)
   {
      //声明一个MyString对象
      MyString s = new MyString("hello");
      //声明一个InvocationHandler，指定代理对象s
      InvocationHandler handler = new TraceHandler(s);
      //查看各个类的类加载器
      System.out.println("Object \t\tcl: "+Object.class.getClassLoader());
      System.out.println("int \t\tcl: "+int.class.getClassLoader());
      System.out.println("Comparable \tcl: "+Comparable.class.getClassLoader());
      System.out.println("TraceHandler \tcl: "+TraceHandler.class.getClassLoader());
      System.out.println("TestMyProxy \tcl: "+TestMyProxy.class.getClassLoader());
      System.out.println("MyProxy \tcl: "+MyProxy.class.getClassLoader());
      System.out.println("MyClassLoader \tcl: "+MyClassLoader.class.getClassLoader());

      /*设置是否保存动态代理生成的类，注意要手动建立com/sun/proxy 目录才行*/
      System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
      System.out.println("=============Start============");

      try{
         ClassLoader boot_cl = Object.class.getClassLoader();
         ClassLoader app_cl = TraceHandler.class.getClassLoader();
         /*初始化类加载器，父加载器空*/
         MyClassLoader my_cl = new MyClassLoader(new URL[]{new URL("file:/Users/saixiaomin/ws/step/java/testjava/reflect.lang.java/MyProxy/")}, null);
         /*初始化类加载器，父加载器为系统类加载器*/
         // MyClassLoader my_cl = new MyClassLoader(new URL[]{new URL("file:/Users/saixiaomin/ws/step/java/testjava/reflect.lang.java")}, app_cl);
         
         /*声明一个Class对象引用MyProxy_ClassRef*/
         Class MyProxy_ClassRef = my_cl.loadClass("MyProxy");
         System.out.println("MyProxy_ClassRef == MyProxy.class: " + (MyProxy.class == MyProxy_ClassRef));
         System.out.println("MyProxy_ClassRef cl: " + MyProxy_ClassRef.getClassLoader());
         System.out.println("MyProxy cl: " + MyProxy.class.getClassLoader());
         
         /*构造Class接口数组*/
         // Class[] classes = {MyProxy_ClassRef};
         // Class[] classes = {MyProxy.class};
         Class[] classes = {Comparable.class, MyProxy.class};
         
         // Object proxy = Proxy.newProxyInstance(app_cl, classes, handler);

         /*Comparable由初始类加载器加载*/
         Object proxy = Proxy.newProxyInstance(app_cl, classes, handler);
         
         System.out.println("\n========interface 1===========");
         MyProxy myproxy_interface_ref = null;
         if(proxy instanceof MyProxy)
         {
            myproxy_interface_ref = (MyProxy)proxy;
            myproxy_interface_ref.say();
         }

         if(proxy instanceof Object)
            System.out.println(proxy.equals("hello"));
         
         System.out.println("\n========interface 2===========");
         Comparable<String> comparable_interface_ref = null;
         /*instanceof 的泛型类型不合法*/
         // if(comparable_interface_ref instanceof Comparable<String>)
         if(proxy instanceof Comparable)
         {
            comparable_interface_ref = (Comparable<String>)proxy;
            System.out.println(comparable_interface_ref.compareTo("helln"));
            System.out.println(comparable_interface_ref.compareTo("hello"));
            System.out.println(comparable_interface_ref.compareTo("hellp"));
         }

         /*测试isProxyClass*/
         System.out.println("Proxy.isProxyClass: " + Proxy.isProxyClass(proxy.getClass()));
      }catch(MalformedURLException e)
      {
         e.printStackTrace();
      }catch (Exception e)
      {}
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
               //loadClass找不到之后会调用此方法findClass，此处会循环,栈溢出
               //所以也有代理机制
               // ret = super.loadClass(name);
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

class TraceHandler implements InvocationHandler
{
   /**
    * Constructs a TraceHandler
    * @param t the implicit parameter of the method call
    */
   public TraceHandler(Object t)
   {
      target = t;
   }

   public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
   {
      // print implicit argument
      System.out.println("---------invoke start---------");
      System.out.print(target);
      // print method name
      System.out.print("." + m.getName() + "(");
      // print explicit arguments
         if (args != null)
         {
            for (int i = 0; i < args.length; i++)
            {
            // System.out.println(args.length+" ");
               System.out.print(args[i]);
               if (i < args.length - 1) System.out.print(", ");
            }
         }
         System.out.println(")");

         // invoke actual method
         return m.invoke(target, args);
      }

      private Object target;
   }
