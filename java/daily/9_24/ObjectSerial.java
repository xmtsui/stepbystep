import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.IOException;
class ObjectSerial implements Serializable{
	private transient int a = 10;
	private int b = 1;
	public static void main(String[] args){
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.txt"));
			ObjectSerial os = new ObjectSerial();
			oos.writeObject(os);
			os.a++;
			os.b++;

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"));
			ObjectSerial os_de = (ObjectSerial) ois.readObject();
			System.out.println(os_de.a);
			System.out.println(os_de.b);
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}