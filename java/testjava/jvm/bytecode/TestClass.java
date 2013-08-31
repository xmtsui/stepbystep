// import java.util.RandomAccess;
public class TestClass{// implements RandomAccess{
	private int m=0;
	public int inc(int i){
		// if(m<1000)
		// {
		// 	inc(m);
		// 	m++;
		// }
		return m+1;
	}

	private class A{
		public void inc(){
			m++;
		}
	}
}