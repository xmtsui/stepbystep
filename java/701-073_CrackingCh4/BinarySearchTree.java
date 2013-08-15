class BinarySearchTree<T extends Comparable> implements Tree<T>{
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

	public int test(T e1, T e2)
	{
		return e1.compareTo(e2);
	}

	/**
	 * 二叉树节点定义
	 */
	public static class TreeNode<T>{
		T value;
		TreeNode<T> leftChild, rightChild;

		TreeNode(){}

		TreeNode(T value)
		{
			this.value = value;
		}

		TreeNode(T value, TreeNode<T> leftChild, TreeNode<T> rightChild)
		{
			this.value = value;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}

		// public int compareTo(T t)
		// {
			// return -2;
		// }

	}

	public static void main(String[] args)
	{
		BinarySearchTree<TreeNode<String>> t = new BinarySearchTree<TreeNode<String>>();
		t.doTraverse();
		TreeNode<String> tn1 = new TreeNode<String>();
		TreeNode<String> tn2 = new TreeNode<String>();
		tn1.value="1";
		tn2.value="2";

		// System.out.println(tn.value);
		System.out.println(t.test(tn1,tn2));
	}
}