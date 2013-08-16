/**
 * Given a sorted (increasing order) array,
 * write an algorithm to create a binary tree with minimal height.
 *
 * @author xmtsui
 * @version v1.0
 */
import java.util.Deque;
import java.util.LinkedList;
class _4_3_ArrayToMinimalHeightBiTree{
	private static TreeNode insert(int[] a, int start, int end)
	{
		if(a == null)
			return null;
		if(start>end)
			return null;
		int mid = (start + end)/2;
		TreeNode node = new TreeNode(a[mid]);
		node.left = insert(a, start, mid-1);
		node.right = insert(a, mid+1, end);
		return node;
	}

	/**
	 * 层序遍历
	 * @return [description]
	 */
	private static void layerTrav(TreeNode root)
	{
		Deque<TreeNode> deque = new LinkedList<TreeNode>();
		if(root != null)
			deque.offer(root);
		while(!deque.isEmpty())
		{
			System.out.print(deque.peek().value);
			TreeNode node = deque.poll();
			if(node.left != null)
				deque.offer(node.left);
			if(node.right != null)
				deque.offer(node.right);
		}
	}

	private static class TreeNode{
		int value;
		TreeNode left;
		TreeNode right;
		TreeNode(int value)
		{
			this.value = value;
		}
	}
	public static void main(String[] args)
	{
		int[] a = {1,2,3,4,5,6,7};
		layerTrav(insert(a,0,a.length-1));
		System.out.println();
	}
}