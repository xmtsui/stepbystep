/**
 * Write an algorithm to find the ‘next’ node (e.g., in-order successor) 
 * of a given node in a binary search tree where each node has a link 
 * to its parent.
 *
 * @author xmtsui
 * @version v1.0
 */
class _4_5_FindSuccessorInBST{
	/**
	 * 二叉树的后继
	 * @param  node [description]
	 * @return      [description]
	 */
	static <E> BinarySearchTree.TreeNode<E> findSuccessor(BinarySearchTree.TreeNode<E> node)
	{
		if(node == null)
			return null;
		
		//首先检测node的右子树是否存在
		BinarySearchTree.TreeNode<E> tmp = node;
		//若右子树存在，则从右孩子开始向左找到右孩子最左子孙
		if(tmp.rightChild != null)
		{
			tmp = tmp.rightChild;
			//再找到右子树的最左子孙
			while(tmp.leftChild != null)
				tmp = tmp.leftChild;
		}
		else//如果node的右子树不存在
		{
			//检测node的父节点
			//如果为空，则表示是根结点
			if(tmp.parent == null)
				return null;
			else
			{
				//如果当前节点是左孩子
				if(tmp.left)
					tmp = tmp.parent;
				else//如果是右孩子，则一直向上找，直到是左孩子
					while(tmp!=null && !tmp.left)
						tmp = tmp.parent;
			}
		}
		return tmp;

	}

	/**
	 * 二叉树前驱
	 * @param  node [description]
	 * @return      [description]
	 */
	static <E> BinarySearchTree.TreeNode<E> findPredecessor(BinarySearchTree.TreeNode<E> node)
	{
		if(node == null)
			return null;
		BinarySearchTree.TreeNode<E> tmp = node;
		//若左子树存在，则从左孩子开始向右找到左孩子最右子孙
		if(tmp.leftChild != null)
		{
			tmp = tmp.leftChild;
			while(tmp.rightChild != null)
				tmp = tmp.rightChild;
		}
		else//若左子树不存在
		{
			//检测node的父节点
			//如果为空，则表示是根结点
			if(tmp.parent == null)
				return null;
			else
			{
				//如果当前节点是右孩子
				if(!tmp.left)
					tmp = tmp.parent;
				else//如果是左孩子，则一直向上找，直到是右孩子
					while(tmp!=null && tmp.left)
						tmp = tmp.parent;
			}
		}
		return tmp;
	}
	public static void main(String[] args)
	{
		System.out.println("***************BSTree1***************");
		BinarySearchTree<String> t = new BinarySearchTree<String>();
		t.insertBST("6");
		t.insertBST("3");
		t.insertBST("4");
		t.insertBST("2");
		t.insertBST("8");
		t.insertBST("9");
		t.doTraverse();
		String target = "6";
		
		BinarySearchTree.TreeNode<String> node = t.searchBST(target);
		System.out.println(node.value);
		
		node = findSuccessor(node);
		if(node == null)
			System.out.println("无后继");
		else
			System.out.println("后继是：" + node.value);
		
		node = t.searchBST(target);
		node = findPredecessor(node);
		if(node == null)
			System.out.println("无前驱");
		else
			System.out.println("前驱是：" + node.value);

	}
}