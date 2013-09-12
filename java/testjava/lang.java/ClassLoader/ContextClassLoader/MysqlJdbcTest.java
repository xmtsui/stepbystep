/**
 * 测试Mysql jdbc DriverManager
 * j -cp .:mysql-connector-java-5.1.6.jar MysqlJdbcTest
 * 对比
 * j MysqlJdbcTest
 *
 * @author xmtsui
 * @version v1.0
 */
import java.net.URL;
import java.net.URLClassLoader;
import java.net.MalformedURLException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Enumeration;
class MysqlJdbcTest{
	public static void main(String[] args)
	{
		Connection conn = null;
		Statement stmt = null;
		try{
			Thread current_thread = Thread.currentThread();
			ClassLoader oldcl = current_thread.getContextClassLoader();
			System.out.println("当前线程上下文类加载器为: " + oldcl);
			MyClassLoader mcl = new MyClassLoader(new URL[]{new URL("file:/Users/saixiaomin/ws/step/java/testjava/lang.java/ContextClassLoader/A/mysql-connector-java-5.1.6.jar")}, null);
			// MyClassLoader mcl = new MyClassLoader(new URL[]{new URL("file:/Users/saixiaomin/ws/step/java/testjava/lang.java/ContextClassLoader/A/mysql-connector-java-5.1.6.jar")}, oldcl);
			current_thread.setContextClassLoader(mcl);
			ClassLoader newcl = current_thread.getContextClassLoader();
			System.out.println("设置后，线程上下文类加载器为: " + newcl);

			//Class.forName("com.mysql.jdbc.Driver", true, mcl);
			// Class.forName("com.mysql.jdbc.Driver");
			Class driver = mcl.loadClass("com.mysql.jdbc.Driver");
			System.out.println("jdbc driver cl: " + driver.getClassLoader());
			System.out.println("jdbc driver cl parent: " + driver.getClassLoader().getParent());
			System.out.println("DriverManager cl: " + DriverManager.class.getClassLoader());

			current_thread.setContextClassLoader(oldcl);
			
			java.util.Enumeration<Driver> test = DriverManager.getDrivers();
			while(test.hasMoreElements())
			{
				Driver tt = test.nextElement();
				System.out.println(tt);
				System.out.println(tt.getClass().getClassLoader());
			}
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crashcourse", "root", "123");
			stmt = conn.createStatement();

			ResultSet rset = stmt.executeQuery("select * from customers");
			while(rset.next())
			{
				System.out.println(rset.getString("cust_name"));
			}
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}catch(MalformedURLException e)
		{
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				conn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
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