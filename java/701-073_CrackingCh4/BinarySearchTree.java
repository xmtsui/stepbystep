import java.util.Deque;
import java.util.LinkedList;
class BinarySearchTree<E extends Comparable<E>> implements Tree<E>{
	private TreeNode<E> root;

	public BinarySearchTree()
	{
		initTree();
	}
	/**
	 * 构造空树
	 */
	public void initTree(){
		root = new TreeNode<E>();
	}

	/**
	 * 销毁树
	 */
	public void destroyTree(){
		clearTree();
	}
	
	/**
	 * 若树存在，则清空
	 */
	public void clearTree(){
		clearTree1(root);
		root = null;
	}

	private void clearTree1(TreeNode<E> node)
	{
		if(node != null)
		{
			clearTree1(node.leftChild);
			clearTree1(node.rightChild);
			node.leftChild = null;
			node.rightChild = null;
			node.parent = null;
			node = null;
		}
	}

	/**
	 * 判断树是否为空
	 * @return 空的话返回true
	 */
	public boolean isTreeEmpty(){
		return root==null;
	}

	/**
	 * 获取树的深度（高度）
	 * @return 空的话返回0
	 */
	public int getTreeDepth(){
		return getTreeDepth1(root);
	}

	private int getTreeDepth1(TreeNode<E> node)
	{
		if(node == null)
			return 0;
		if(node.leftChild==null && node.rightChild==null)
			return 1;
		else
		{
			int d1 = getTreeDepth1(node.leftChild);
			int d2 = getTreeDepth1(node.rightChild);
			return d1 > d2 ? (d1+1) : (d2+1);
		}
	}
	
	/**
	 * 遍历树
	 */
	public void doTraverse(){
		if(root == null)
		{
			System.out.println("NULL TREE");
		}
		else{
			System.out.print("Pre   trav: ");
			preTrav(root);
			System.out.println();
			System.out.print("in    trav: ");
			inTrav(root);
			System.out.println();
			System.out.print("post  trav: ");
			postTrav(root);
			System.out.println();
			System.out.print("layer trav: ");
			layerTrav(root);
			System.out.println();
		}
	}

	/**
	 * 前序遍历
	 * @param node [description]
	 */
	private void preTrav(TreeNode<E> node)
	{
		if(node != null)
			System.out.print(node.value);
		if(node.leftChild != null)
			preTrav(node.leftChild);
		if(node.rightChild != null)
			preTrav(node.rightChild);
	}

	/**
	 * 中序遍历
	 * @param node [description]
	 */
	private void inTrav(TreeNode<E> node)
	{
		if(node.leftChild != null)
			inTrav(node.leftChild);
		if(node != null)
			System.out.print(node.value);
		if(node.rightChild != null)
			inTrav(node.rightChild);
	}

	/**
	 * 后序遍历
	 * @param node [description]
	 */
	private void postTrav(TreeNode<E> node)
	{
		if(node.leftChild != null)
			postTrav(node.leftChild);
		if(node.rightChild != null)
			postTrav(node.rightChild);
		if(node != null)
			System.out.print(node.value);
	}

	/**
	 * 层序遍历
	 * @return [description]
	 */
	private void layerTrav(TreeNode<E> root)
	{
		Deque<TreeNode<E>> deque = new LinkedList<TreeNode<E>>();
		if(root != null)
			deque.offer(root);
		while(!deque.isEmpty())
		{
			System.out.print(deque.peek().value);
			TreeNode<E> node = deque.poll();
			if(node.leftChild != null)
				deque.offer(node.leftChild);
			if(node.rightChild != null)
				deque.offer(node.rightChild);
		}
	}

	/*自定义操作*/

	/**
	 * 获取根结点
	 * @return [description]
	 */
	public TreeNode<E> getRoot()
	{
		return root;
	}
	
	/**
	 * 查找一个节点，找到后返回当前节点，没找到的话返回null
	 * @param  value [description]
	 * @return       [description]
	 */
	public TreeNode<E> searchBST(E value)
	{
		int i = 0;
		TreeNode<E> tmp = root;
		while(tmp != null)
		{
			// System.out.println("第" + i + "层");
			int res = value.compareTo(tmp.value);
			if(res > 0)
				tmp = tmp.rightChild;
			else if(res < 0)
				tmp = tmp.leftChild;
			else
				return tmp;
		}
		return null;
	}

	/**
	 * 插入一个新的节点，如果重复的话插入失败
	 * @param  value [description]
	 * @return       [description]
	 */
	public boolean insertBST(E value)
	{
		if(root.value == null)
		{
			root.value = value;
			return true;
		}	
		else
		{
			TreeNode<E> tmp = root;
			TreeNode<E> parent = null;
			int res = 0;
			while(tmp != null)
			{
				parent = tmp;
				res = value.compareTo(tmp.value);
				if(res > 0)
					tmp = tmp.rightChild;
				else if(res < 0)
					tmp = tmp.leftChild;
				else
					return false;//已有重复的，不再插入
			}

			TreeNode<E> newn = new TreeNode<E>(value);
			if(res > 0)
			{
				parent.rightChild = newn;
				newn.left = false;
			}
			else
			{
				parent.leftChild = newn;
				newn.left = true;
			}
			newn.parent = parent;
			return true;
		}
	}

	public boolean deleteBST(E value)
	{
		TreeNode<E> tmp = searchBST(value);
		if(tmp == null)
			return false;
		else
		{
			//左右子树都空
			if(tmp.leftChild == null && tmp.rightChild==null)
			{
				if(tmp == root)
					tmp = null;
				else if(tmp.left)
					tmp.parent.leftChild = null;
				else
					tmp.parent.rightChild = null;
			}
			else if(tmp.leftChild == null)//只有右子树
			{
				if(tmp == root)
				{
					root = tmp.rightChild;
					root.parent = null;
					root.left = false;
				}
				else if(tmp.left)
				{
					tmp.parent.leftChild = tmp.rightChild;
					tmp.rightChild.parent = tmp.parent;
					tmp.rightChild.left = true;
				}
				else
				{
					tmp.parent.rightChild = tmp.rightChild;
					tmp.rightChild.parent = tmp.parent;
					tmp.rightChild.left = false;
				}
			}
			else if(tmp.rightChild == null)//只有左子树
			{
				if(tmp == root)
				{
					root = tmp.leftChild;
					root.parent = null;
					root.left = false;
				}
				else if(tmp.left)
				{
					tmp.parent.leftChild = tmp.leftChild;
					tmp.leftChild.parent = tmp.parent;
					tmp.leftChild.left = true;
				}
				else
				{
					tmp.parent.rightChild = tmp.leftChild;
					tmp.leftChild.parent = tmp.parent;
					tmp.leftChild.left = false;
				}
			}
			else//左右子树都不为空
			{
				TreeNode<E> tmpParent = tmp.parent;
				TreeNode<E> tmpLeftChild = tmp.leftChild;
				TreeNode<E> tmpRightChild = tmp.rightChild;
				boolean tmpLeft = tmp.left;
				tmp = tmp.leftChild;

				//找到要删除节点的前驱
				while(tmp.rightChild != null)
					tmp = tmp.rightChild;
				
				//删除找到的前驱节点的父节点的引用
				if(tmp.left)
					tmp.parent.leftChild = null;
				else
					tmp.parent.rightChild = null;

				//如果要删除的节点是根节点
				if(tmpParent == null)
					root = tmp;
				else if(tmpLeft)//如果要删除的节点是其父节点的左孩子
					tmpParent.leftChild = tmp;
				else//如果要删除的节点是其父节点的右孩子
					tmpParent.rightChild = tmp;

				if(tmp == tmpLeftChild)//如果要删除的节点的前驱是其左节点,则其左孩子不变
				{
					tmp.rightChild = tmpRightChild;
					tmp.left = tmpLeft;
				}
				else
				{
					tmp.leftChild = tmpLeftChild;
					tmp.rightChild = tmpRightChild;
					tmp.left = tmpLeft;
				}
			}
			tmp = null;
			return true;
		}
	}

	/**
	 * 二叉树节点定义
	 */
	public static class TreeNode<E>{
		E value;
		TreeNode<E> leftChild, rightChild, parent/*若无父节点，则删除的时候无法删除*/;
		boolean left;

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

	public static void main(String[] args)
	{
		BinarySearchTree<String> t = new BinarySearchTree<String>();
		t.insertBST("6");
		t.insertBST("3");
		t.insertBST("4");
		t.insertBST("2");
		t.insertBST("8");
		t.insertBST("9");
		t.doTraverse();
		System.out.println(t.searchBST("6").value);
		t.deleteBST("6");
		t.deleteBST("9");
		t.doTraverse();

		//非平衡二叉树
		BinarySearchTree<String> t1 = new BinarySearchTree<String>();
		t1.insertBST("1");
		t1.insertBST("2");
		t1.insertBST("3");
		t1.insertBST("4");
		t1.insertBST("5");
		t1.insertBST("6");
		System.out.println("t1 depth: " + t1.getTreeDepth());
		t1.doTraverse();
		System.out.println(t1.searchBST("6").value);
		t1.deleteBST("6");
		t1.deleteBST("9");
		t1.doTraverse();
	}
}