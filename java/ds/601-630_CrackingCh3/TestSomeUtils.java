import java.util.Stack;
import java.util.Iterator;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
class TestSomeUtils{
	public static void main(String[] args)
	{
		//Stack使用
		Stack<String> s = new Stack<String>();
		s.push("Hello");
		s.push("World");
		System.out.println("peek:" + s.peek());

		//Vector使用
		Vector<Integer> v = new Vector<Integer>();
		for(int i=0; i<1; i++)
			v.addElement(0);
		
		Iterator itr1 = v.iterator();
		while(itr1.hasNext())
			System.out.println(itr1.next());
		
		//ArrayList使用
		List<Integer> l = new ArrayList<Integer>();
		for(int i=0; i<1; i++)
			l.add(0);
		Iterator itr2 = l.iterator();
		while(itr2.hasNext())
			System.out.println(itr2.next());

		//序列化对比
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obejct_vector.txt"));
			ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("obejct_arraylist.txt"));
			oos.writeObject(v);
			oos1.writeObject(l);
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}

		//clone方法测试
		Integer[] elementData = new Integer[10];
		for(int ind=0; ind<6; ind++)
			elementData[ind] = 0;
		Integer[] element;
		// element = elementData;//同一段内存
		element = elementData.clone();//clone之后,不是同一段内存
		System.out.println("====" + element[8]);//输出null
		if(elementData == element)
			System.out.println("====" + element.length);
	}
}