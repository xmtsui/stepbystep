/**
 * 线性存储结构实现的二叉树
 * 适合完全二叉树Full binary tree
 * 
 * 二叉树的编号是从1开始的
 * 线性存储结构中的index是从0开始的
 * 
 * @author xmtusi
 * @version v1.0
 */
import java.util.Arrays;
import java.lang.Math;
import java.util.ArrayDeque;
class BinaryTree1<E> implements Tree<E>{

	private E root;/*null if empty*/
	private int count;/*tree实际节点数*/
	private E[] nodes;/*tree节点数组*/

	private static final int MAX_DEEP = 5;
	private static final int MAX_SIZE = (int)(Math.pow(2, MAX_DEEP) - 1);

	/**
	 * 默认深度构造空树
	 */
	BinaryTree1()
	{
		initTree();
	}

	/**
	 * 构造空树
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initTree()
	{
		nodes = (E[])new Object[MAX_SIZE];
		root = null;
		count = 0;
	}

	/**
	 * 销毁树
	 */
	@Override
	public void destroyTree()
	{
		clearTree();
		nodes = null;
	}
	
	/**
	 * 若树存在，则清空
	 */
	@Override
	public void clearTree()
	{
		//foreach 不能修改值
		// for(E item:nodes)
		// {
		// 	item = null;
		// }
		int size = nodes.length;
		for(int i=0; i<size; ++i)
		{
			nodes[i] = null;
			count--;
		}
		root = null;
	}

	/**
	 * 判断树是否为空
	 * @return 空的话返回true
	 */
	@Override
	public boolean isTreeEmpty()
	{
		return root == null;
		// return nodes[0] == null;
		// return count == 0;
	}

	/**
	 * 获取树的深度（高度）
	 * 遍历每一层，只要有不为null的就depth++
	 * 否则，返回depth
	 * 每一层的开始是：2左移depth-1，再减1;
	 * 结束是 2左移depth, 再减1
	 * @return 空的话返回0
	 */
	@Override
	public int getTreeDepth()
	{
		int depth = 0;
		if(root == null)
			return 0;
		else
			depth = 1;
		boolean flag = true;
		while(flag)
		{
			int start = (2<<depth-1) - 1;
			int end = (2<<depth) - 1;
			// System.out.println("start: " + start);
			// System.out.println("end: " + end);
			int depth_tmp = depth;
			int size = nodes.length;
			for(int i=start; i<end && i<size; ++i)
			{
				if(nodes[i] != null)
				{
					depth++;
					break;
				}
			}
			if(depth_tmp == depth)
			{
				flag = false;
			}
		}
		return depth;
	}
	
	/**
	 * 遍历树
	 */
	@Override
	public void doTraverse()
	{
		System.out.println("----start trav-----");
		System.out.print("norm trav: ");
		if(root == null)
			System.out.print("EMPTY BINARY TREE");
		else
		{
			for(E item:nodes)
			{
				if(item != null)
					System.out.print(item);
			}
		}
		System.out.println();

		System.out.print("pre  trav: ");
		preTrav(1);
		System.out.println();

		System.out.print("in   trav: ");
		inTrav(1);
		System.out.println();

		System.out.print("post trav: ");
		postTrav(1);
		System.out.println();
		System.out.println("----end trav-----");
	}
	
	private void preTrav(int node)
	{
		int size = nodes.length;
		if(node<=0 || node>=MAX_SIZE)
			return;
		if(nodes[node-1]!=null || node<MAX_SIZE)
			System.out.print(nodes[node-1]);
		if(nodes[2*node-1]!=null)
			preTrav(2*node);
		if(nodes[2*node+1 - 1]!=null)
			preTrav(2*node+1);
	}

	private void inTrav(int node)
	{
		int size = nodes.length;
		if(node<=0 || node>=MAX_SIZE)
			return;
		if(nodes[2*node-1]!=null)
			inTrav(2*node);
		if(nodes[node-1] != null || node<MAX_SIZE)
			System.out.print(nodes[node-1]);
		if(nodes[2*node+1 - 1]!=null)
			inTrav(2*node+1);

	}

	private void postTrav(int node)
	{
		int size = nodes.length;
		if(node<=0 || node>=MAX_SIZE)
			return;
		if(nodes[2*node-1]!=null)
			postTrav(2*node);
		if(nodes[2*node+1 - 1]!=null)
			postTrav(2*node+1);
		if(nodes[node-1] != null || node<MAX_SIZE)
			System.out.print(nodes[node-1]);
	}

	/* Binary tree definition */

	/**
	 * 根据编号顺序创建二叉树
	 * @param elements [description]
	 */
	public boolean createBinaryTree(E[] elements)
	{
		int size = elements.length;
		if(elements == null)
			return false;
		if(size == 0 || size > MAX_SIZE)
			return false;
		// for(int i=0; i<size; ++i)
		// {
		// 	nodes[i] = elements[i];
		// }

		// 未改变nodes，使用初始化时的内存
		System.arraycopy(elements, 0, nodes, 0, size);

		//改变了nodes,重新分配了内存
		//E[] tmp = nodes;
		// nodes = Arrays.copyOf(elements, size);
		// if(tmp == nodes)
		// {
		// System.out.println("yes");
		// }
		// nodes = elements;
		
		if(root == null)
			root = nodes[0];
		return true;
	}

	/**
	 * 获取二叉树根结点
	 * @return [description]
	 */
	public E getRoot()
	{
		if(root == null)
			return null;
		return nodes[0];
	}

	/**
	 * 根据编号指定某一节点，获取左子节点
	 * 当前index的2倍，但index与实际存储编号相差1
	 * 所以为2*index - 1
	 * 
	 * @param  index [description]
	 * @return       [description]
	 */
	public E getLeftChild(int index)
	{
		if(index >= MAX_SIZE)
			return null;
		E tmp = nodes[2*index - 1];
		if(tmp == null)
			return null;
		else
			return tmp;
	}

	/**
	 * 根据编号指定某一节点，获取右子节点
	 * 当前index的2倍+1，但index与实际存储编号相差1
	 * 所以为2*index + 1 - 1
	 * @param  index [description]
	 * @return       [description]
	 */
	public E getRightChild(int index)
	{
		if(index >= MAX_SIZE)
			return null;
		E tmp = nodes[2*index + 1 - 1];
		if(tmp == null)
			return null;
		else
			return tmp;
	}

	/**
	 * 测试主函数
	 * @param  args [description]
	 * @return      [description]
	 */
	public static void main(String[] args)
	{
		BinaryTree1<String> abt = new BinaryTree1<String>();
		String[] strs = {"A","B","C","D","E","F","G"};

		abt.createBinaryTree(strs);

		System.out.println("root:" + abt.getRoot());
		System.out.println("left:" + abt.getLeftChild(3));
		System.out.println("right:" + abt.getRightChild(3));
		System.out.println("depth:" + abt.getTreeDepth());
		
		abt.doTraverse();
		
		abt.clearTree();
		abt.doTraverse();
	}

}