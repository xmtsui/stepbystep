import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  

public class MockPing {  

    public static void pingCommand(String ping){  
        BufferedReader br=null;
        try {  
            Process process=Runtime.getRuntime().exec(ping);  
            try {  
                process.waitFor();//等待子进程完成再往下执行  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            br=new BufferedReader(new InputStreamReader(process.getInputStream()));  
            String line=null;  
            while((line=br.readLine())!=null){  
                System.out.println(line);
            }  
        } catch (IOException e) {
            e.printStackTrace();  
        } finally{
            try{
                br.close();
            } catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }  
    public static void main(String[] args) {  
        MockPing.pingCommand("ping -c 3 127.0.0.1"); 
    }  

}  