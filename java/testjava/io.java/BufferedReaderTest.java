import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
class BufferedReaderTest{
	public static void main(String[] args)
	{
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try{
			fis = new FileInputStream("test.txt");
			// InputStreamReader isr = new InputStreamReader(System.in);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			while(br.ready())
			{
				br.skip(1);
				if(br.markSupported())
				{
					br.mark(1);//readlimit 参数告知此输入流在标记位置无效之前允许读取的字节数。
					String next = br.readLine();
					System.out.println(next);

					br.reset();
					next = br.readLine();
					System.out.println(next);
				}
			}
		}catch (IOException e)
		{
			e.printStackTrace();
		}finally
		{
			try{
				br.close();
				isr.close();
				fis.close();
			}catch (IOException e)
			{

			}
		}
	}
}