/**
 * 链式存储结构实现的二叉树
 * 
 * @author xmtusi
 * @version v1.0
 */
import java.util.Deque;
import java.util.LinkedList;
class BinaryTree2<E> implements Tree<E>{

	private TreeNode<E> root;

	/**
	 * 构造空树
	 */
	private BinaryTree2()
	{
		initTree();
	}

	/**
	 * 指定根节点构造
	 */
	BinaryTree2(E element)
	{
		initTree();
		root.value = element;
	}

	/**
	 * 构造空树
	 */
	@Override
	public void initTree()
	{
		root = new TreeNode<E>();
	}

	/**
	 * 销毁树
	 */
	@Override
	public void destroyTree()
	{
		clearTree();
		root = null;
	}
	
	/**
	 * 若树存在，则清空
	 */
	@Override
	public void clearTree()
	{
		clearTree(root);
		//此处容易忽略root，如果不处理root，会导致内存泄漏
		root = null;
	}

	/**
	 * 从某个节点开始递归删除
	 * @param node [description]
	 */
	private void clearTree(TreeNode<E> node)
	{
		if(node != null)
		{
			clearTree(node.leftChild);
			clearTree(node.rightChild);
			node.leftChild = null;
			node.rightChild = null;
			node = null;
		}
	}

	/**
	 * 判断树是否为空
	 * @return 空的话返回true
	 */
	@Override
	public boolean isTreeEmpty()
	{
		return root == null;
	}

	/**
	 * 获取树的深度（高度）
	 * @return 空的话返回0
	 */
	@Override
	public int getTreeDepth()
	{
		return getTreeDepth(root);
	}

	/**
	 * 递归方式求树的深度
	 * @param  node [description]
	 * @return      [description]
	 */
	private int getTreeDepth(TreeNode<E> node)
	{
		if(node == null)
			return 0;
		if(node.rightChild == null && node. leftChild == null)
			return 1;
		else
		{
			int d1 = getTreeDepth(node.leftChild);
			int d2 = getTreeDepth(node.rightChild);
			int max = d1 > d2 ? d1 : d2;
			return max+1;
		}
	}
	
	/**
	 * 遍历树
	 */
	@Override
	public void doTraverse()
	{
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

	public TreeNode<E> getRoot()
	{
		return root;
	}

	public TreeNode<E> addNode(TreeNode<E> node, E element, boolean left)
	{
		if(node == null)
		{
			System.out.println("null node cant be used");
			System.exit(1);
		}
		if(node.leftChild != null && left)
		{
			System.out.println("left already existed");
			System.exit(1);
		}
		if(node.rightChild != null && !left)
		{
			System.out.println("left already existed");
			System.exit(1);
		}
		if(left)
		{
			return node.leftChild = new TreeNode<E>(element, null, null);
		}
		else
		{
			return node.rightChild = new TreeNode<E>(element, null, null);
		}
	}

	/**
	 * 二叉树节点定义
	 */
	public static class TreeNode<E>{
		E value;
		TreeNode<E> leftChild, rightChild;

		TreeNode(){}

		TreeNode(E value)
		{
			this.value = value;
		}

		TreeNode(E value, TreeNode<E> leftChild, TreeNode<E> rightChild)
		{
			this.value = value;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
	}

	/**
	 * 测试主函数
	 * @param  args [description]
	 * @return      [description]
	 */
	public static void main(String[] args)
	{
		BinaryTree2<String> tree = new BinaryTree2<String>("A");
		TreeNode<String> B = tree.addNode(tree.getRoot(), "B", true);
		TreeNode<String> C = tree.addNode(tree.getRoot(), "C", false);
		tree.addNode(B, "D", true);
		tree.addNode(C, "E", true);
		tree.doTraverse();
		System.out.println("Tree depth: " + tree.getTreeDepth());
		tree.clearTree();
		tree.doTraverse();
	}
}