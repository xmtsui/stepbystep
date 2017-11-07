import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TestRegex {
    public static void main(String[] args) {
        // 1. 按制定模式在字符串中查找
        String line = "/rest/";
        String regular = "/rest/.*?";//非贪婪匹配
        // String regular = "/rest/.*";//贪婪匹配

        // 创建pattern对象
        Pattern pattern = Pattern.compile(regular);

        // 创建matcher对象
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            System.out.println("Found value:" + matcher.group(0));
        }

        // 2. 按制定模式在字符串中查找
        line = "This order was placed for QT3000! OK?";
        regular = "(.*?)(\\d+)(.*)";

        // 创建pattern对象
        pattern = Pattern.compile(regular);

        // 创建matcher对象
        matcher = pattern.matcher(line);

        if (matcher.find()) {
            System.out.println("Found value:" + matcher.group(0));
            System.out.println("Found value:" + matcher.group(1));
            System.out.println("Found value:" + matcher.group(2));
            System.out.println("Found value:" + matcher.group(3));
        }

        // 3. ALL
        line = "xxxSB-xxx";
        // regular = "^[A-Za-z0-9\\-_]+$";
        // regular = "^[\\.%|a-zA-Z0-9_-]+$";
        // regular = "^SB-[\\.%|a-zA-Z0-9_-]*$";
        // regular = "^[\\.%|a-zA-Z0-9_-]*SB-$";
        regular = "^[\\.%|a-zA-Z0-9_-]*SB-[\\.%|a-zA-Z0-9_-]*$";

        // 创建pattern对象
        pattern = Pattern.compile(regular);

        // 创建matcher对象
        matcher = pattern.matcher(line);

        if (matcher.find()) {
            System.out.println("Found value:" + matcher.group(0));
        }
    }
}