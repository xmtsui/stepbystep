/**
 * 孩子兄弟表示法实现的树 (Unfinished)
 * Child + Sibling Linked Tree
 * 链式存储结构
 *
 * @author xmtsui
 * @version v1.0
 */
public class Tree3<E> implements Tree<E>{
	private TreeNode<E> root;
	private int count;
	public Tree3(){
		initTree();
	}
	/**
	 * 构造空树
	 */
	public void initTree()
	{
		count = 0;
		root = null;
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
	public void doTraverse()
	{

	}
	public static class TreeNode<E>{
		E value;
		TreeNode<E> firstChild, rightSib;
	}
}