/**
 * Test Arrays.copyOf
 *
 * @author xmtsui
 * @version v1.0
 */
import java.util.Arrays;
class TestArraysCopyOf{
	public static void main(String[] args)
	{
		String[] vertex ={"A","B","C","D","E","F","G","H"};
		int size = vertex.length;
		String[] newv = new String[size];
		// String[] newv = Arrays.copyOf(vertex,0);
		System.arraycopy(vertex, 0, newv, 0, size);
	
		/*
		int[] vertex,vertex1;
		...vertex 初始化...

		vertex1 = vertex;
		System.arraycopy(vertex, 0, vertex1, 0, size);
		区别于
		vertex1 = Arrays.copyOf(vertex,size);
		前者没有新生成数组，vertex==vertex1;
		后者新生成数组vertex != vertex1;
		*/
	
		// String[] newv = Arrays.copyOf(vertex,1);
		// String[] newv = Arrays.copyOf(vertex,8);
		// String[] newv = Arrays.copyOf(vertex,9);
		// String[] newv = Arrays.copyOfRange(vertex,3,4);
		for(String item : newv)
			System.out.print(item);
		System.out.println(vertex==newv);
	}
}