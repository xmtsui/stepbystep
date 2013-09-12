/**
 * 测试ClassLoader.load()
 * 与
 * Class.forName区别
 *    第一，ClassLoader.loadClass可以显式指定装载class的ClassLoader，
 *    但是Class.forName就不行了，他会默认使用调用类的ClassLoader来装载class。
 *    
 *    第二，ClassLoader.loadClass仅仅加载class进来，但是不会初始化类，
 *    而Class.forName不仅会加载class而且还会初始化类。我们可以做个试验。
 */
public class TestClassLoader {
  public static void main(String [] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
    // ClassLoader cl = TestClassLoader.class.getClassLoader();
    ClassLoader cl = TestClassLoader.class.getClassLoader();
    // Class c = Class.forName("TestClassLoader$UnderTestClass1");
    // Class c = Class.forName("TestClassLoader$UnderTestClass1", true, cl);
    Class c = Class.forName("TestClassLoader$UnderTestClass1", false, cl);//是否初始化
    TestClassLoader.class.getClassLoader().loadClass("TestClassLoader$UnderTestClass2").newInstance();

  }
  public static class UnderTestClass1{
    public static String testStr;
    static {
      testStr = "test1";
      System. out.println(testStr);
    }
  }

  public static class UnderTestClass2{
    public static String testStr;

    static {
      testStr = "test2" ;
      System. out.println(testStr);
    }
  }
}