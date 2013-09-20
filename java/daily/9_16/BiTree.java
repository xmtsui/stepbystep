public class BiTree{
	public static int max(TreeNode treeNode)
	{
		if(treeNode == null)
			return 0;
		else
		{
			int a = max(treeNode.leftChild);
			int b = max(treeNode.rightChild);
			return a>b ? a+1 : b+1;
		}
	}
	public static int min(TreeNode treeNode)
	{
		if(treeNode == null)
			return 0;
		else
		{
			int a = min(treeNode.leftChild);
			int b = min(treeNode.rightChild);
			return a<b ? a+1 : b+1;
		}
	}

	public static int doMinus(TreeNode treeNode)
	{
		return max(treeNode) - min(treeNode);
	}

	private static class TreeNode{
		String value;
		TreeNode leftChild;
		TreeNode rightChild;
		TreeNode(String value, TreeNode leftChild, TreeNode rightChild)
		{
			this.value = value;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
	}

	public static void main(String[] args)
	{
		TreeNode D = new TreeNode("C",null,null);
		TreeNode C = new TreeNode("C",D,null);
		TreeNode B = new TreeNode("B",C,null);
		TreeNode A = new TreeNode("A",B,null);
		System.out.println(doMinus(A));
	}
}