import java.lang.reflect.Field;

public class TestHackIntern {

    public static void main(String[] args) {
        String test = "com.alipay.One";
        try {
            Field valueField = String.class.getDeclaredField("value");
            valueField.setAccessible(true);
            valueField.set("com.alipay.One", "com.alipay.two".toCharArray());

            System.out.println(test);
        } catch (Exception e) {

        }
    }
}