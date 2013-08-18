/**
 * Write a program to move the disks from the first rod to the last using Stacks.
 *
 * @author xmtsui
 * @version v1.0
 */
import com.tsui.util.ArrayStack;
class _3_4_B_HanoiStack{
	public static void main(String[] args)
	{
		//声明三个rod引用，并初始化
		//0:src, 1:buf; 2:des
		Rod[] rod = new Rod[3];
		for(int i=0; i<3; ++i)
		{
			rod[i] = new Rod(i+1);
		}

		//在第一根rod上增加disk
		int number = 3;
		for(int i=number-1; i>=0; --i)
		{
			rod[0].add(i);
		}
		rod[0].move(number, rod[0], rod[2], rod[1]);
	}
}

class Rod{
	private int rodIndex;
	private ArrayStack<Integer> disks = new ArrayStack<Integer>();
	private static int step=1;

	/**
	 * 构造函数，初始化Rod编号
	 */
	Rod(int index)
	{
		this.rodIndex = index;
	}
	/**
	 * get the rod index
	 * @return [description]
	 */
	public int index()
	{
		return rodIndex;
	}

	/**
	 * 在每个柱子上增加disk，数字大小代表disk大小，
	 * 原则是小的放在大的上面，没有一样大的
	 * @param disk [description]
	 */
	public void add(int disk)
	{
		if(rangeCheck(disk))
			disks.push(disk);
	}

	/**
	 * [removeAndAddTo description]
	 * @param des [description]
	 */
	public void removeAndAddTo(Rod des)
	{
		System.out.println("Step "+ step++ + ": from rod " 
			+ index() + " to rod " + des.index());
		try{
			des.add(disks.pop());
		}catch(Exception e)
		{

		}
	}

	public void move(int number, Rod src, Rod des, Rod buf)
	{
		if(number == 0 || number == 1)
			removeAndAddTo(des);
		else
		{
			move(number-1, src, buf, des);
			move(1, src, des, buf);
			buf.move(number-1, buf, des, src);
		}
	}
	/**
	 * 监测插入rod的合法性
	 * @param  disk [description]
	 * @return      [description]
	 */
	private boolean rangeCheck(int disk)
	{	
		try{
			if(disk<0 || disk>= disks.peek())
			{
				System.out.println("Error placing disk...");
				return false;
			}
			else
				return true;
		}catch(Exception e)
		{
		}
		return false;
	}

}