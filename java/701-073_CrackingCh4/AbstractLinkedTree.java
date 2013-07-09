/**
 * 链式存储结构抽象类
 * 
 * 最大限度实现
 * 包括抽象方法定义
 * 节点定义
 * 
 * 孩子表示法实现ChildLinkedTree.java
 *
 * @author xmtsui
 * @version v1.0
 */
class AbstractLinkedTree<E> implements Tree<E>{
	protected AbstractLinkedTree(){}

	/*Tree 接口实现*/

	/**
	 * 构造空树
	 */
	public abstract void initTree();

	/**
	 * 销毁树
	 */
	public abstract void destroyTree();
	
	/**
	 * 若树存在，则清空
	 */
	public abstract void clearTree();

	/**
	 * 判断树是否为空
	 * @return boolean
	 */
	public abstract boolean isTreeEmpty();

	/**
	 * 获取树的深度（高度）
	 * @return [description]
	 */
	public abstract int getTreeDepth();

	/**
	 * 遍历树
	 */
	public abstract void doTranverse();

	/* Linked Tree 定义*/

	/**
	 * 节点定义
	 * 可根据需要增加和删除左儿子，右兄弟
	 */
	public static class TreeNode<E>{
		private Node<E> parent;
		private int parent, leftChild, rightSib;
		
		/**
		 * 构造一个新的节点
		 */
		Node(E value, int parent)
		{
			this.value = value;
			this.parent = parent;
		}
	}
}