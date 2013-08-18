/**
 * Design an algorithm and write code to find the first common ancestor 
 * of two nodes in a binary tree. Avoid storing additional nodes in 
 * a data structure. NOTE: This is not necessarily a binary search tree.
 *
 * @author xmtsui
 * @version v1.0
 */
class _4_6_FirstCommonAncestorInBiTree{
	/**
	 * [findCommon1 解法1]
	 * @param  root [description]
	 * @param  n1   [description]
	 * @param  n2   [description]
	 * @return      [description]
	 */
	static <E> BinaryTree2.TreeNode<E> findCommon1(BinaryTree2.TreeNode<E> root,
		BinaryTree2.TreeNode<E> n1, BinaryTree2.TreeNode<E> n2)
	{
		if(covers1(root.leftChild, n1) && covers1(root.leftChild, n2))
			return findCommon1(root.leftChild, n1, n2);
		if(covers1(root.rightChild, n1) && covers1(root.rightChild, n2))
			return findCommon1(root.rightChild, n1, n2);
		return root;
	}

	/**
	 * Covers touches every child node, 
	 * so we know that every single node in the tree must be touched 
	 * at least once, and many nodes are touched multiple times.
	 * @param  root [description]
	 * @param  n    [description]
	 * @return      [description]
	 */
	private static <E> boolean covers1(BinaryTree2.TreeNode<E> root, 
		BinaryTree2.TreeNode<E> n)
	{
		if(root == null)
			return false;
		// if(root.leftChild == n || root.rightChild == n)
		if(root == n)
			return true;
		return covers1(root.leftChild, n) || covers1(root.rightChild, n);
	}

	final static int TWO_FOUND = 2;
	final static int ONE_FOUND = 1;
	final static int NO_FOUND = 0;
	
	/**
	 * [findCommon2 解法2]
	 * @param  root [description]
	 * @param  n1   [description]
	 * @param  n2   [description]
	 * @return      [description]
	 */
	static <E> BinaryTree2.TreeNode<E> findCommon2(BinaryTree2.TreeNode<E> root,
		BinaryTree2.TreeNode<E> n1, BinaryTree2.TreeNode<E> n2)
	{
		if(n1 == n2 &&(root.leftChild == n2 || root.rightChild == n2))
			return root;
		int nodesFromLeft = covers2(root.leftChild, n1, n2);
		if(nodesFromLeft == TWO_FOUND)
		{
			if(root.leftChild==n1 || root.leftChild==n2)
				return root.leftChild;
			else 
				return findCommon2(root.leftChild, n1, n2);
		}else if(nodesFromLeft == ONE_FOUND)
		{
			if(root == n1 || root == n2)
				return root;
		}

		int nodesFromRight = covers2(root.rightChild, n1, n2);
		if(nodesFromRight == TWO_FOUND)
		{
			if(root.rightChild==n1 || root.rightChild==n2)
				return root.rightChild;
			else 
				return findCommon2(root.rightChild, n1, n2);
		}else if(nodesFromRight == ONE_FOUND)
		{
			if(root == n1 || root == n2)
				return root;
		}

		if(nodesFromLeft == ONE_FOUND && nodesFromRight == ONE_FOUND)
			return root;
		else
			return null;
	}

	private static <E> int covers2(BinaryTree2.TreeNode<E> root, 
		BinaryTree2.TreeNode<E> n1, BinaryTree2.TreeNode<E> n2)
	{
		int ret = NO_FOUND;
		if(root == null)
			return ret;
		if(root == n1 || root == n2)
			ret+=1;
		ret += covers2(root.leftChild, n1, n2);
		ret += covers2(root.rightChild, n1, n2);
		// if(ret == TWO_FOUND)
		return ret;
	}
	
	public static void main(String[] args)
	{
		BinaryTree2<String> tree = new BinaryTree2<String>("A");
		BinaryTree2.TreeNode<String> B = tree.addNode(tree.getRoot(), "B", true);
		BinaryTree2.TreeNode<String> C = tree.addNode(tree.getRoot(), "C", false);
		BinaryTree2.TreeNode<String> D = tree.addNode(B, "D", true);
		BinaryTree2.TreeNode<String> E = tree.addNode(C, "E", true);
		tree.doTraverse();
		System.out.println("Tree depth: " + tree.getTreeDepth());
		
		BinaryTree2.TreeNode<String> A = tree.getRoot();
		System.out.println(findCommon1(tree.getRoot(),A,C).value);
		System.out.println(findCommon2(tree.getRoot(),A,C).value);
	}	
}