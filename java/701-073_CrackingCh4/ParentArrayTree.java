/**
 * 双亲表示法实现的树
 * 采用线性存储结构
 * 
 * 除了双亲，还增加了长子与右兄弟。
 * 可以进行o(1)操作获取某一节点的如下结构：
 * 双亲，最左孩子（长子），右兄弟
 * 规定:
 * 根的parent为-1
 * 无儿子节点为-1
 * 无右兄弟为-1
 * @author xmtsui
 * @version v1.0
 */
import java.util.List;
import java.util.ArrayList;
class ParentArrayTree<E> extends AbstractArrayTree<E> implements Tree<E>{
	private Node<E> root;
	private int count;
	// private static final int MAX_TREE_SIZE = 30;
	// private Node<E>[] nodes = new Node<E>[MAX_TREE_SIZE];//不可以编译通过
	// private Node<E>[] nodes = new Node[MAX_TREE_SIZE];//可以编译通过
	// 但此种定义方式错误，因为java不支持范型数组，容易造成不安全的访问
	// 有一种解决思路是定义成List,如：
	private List<Node<E>> nodes;

	/**
	 * 构造函数
	 * 创建一棵树并创建根结点
	 */
	ParentArrayTree(E element){
		initTree();
		addNode(element, null);
	}

	/*Tree接口实现*/

	/**
	 * 构造空树
	 */
	public void initTree()
	{
		root = null;
		count = 0;
		nodes = new ArrayList<Node<E>>();
	}

	/**
	 * 销毁树
	 * @param t [description]
	 */
	public void destroyTree()
	{
		root = null;
		count = 0;
		if(nodes != null)
		{
			for(Node<E> node : nodes)
				node = null;
		}
		nodes = null;
	}
	
	/**
	 * 若树存在，则清空
	 * @param t [description]
	 */
	public void clearTree()
	{
		root = null;
		count = 0;
		if(nodes != null)
		{
			for(Node<E> node : nodes)
				node = null;
		}
	}

	/**
	 * 判断树是否为空
	 * @return [description]
	 */
	public boolean isTreeEmpty(){
		return root == null;
		//return count == 0;
	}

	/**
	 * 获取树的高度(深度)
	 * @return [description]
	 */
	public int getTreeDepth()
	{
		int depth = 0;
		for(int i=0; i<count && node(i)!=null; ++i)
		{
			int tmpdepth = 0;
			int runner=i;
			while(runner != -1 && node(runner) != null)
			{
				runner = node(runner).getParent();
				tmpdepth++;
			}
			if(tmpdepth>depth)
				depth = tmpdepth;
		}
		return depth;
	}

	/**
	 * 遍历树
	 */
	public void doTranverse(){
		if(nodes != null)
		{
			for(Node<E> node : nodes)
			{
				System.out.print("value: " + node.getValue());
				System.out.print(" | parent: " + node.getParent());
				System.out.print(" | leftchild: " + node.getLeftChild());
				System.out.print(" | rightSib: " + node.getRightSibling());
				System.out.println();
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
	public Node<E> addNode(E element, Node<E> parent)
	{
		Node<E> node = new Node<E>(element, nodeIndex(parent));
		if(parent == null)
		{
			root = node;
			root.setRightSibling(-1);
		}
		nodes.add(node);
		count++;
		return node;
	}

	/**
	 * 获取根结点
	 * @return [description]
	 */
	public Node<E> getRoot()
	{
		return node(0);
	}

	/**
	 * 获取某一个节点的双亲节点
	 * @param  index [description]
	 * @return       [description]
	 */
	public Node<E> getParent(Node<E> node)
	{
		return node(node.getParent());
	}

	/**
	 * 设置某个节点的最左儿子
	 * @param a [description]
	 * @param b [description]
	 */
	public void setLeftChild(Node<E> a, Node<E> b)
	{
		a.setLeftChild(nodeIndex(b));
	}

	/**
	 * 获取最左儿子节点
	 * @param  index [description]
	 * @return       [description]
	 */
	public Node<E> getLeftChild(Node<E> node)
	{
		return node(node.getLeftChild());
	}

	/**
	 * 设置节点的右兄弟
	 * @param a [description]
	 * @param b [description]
	 */
	public void setRightSibling(Node<E> a, Node<E> b)
	{
		a.setRightSibling(nodeIndex(b));
	}

	/**
	 * 若右兄弟存在，则返回该节点
	 * 不存在则返回-1
	 * @param  index [description]
	 * @return       [description]
	 */
	public Node<E> getRightSibling(Node<E> node)
	{
		return node(node.getRightSibling());
	}

	/**
	 * 指定一个节点parentNode，增加一个子树childNode
	 * 子树的位置由index指定
	 * @param  parentNode [description]
	 * @param  childNode  [description]
	 * @param  index      [description]
	 * @return            [description]
	 */
	public boolean insertChild(Node<E> parentNode, Node<E> childNode, int index)
	{
		return true;
	}

	/**
	 * 删除一颗子树
	 * @param  node [description]
	 * @return      [description]
	 */
	public boolean deleteChild(Node<E> node)
	{
		return true;
	}

	/*自定义操作*/

	/**
	 * 获取某个位置的节点
	 * @param  index [description]
	 * @return       [description]
	 */
	private Node<E> node(int index)
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
	private int nodeIndex(Node<E> node)
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


	/**
	 * 测试主函数
	 * @param  args [description]
	 * @return      [description]
	 */
	public static void main(String[] args)
	{
		ParentArrayTree<String> pat = new ParentArrayTree<String>("A");
		Node<String> A = pat.getRoot();
		Node<String> B = pat.addNode("B",A);
		Node<String> C = pat.addNode("C",A);
		Node<String> D = pat.addNode("D",B);
		Node<String> E = pat.addNode("E",C);
		Node<String> F = pat.addNode("F",C);
		Node<String> G = pat.addNode("G",D);
		Node<String> H = pat.addNode("H",D);
		Node<String> I = pat.addNode("I",D);
		Node<String> J = pat.addNode("J",E);
		
		pat.setLeftChild(A,B);
		pat.setLeftChild(B,D);
		pat.setLeftChild(C,E);
		pat.setLeftChild(D,G);
		pat.setLeftChild(E,J);
		pat.setLeftChild(F,null);
		pat.setLeftChild(G,null);
		pat.setLeftChild(H,null);
		pat.setLeftChild(I,null);
		pat.setLeftChild(J,null);

		pat.setRightSibling(B,C);
		pat.setRightSibling(C,null);
		pat.setRightSibling(D,null);
		pat.setRightSibling(E,F);
		pat.setRightSibling(F,null);
		pat.setRightSibling(G,H);
		pat.setRightSibling(H,I);
		pat.setRightSibling(I,null);
		pat.setRightSibling(J,null);

		pat.doTranverse();
		Node<String> tmp = pat.getLeftChild(B);
		if(tmp != null)
			System.out.println(tmp.getValue());

		System.out.println(pat.getTreeDepth());
	}
}