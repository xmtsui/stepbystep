/**
 * 线性存储结构实现Tree
 * 
 * 双亲表示实现(ParentArrayTree.java)
 * 	快速获取某一个节点的父亲节点
 * 双亲加最左孩子，以及最左孩子的右兄弟实现
 * 	快速获取某一节点的父亲节点以及孩子节点
 * 	@author xmtsui
 * 	@version v1.0
 */
import java.util.List;
abstract class AbstractArrayTree<E> implements Tree<E>{
	protected AbstractArrayTree(){
	}
	
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

	/* Array Tree 定义*/
	
	/**
	 * 增加一个节点
	 * @param element   [description]
	 * @param parent    [description]
	 * @return 			返回创建的节点
	 */
	public abstract Node<E> addNode(E element, Node<E> parent);

	/**
	 * 获取根结点
	 * @return [description]
	 */
	public abstract Node<E> getRoot();

	/**
	 * 获取某一个节点的双亲节点
	 * @param  node [description]
	 * @return       [description]
	 */
	public abstract Node<E> getParent(Node<E> node);

	/**
	 * 获取某一个节点的最左儿子节点
	 * @param  node [description]
	 * @return       [description]
	 */
	public abstract Node<E> getLeftChild(Node<E> node);

	/**
	 * 若右兄弟存在，则返回该节点
	 * 不存在则返回-1
	 * @param  node [description]
	 * @return       [description]
	 */
	public abstract Node<E> getRightSibling(Node<E> node);


	/**
	 * 指定一个节点parentNode，增加一个子树childNode
	 * 子树的位置由index指定
	 * @param  parentNode [description]
	 * @param  childNode  [description]
	 * @param  index      [description]
	 * @return            [description]
	 */
	public abstract boolean insertChild(Node<E> parentNode, Node<E> childNode, int index);

	/**
	 * 删除一颗子树
	 * @param  node [description]
	 * @return      [description]
	 */
	public abstract boolean deleteChild(Node<E> node);
	
	/**
	 * 节点定义
	 * 可根据需要增加和删除左儿子，右兄弟
	 */
	public static class Node<E>{
		private E value;
		private int parent, leftChild, rightSib;
		
		/**
		 * 构造一个新的节点
		 */
		Node(E value, int parent)
		{
			this.value = value;
			this.parent = parent;
		}

		/**
		 * 设置当前节点的值
		 * @param value [description]
		 */
		void setValue(E value)
		{
			this.value = value;
		}

		/**
		 * 获取当前节点值
		 */
		E getValue()
		{
			return this.value;
		}

		void setParent(int parent)
		{
			this.parent = parent;
		} 

		int getParent()
		{
			return parent;
		}

		void setLeftChild(int leftChild)
		{
			this.leftChild = leftChild;
		}

		int getLeftChild()
		{
			return leftChild;
		}

		void setRightSibling(int rightSib)
		{
			this.rightSib = rightSib;
		}

		int getRightSibling()
		{
			return rightSib;
		}
	}
}