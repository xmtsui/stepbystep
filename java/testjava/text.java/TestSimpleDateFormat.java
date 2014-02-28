import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
class TestSimpleDateFormat{
	public static void main(String[] args)
	{
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String date = format.format(new Date());
		System.out.println(date);
	}
}