/**
 * 孩子表示法实现的树
 * 数组存储结构
 *
 * @author xmtsui
 * @version v1.0
 */
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
class Tree2<E> implements Tree<E>{
	private TreeNode<E> root;
	private List<TreeNode<E>> nodes;
	private int count;

	/**
	 * 创建一棵空树
	 */
	public Tree2(){
		initTree();
	}

	/**
	 * 构造函数
	 * 创建一棵空树
	 * 然后创建根结点
	 */
	public Tree2(E element){
		initTree();
		addNode(element, null);
	}

	/*Tree 接口实现*/
	/**
	 * 构造空树
	 */
	@Override
	public void initTree()
	{
		root = null;
		nodes = new ArrayList<TreeNode<E>>();
		count = 0;
	}

	/**
	 * 销毁树
	 */
	@Override
	public void destroyTree()
	{
		clearTree();
		nodes = null;
	}
	
	/**
	 * 若树存在，则清空
	 */
	@Override
	public void clearTree()
	{	
		if(nodes != null)
		{
			for(TreeNode<E> node : nodes)
			{
				SonNode sonNode = node.firstSon;
				while(sonNode != null)
				{
					SonNode tmp = sonNode;
					sonNode = sonNode.next;
					tmp.next = null;
					tmp = null;
				}
				node.firstSon = null;
				node = null;
			}
			nodes.clear();
		}
	}

	/**
	 * 判断树是否为空
	 * @return boolean
	 */
	@Override
	public boolean isTreeEmpty()
	{
		return root == null;
		//return count == 0;
	}

	/**
	 * 获取树的深度（高度）
	 * @return 
	 */
	@Override
	public int getTreeDepth()
	{
		int depth = 0;
		if(nodes == null)
			return 0;
		for(TreeNode<E> node : nodes)
		{
			int tmpdepth = 0;
			int parent = node.parent;
			while(parent != -1)
			{
				parent = node(parent).parent;
				tmpdepth++;
			}
			if(tmpdepth > depth)
				depth = tmpdepth;
		}
		return depth+1;
	}
	
	/**
	 * 遍历树
	 */
	@Override
	public void doTraverse()
	{
		if(nodes != null)
		{
			if(nodes.size() == 0)
				System.out.println("EMPTY TREE");
			else
			{
				for(TreeNode<E> node : nodes)
				{
					System.out.print("value: " + node.value);
					System.out.print(" | parent: " + node.parent);
					System.out.print(" | sons: ");
					SonNode sonNode = node.firstSon;
					while(sonNode != null)
					{
						System.out.print(sonNode.pos+ " ");
						sonNode = sonNode.next;
					}
					System.out.println();
				}
			}
		}
		else
			System.out.println("NULL TREE");
	}

	/* Array Tree 定义*/
	
	/**
	 * 增加一个节点
	 * @param element   [description]
	 * @param parent    [description]
	 * @return 			返回创建的节点
	 */
	public TreeNode<E> addNode(E element, TreeNode<E> parent)
	{
		TreeNode<E> newNode = new TreeNode<E>(element, nodeIndex(parent));
		nodes.add(newNode);
		count++;
		if(parent == null)
			root = newNode;
		return newNode;
	}

	/**
	 * 获取根结点
	 * @return [description]
	 */
	public TreeNode<E> getRoot()
	{
		return node(0);
	}

	/**
	 * 获取某一个节点的双亲节点
	 * @param  node [description]
	 * @return       [description]
	 */
	public TreeNode<E> getParent(TreeNode<E> node)
	{
		return node(node.parent);
	}

	/**
	 * 获取某一个节点的最左儿子节点
	 * @param  node [description]
	 * @return       [description]
	 */
	public TreeNode<E> getFirstSon(TreeNode<E> node)
	{
		return node(node.firstSon.pos);
	}

	/**
	 * 设置节点的儿子链表
	 * @param a [description]
	 * @param b [description]
	 */
	public boolean setSons(TreeNode<E> node, List<TreeNode<E>> sons)
	{
		if(node == null || sons == null)
			return false;

		if(node.firstSon == null)
		{
			if(sons.size() == 0)
				return true;
			node.firstSon = new SonNode(nodeIndex(sons.get(0)), null);
		}

		SonNode runner = node.firstSon;
		sons.remove(0);
		//注意当两边都为null的时候，改变一个引用，另一个不会随着变
		//只有不为null的时候，才会变
		// SonNode runner = firstSon.next;
		for(TreeNode<E> son:sons)
		{
			int sonIndex = nodeIndex(son);
			runner.next = new SonNode(sonIndex, null);
			runner = runner.next;
		}
		return true;
	}

	/**
	 * 获取所有儿子
	 * @param  node [description]
	 * @return       [description]
	 */
	public List<TreeNode<E>> getSons(TreeNode<E> node)
	{
		List<TreeNode<E>> sons = new ArrayList<TreeNode<E>>();
		if(node == null)
			return null;
		SonNode firstSon = node.firstSon;
		while(firstSon != null)
		{
			sons.add(node(firstSon.pos));
			firstSon = firstSon.next;
		}
		return sons;
	}

	/**
	 * 指定一个节点parentNode，增加一个子树childNode
	 * 子树的位置由index指定
	 * @param  parentNode [description]
	 * @param  childNode  [description]
	 * @param  index      [description]
	 * @return            [description]
	 */
	public boolean insertChild(TreeNode<E> parentNode, TreeNode<E> childNode, int index)
	{
		return false;
	}

	/**
	 * 删除一颗子树
	 * @param  node [description]
	 * @return      [description]
	 */
	public boolean deleteChild(TreeNode<E> node)
	{
		return false;
	}

	/*自定义操作*/

	/**
	 * 获取某个位置的节点
	 * @param  index [description]
	 * @return       [description]
	 */
	private TreeNode<E> node(int index)
	{
		if(index<0 || index >= count)
			return null;
		return nodes.get(index);
	}

	/**
	 * 获取某个节点的index
	 * @param  node [description]
	 * @return      [description]
	 */
	private int nodeIndex(TreeNode<E> node)
	{
		if(node == null)
			return -1;
		for(int i=0; i<count; ++i)
		{
			if(node == node(i))
				return i;
		}
		return -1;
	}

	public static class TreeNode<E>{
		E value;//节点的数据域
		int parent;
		SonNode firstSon;//第一个孩子节点的指针

		TreeNode(E value, int parent)
		{
			this.value = value;
			this.parent = parent;
			this.firstSon = null;
		}

		TreeNode(E value, int parent, SonNode firstSon)
		{
			this.value = value;
			this.parent = parent;
			this.firstSon = firstSon;
		}
	}

	private static class SonNode {
		int pos;//孩子节点的位置信息
		SonNode next;//下一个孩子节点的指针

		SonNode(int pos, SonNode next)
		{
			this.pos = pos;
			this.next =next;
		}
	}

	public static void main(String[] args)
	{
		Tree2<String> cat = new Tree2<String>("A");
		TreeNode<String> A = cat.getRoot();
		TreeNode<String> B = cat.addNode("B",A);
		TreeNode<String> C = cat.addNode("C",A);
		TreeNode<String> D = cat.addNode("D",B);
		TreeNode<String> E = cat.addNode("E",C);
		TreeNode<String> F = cat.addNode("F",C);
		TreeNode<String> G = cat.addNode("G",D);
		TreeNode<String> H = cat.addNode("H",D);
		TreeNode<String> I = cat.addNode("I",D);
		TreeNode<String> J = cat.addNode("J",E);
		
		List<TreeNode<String>> sons = new LinkedList<TreeNode<String>>();
		sons.add(B);
		sons.add(C);
		cat.setSons(A,sons);
		sons.clear();
		sons.add(D);
		cat.setSons(B,sons);
		sons.clear();
		sons.add(E);
		sons.add(F);
		cat.setSons(C,sons);
		sons.clear();
		sons.add(G);
		sons.add(H);
		sons.add(I);
		cat.setSons(D,sons);
		sons.clear();
		sons.add(J);
		cat.setSons(E,sons);
		sons.clear();
		cat.setSons(F,sons);
		cat.setSons(G,sons);
		cat.setSons(H,sons);
		cat.setSons(I,sons);
		cat.setSons(J,sons);
		cat.doTraverse();
		System.out.println(cat.getTreeDepth());
		System.out.println(cat.isTreeEmpty());
		List<TreeNode<String>> list = cat.getSons(D);
		for(TreeNode<String> item : list)
		{
			System.out.print(item.value);
		}
		System.out.println();
		cat.clearTree();
		cat.doTraverse();
		cat.destroyTree();
		cat.doTraverse();
	}
}