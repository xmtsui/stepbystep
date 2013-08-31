import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.StringReader;  

public class MarkResetTest {  
    public static void main(String[] args) throws IOException { 
        String s = "This is the internal StringReader buffer.";
        StringReader stringReader = new StringReader(s); 
        BufferedReader bufReader = new BufferedReader(stringReader);  

        // Read from the underlying StringReader.
        char[] arr = new char[s.length()];
        bufReader.read(arr, 0, arr.length - 14);
        System.out.println(arr);

        // Call mark after having read all but the last 10
        // characters from the StringReader.
        if(bufReader.markSupported()) {
            System.out.println("mark supported.");
            bufReader.mark(14);// 再读14个失效
        }
        for(int i=0; i<14; ++i)
            bufReader.read();
        // bufReader.read(arr, arr.length - 14, 14);//再读14个
        // System.out.println(arr);
        // bufReader.read();
        bufReader.reset();//越过流尾reaset失败，报错，没越过流尾不报错
        stringReader.close();
        bufReader.close();
    }
}