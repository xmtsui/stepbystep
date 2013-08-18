/**
 * Given a binary search tree, design an algorithm which creates a linked list 
 * of all the nodes at each depth (eg, if you have a tree with depth D, you’ll 
 * have D linked lists).
 *
 * @author xmtsui
 * @version v1.0
 */
import java.util.*;
class _4_4_BFSWithLevel{
	static <E> ArrayList<LinkedList<BinaryTree2.TreeNode<E>>> bfsWithLevel(BinaryTree2.TreeNode<E> node)
	{
		int level = 0;
		ArrayList<LinkedList<BinaryTree2.TreeNode<E>>> result = new ArrayList<LinkedList<BinaryTree2.TreeNode<E>>>();
		LinkedList<BinaryTree2.TreeNode<E>> list = new LinkedList<BinaryTree2.TreeNode<E>>();
		if(node != null)
			list.add(node);
		result.add(0, list);
		while(true)
		{
			list = new LinkedList<BinaryTree2.TreeNode<E>>();
			for(int i=0; i<result.get(level).size(); ++i)
			{
				BinaryTree2.TreeNode<E> tmp = result.get(level).get(i);
				if(tmp != null)
				{
					if(tmp.leftChild != null)
						list.add(tmp.leftChild);
					if(tmp.rightChild != null)
						list.add(tmp.rightChild);
				}
			}
			if(list.size() > 0)
				result.add(level+1, list);
			else
				break;
			level++;
		}
		return result;
	}
	public static void main(String[] args)
	{
		BinaryTree2<String> tree = new BinaryTree2<String>("A");
		BinaryTree2.TreeNode<String> B = tree.addNode(tree.getRoot(), "B", true);
		BinaryTree2.TreeNode<String> C = tree.addNode(tree.getRoot(), "C", false);
		tree.addNode(B, "D", true);
		tree.addNode(C, "E", true);
		tree.doTraverse();
		System.out.println("Tree depth: " + tree.getTreeDepth());
		ArrayList<LinkedList<BinaryTree2.TreeNode<String>>> list 
			= _4_4_BFSWithLevel.<String>bfsWithLevel(tree.getRoot());
		int level=0;
		for(LinkedList<BinaryTree2.TreeNode<String>> linkl : list)
		{
			System.out.println("第" + ++level + "层");
			for(BinaryTree2.TreeNode<String> node : linkl)
			{
				System.out.println(node.value);
			}
		}
	}	
}