import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTest {
    public static void main(String[] args) throws ParseException {
        long ms1 = 1387989084000L;//时间差，单位毫秒
        long ms2 = 1389576509121L;//时间差，单位毫秒

        Date d1 = new Date(ms1);
        Date d2 = new Date(ms2);

        System.out.println("d1: " + d1.toString());
        System.out.println("d2: " + d2.toString());
        //System.out.println(DateUtil.formatDate(d1, "yyyy-MM-dd hh:mm:ss"));
        //System.out.println(DateUtil.formatDate(d2, "yyyy-MM-dd hh:mm:ss"));

        java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String s1 = format1.format(d1);
        String s2 = format1.format(d2);

        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);

        Date revd = new Date(112, 12, 23, 13, 00, 00);
        System.out.println("new: " + revd.toGMTString());
        System.out.println("new: " + revd.toString());
        System.out.println("new: " + revd.getTime());

        SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        //        DATEFORMAT.parse("2013-12-23-11-00-00");
        //        DATEFORMAT.parse("2013-12-23-12-00-00");
        //        DATEFORMAT.parse("2013-12-23-13-00-00");
        System.out.println(DATEFORMAT.parse("2013-12-23-11-00-00").toString());
        System.out.println(DATEFORMAT.parse("2013-12-23-12-00-00").toString());
        System.out.println(DATEFORMAT.parse("2013-12-23-13-00-00").toString());
        System.out.println(DATEFORMAT.parse("2013-12-23-11-00-00").getTime());
        System.out.println(DATEFORMAT.parse("2013-12-23-12-00-00").getTime());
        System.out.println(DATEFORMAT.parse("2013-12-23-13-00-00").getTime());
        System.out.println(DATEFORMAT.parse("2000-01-01-23-59-59").getTime());
    }
}