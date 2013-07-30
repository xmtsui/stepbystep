/**
 * 三叉链表结构实现的二叉树
 *
 * @author xmtsui
 * @version v1.0
 */
class BinaryTree3<E> implements Tree<E>{
	/**
	 * 构造空树
	 */
	public void initTree(){

	}

	/**
	 * 销毁树
	 */
	public void destroyTree(){

	}
	
	/**
	 * 若树存在，则清空
	 */
	public void clearTree(){

	}

	/**
	 * 判断树是否为空
	 * @return 空的话返回true
	 */
	public boolean isTreeEmpty(){
		return false;
	}

	/**
	 * 获取树的深度（高度）
	 * @return 空的话返回0
	 */
	public int getTreeDepth(){
		return -1;
	}
	
	/**
	 * 遍历树
	 */
	public void doTraverse(){

	}

	/**
	 * 二叉树节点定义
	 */
	private static class TreeNode<E>{
		E value;
		TreeNode<E> leftChild, rightChild, parent;

		TreeNode(){}

		TreeNode(E value)
		{
			this.value = value;
		}

		TreeNode(E value, TreeNode<E> leftChild, 
			TreeNode<E> rightChild, TreeNode<E> parent)
		{
			this.value = value;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.parent = parent;
		}
	}
}