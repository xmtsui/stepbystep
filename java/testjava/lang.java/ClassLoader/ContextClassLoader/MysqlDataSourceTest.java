// import java.
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Hashtable;
import java.util.Enumeration;
class MysqlDataSourceTest{
	public static void main(String[] args)
	{
		try{
			System.setProperty("java.naming.factory.initial", "org.apache.naming.java.javaURLContextFactory");
			System.out.println (System.getProperty("java.naming.factory.initial"));
			Hashtable ht = new InitialContext().getEnvironment();
			// System.out.println (System.getProperty());
			Enumeration elements = ht.elements();
			while(elements.hasMoreElements())
			{
				elements.nextElement();
			}

			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MysqlDb");
		}catch (NamingException e)
		{
			e.printStackTrace();
		}
	}
}