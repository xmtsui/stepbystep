import java.util.*;
import java.lang.reflect.*;
import java.lang.Class;
import java.lang.reflect.Modifier;

/**
 * Reflect basic
 * @author xmtsui
 * @version 1.0 2013-09-02
 */
public class ReflectBasic{
   public static void main(String[] args){
      System.out.println(String.class);
      System.out.println(String.class.getName());
      System.out.println(int.class);
      System.out.println(int.class.getName());
      // System.out.println(Class<String>);
      
      System.out.println("============");

      try{
         Class cl = Class.forName("ReflectBasic$B");
         System.out.println(cl.getName());
         Class supercl = cl.getSuperclass();
         System.out.println(supercl.getName());
         String modifiers = Modifier.toString(cl.getModifiers());
         System.out.println(modifiers);
         modifiers = Modifier.toString(supercl.getModifiers());
         System.out.println(modifiers);

         System.out.println("============");
         Constructor[] constructors = cl.getDeclaredConstructors();
         for (Constructor c : constructors)
         {
            String name = c.getName();
            System.out.println(name);
            
            modifiers = Modifier.toString(c.getModifiers());
            System.out.println(modifiers);
            
            Class[] paramTypes = c.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++)
            {
               System.out.println(paramTypes[j].getName());
            }
         }

         System.out.println("============");
         Method[] methods = cl.getDeclaredMethods();
         // Method[] methods = cl.getMethods();//所有父类方法

         for (Method m : methods)
         {
            Object[] o = new Integer[1];
            // Object[] o = new String[1];//Runtime: java.lang.IllegalArgumentException
            // o[0] = "1";
            o[0] = 1;
            // B b = (B)cl.newInstance();//InstantiationException
            // m.invoke(b, o);//IllegalAccessException,InvocationTargetException

            //method name
            String name = m.getName();
            System.out.println("method name: " + name);

            //return type
            Class retType = m.getReturnType();
            System.out.println("retType: " + retType.getName());

            // print modifiers
            modifiers = Modifier.toString(m.getModifiers());
            System.out.println("modifiers: " + modifiers);

            // print parameter types
            Class[] paramTypes = m.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++)
            {
               System.out.print("parameter types: " + paramTypes[j].getName());
            }
            System.out.print("\n");
         }

      }catch (ClassNotFoundException e)
      {
         e.printStackTrace();
      }catch (IllegalAccessException e)
      {
         e.printStackTrace();
      }catch (InvocationTargetException e)
      {
         e.printStackTrace();
      }catch (InstantiationException e)
      {
         e.printStackTrace();
      }
   }

   private static class A{
      public void say(int x)
      {
         System.out.println("hello world A!"+x);
      }
   }

   private final static class B extends A{
      public B(int i){}
      public B(){}
      public final void say(int x)
      {
         System.out.println("hello world B!"+x);
      }
   }
}