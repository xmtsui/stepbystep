/**
 * 二叉树的平衡检测
 * 
 * @author xmtsui
 * @version  v1.0
 */
//import static BinaryTree2.TreeNode;
//this cant be used, should be full qulified name , canonical name
class _4_1_CheckBalanced{
	private static int maxDepth(BinaryTree2.TreeNode<String> node)
	{
		if(node == null)
			return 0;
		return 1 + Math.max(maxDepth(node.leftChild), maxDepth(node.rightChild));
	}

	private static int minDepth(BinaryTree2.TreeNode<String> node)
	{
		if(node == null)
			return 0;
		return 1 + Math.min(minDepth(node.leftChild), minDepth(node.rightChild));

	}

	private static boolean isBalanced(BinaryTree2.TreeNode<String> node)
	{
		return (maxDepth(node) - minDepth(node) <= 1);
	}

	public static void main(String[] args)
	{
		BinaryTree2<String> tree = new BinaryTree2<String>("A");
		BinaryTree2.TreeNode<String> root = tree.getRoot();
		BinaryTree2.TreeNode<String> B = tree.addNode(root, "B", true);
		// BinaryTree2.TreeNode<String> C = tree.addNode(root, "C", false);
		tree.addNode(B, "D", true);
		// tree.addNode(C, "E", true);
		System.out.println("is balanced: " + isBalanced(root));
	}
}