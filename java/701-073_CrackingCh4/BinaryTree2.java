/**
 * 链式存储结构实现的二叉树
 * 
 * @author xmtusi
 * @version v1.0
 */
class LinkedBinaryTree<E> implements Tree<E>{

	private BTNode<E> root;
	private int count;

	LinkedBinaryTree()
	{
		initTree();
	}

	/**
	 * 构造空树
	 */
	public void initTree()
	{
		root = null;
		count = 0;
	}

	/**
	 * 销毁树
	 */
	public void destroyTree()
	{
		
	}
	
	/**
	 * 若树存在，则清空
	 */
	public void clearTree()
	{

	}

	/**
	 * 判断树是否为空
	 * @return 空的话返回true
	 */
	public boolean isTreeEmpty()
	{
		return false;
	}

	/**
	 * 获取树的深度（高度）
	 * @return 空的话返回0
	 */
	public int getTreeDepth()
	{
		return -1;
	}
	
	/**
	 * 遍历树
	 */
	public void doTranverse()
	{

	}

	/**
	 * 二叉树节点定义
	 */
	private static class BTNode<E>{
		E value;
		BTNode<E> leftChild, rightChild;
		
		BTNode(E value, BTNode<E> leftChild, BTNode<E> rightChild)
		{
			this.value = value;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
	}
}