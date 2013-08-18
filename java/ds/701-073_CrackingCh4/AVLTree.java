class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E>{
	
	public boolean isBalanced()
	{
		return (maxDepth(root) - minDepth(root) <= 1) ? true : false;
	}

	private int minDepth(TreeNode<E> node)
	{
		if(node == null)
			return 0;
		else
			return 1 + Math.min(minDepth(node.leftChild), minDepth(node.rightChild));
	}

	private int maxDepth(TreeNode<E> node)
	{
		if(node == null)
			return 0;
		else
			return 1 + Math.max(maxDepth(node.leftChild), maxDepth(node.rightChild));
	}

	public static void main(String[] args)
	{
		System.out.println("***************AVLTree1***************");
		AVLTree<String> t = new AVLTree<String>();
		t.insertBST("6");
		t.insertBST("3");
		t.insertBST("4");
		t.insertBST("2");
		t.insertBST("8");
		t.insertBST("9");
		System.out.println("t depth: " + t.getTreeDepth());
		System.out.println("t is balanced: " + t.isBalanced());
		t.doTraverse();
		System.out.println(t.searchBST("6").value);
		t.deleteBST("6");
		t.deleteBST("9");
		t.doTraverse();

		System.out.println("\n***************AVLTree2***************");
		//非平衡二叉树
		AVLTree<String> t1 = new AVLTree<String>();
		t1.insertBST("1");
		t1.insertBST("2");
		t1.insertBST("3");
		t1.insertBST("4");
		t1.insertBST("5");
		t1.insertBST("6");
		System.out.println("t1 depth: " + t1.getTreeDepth());
		System.out.println("t1 is balanced: " + t1.isBalanced());
		t1.doTraverse();
		System.out.println(t1.searchBST("6").value);
		t1.deleteBST("6");
		t1.deleteBST("9");
		t1.doTraverse();
	}
}