/**
 * read a class file, print in hex
 * @author xmtsui
 * @version v1.0
 */
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
class ClassToHexReader{
	public static void main(String[] args)
	{
		try
		{
			reader = new BufferedReader(new InputStreamReader(System.in));
			tokenizer = null;
			writer = new PrintWriter(System.out);
			String s = reader.readLine();

			FileInputStream fis=null;
			if(s!=null)
				fis = new FileInputStream(s+".class");
			
			int tmp=0;
			// while((tmp = fis.read()) != -1)
				// System.out.printf("%x", tmp);
			// System.out.printf("\n");

			BufferedInputStream bis = new BufferedInputStream(fis);
			writer.printf("\t");

			//打印列标
			for(int i=0; i<16; ++i)
				writer.printf("  %x", i);
			//打印第一行的行标 #表示打印数字类型，此处为16进制，加上0x
			//08表示共8位，前面补零
			writer.printf("\n%#08x",0);
			
			int columns=0;//列信息
			int line=0;//行信息
			char[] tmpchar=new char[16];//可显示字符的缓冲区
			
			while((tmp=bis.read()) != -1)
			{
				//打印读取到的一个字节
				writer.printf(" %02x", tmp);
				//如果不是可显示字符
				if(tmp<32||tmp>126)//1～127
					tmpchar[columns]='.';
				else
					tmpchar[columns]=(char)tmp;
				columns++;
				
				//最后一列特殊处理,打印可显示字符
				if(columns==16)
				{
					columns=0;
					line+=16;
					writer.printf("|");
					for(char item:tmpchar)
						writer.printf("%c",item);
					writer.printf("\n%08x", line);
				}
			}
			//最后一行特殊处理
			for(int i=columns; i<16; ++i)
				writer.printf("   ");
			writer.printf("|");
			for(char item:tmpchar)
				writer.printf("%c",item);
			writer.printf("%n");
		}catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}catch (IOException e)
		{
			e.printStackTrace();
		}finally
		{
			try{
				reader.close();
				writer.close();
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	static BufferedReader reader;
	static StringTokenizer tokenizer;
	static PrintWriter writer;
	static int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}

	static String nextToken() throws IOException {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			tokenizer = new StringTokenizer(reader.readLine());
		}
		return tokenizer.nextToken();
	}
}