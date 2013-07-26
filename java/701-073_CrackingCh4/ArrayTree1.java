/**
 * 双亲表示法实现的树 
 * 采用线性存储结构
 * 
 * 除了双亲，还增加了长子与右兄弟。
 * 优点：可以进行o(1)操作获取某一节点的如下结构－双亲，最左孩子（长子），右兄弟
 * 缺点：构造麻烦，添加／删除麻烦
 * 
 * 规定:
 * 根的parent为-1
 * 无儿子节点为-1
 * 无右兄弟为-1
 * @author xmtsui
 * @version v1.0
 */
import java.util.List;
import java.util.Deque;
import java.util.ArrayList;
import java.util.LinkedList;
class ArrayTree1<E> implements Tree<E>{
	private TreeNode<E> root;
	private int count;
	// private static final int MAX_TREE_SIZE = 30;
	// private TreeNode<E>[] nodes = new TreeNode<E>[MAX_TREE_SIZE];//不可以编译通过
	// private TreeNode<E>[] nodes = new TreeNode[MAX_TREE_SIZE];//可以编译通过
	// 但此种定义方式错误，因为java不支持范型数组，容易造成不安全的访问
	// 有一种解决思路是定义成List,如：
	private List<TreeNode<E>> nodes;

	/**
	 * 空的构造函数
	 * 创建一棵空树
	 */
	ArrayTree1(){
		initTree();
	}

	/**
	 * 构造函数
	 * 创建一棵空树
	 * 然后创建根结点
	 */
	ArrayTree1(E element){
		initTree();
		addNode(element, null);
	}

	/*Tree接口实现*/

	/**
	 * 构造空树
	 */
	
	@Override
	public void initTree()
	{
		root = null;
		count = 0;
		nodes = new ArrayList<TreeNode<E>>();
	}

	/**
	 * 销毁树
	 * @param t [description]
	 */
	@Override
	public void destroyTree()
	{
		clearTree();
		nodes = null;
	}
	
	/**
	 * 若树存在，则清空
	 * @param t [description]
	 */
	@Override
	public void clearTree()
	{
		root = null;
		count = 0;
		if(nodes != null)
		{
			for(TreeNode<E> node : nodes)
				node = null;
			nodes.clear();
		}
	}

	/**
	 * 判断树是否为空
	 * @return [description]
	 */
	@Override
	public boolean isTreeEmpty(){
		return root == null;
		//return count == 0;
	}

	/**
	 * 获取树的高度(深度)
	 * @return [description]
	 */
	@Override
	public int getTreeDepth()
	{
		int depth = 0;
		for(int i=0; i<count && node(i)!=null; ++i)
		{
			int tmpdepth = 0;
			int runner=node(i).parent;
			while(runner != -1 && node(runner) != null)
			{
				runner = node(runner).getParent();
				tmpdepth++;
			}
			if(tmpdepth>depth)
				depth = tmpdepth;
		}
		return depth+1;
	}

	/**
	 * 根据内存结构遍历树
	 */
	@Override
	public void doTraverse(){
		System.out.println("----start trav-----");
		System.out.print("norm trav: ");
		if(nodes != null)
		{
			if(nodes.size() == 0)
				System.out.println("EMPTY TREE");
			else
			{
				for(TreeNode<E> node : nodes)
				{
					System.out.print("value: " + node.getValue());
					System.out.print(" | parent: " + node.getParent());
					System.out.print(" | leftChild: " + node.getLeftChild());
					System.out.print(" | rightSib: " + node.getRightSibling());
					System.out.println();
				}
			}
		}
		else
			System.out.println("NULL TREE");

		System.out.print("pre   order T: ");
		preTrav(root,true);
		System.out.print("\npre   order F: ");
		preTrav(root,false);
		System.out.println();

		System.out.print("in    order T: ");
		inTrav(root,true);
		System.out.print("\nin    order F: ");
		inTrav(root,false);
		System.out.println();

		System.out.print("post  order T: ");
		postTrav(root,true);
		System.out.print("\npost  order F: ");
		postTrav(root,false);
		System.out.println();

		System.out.print("layer order  : ");
		layerTrav(root);
		System.out.println();
		System.out.println("----end trav-----");
	}

	/**
	 * 递归实现前序遍历
	 * @param node [description]
	 */
	private void preTrav(TreeNode<E> node)
	{
		if(node==null)
			return;
		if(node != null)
			System.out.print(node.getValue());
		int leftChild = node.getLeftChild();
		if(leftChild != -1)
			preTrav(node(leftChild));
		List<TreeNode<E>> list = getAllRightChildren(node);
		if(list.size() != 0)
		{
			for(TreeNode<E> item:list)
			{
				preTrav(item);
			}
		}
	}

	/**
	 * 选择是否使用递归的方式前序遍历
	 * @param node      [description]
	 * @param recursive [description]
	 */
	public void preTrav(TreeNode<E> node, boolean recursive)
	{
		if(recursive)
			preTrav(node);
		else
		{
			Deque<TreeNode<E>> stack = new LinkedList<TreeNode<E>>();
			if(node==null)
				return;
			if(node != null)
				stack.push(node);
			while(!stack.isEmpty())
			{
				System.out.print(stack.peek().getValue());
				TreeNode<E> tmpnode = stack.pop();
				//右边先进栈
				//注意所有右儿子的顺序
				List<TreeNode<E>> list = getAllRightChildren(tmpnode);
				int size = list.size();
				if(size != 0)
				{

					for(int i=size-1; i>=0; --i)
					{
						stack.push(list.get(i));
					}
				}
				
				int leftChinldIndex = tmpnode.getLeftChild();
				if(leftChinldIndex != -1)
				{
					stack.push(node(leftChinldIndex));
				}
			}
		}
	}

	/**
	 * 中序遍历
	 * @param node [description]
	 */
	public void inTrav(TreeNode<E> node)
	{
		if(node==null)
			return;
		int leftChild = node.getLeftChild();
		if(leftChild != -1)
			inTrav(node(leftChild));
		if(node != null)
			System.out.print(node.getValue());
		List<TreeNode<E>> list = getAllRightChildren(node);
		if(list.size() != 0)
		{
			for(TreeNode<E> item:list)
			{
				inTrav(item);
			}
		}
	}

	/**
	 * 选择是否使用递归的方式中序遍历
	 * @param node      [description]
	 * @param recursive [description]
	 */
	public void inTrav(TreeNode<E> node, boolean recursive)
	{
		if(recursive)
			inTrav(node);
		else
		{
			Deque<TreeNode<E>> stack = new LinkedList<TreeNode<E>>();
			if(node==null)
				return;
			while(node != null)
			{
				while(node != null)
				{
					List<TreeNode<E>> list = getAllRightChildren(node);
					int size = list.size();
					for(int i=size-1; i>=0; --i)
						stack.push(list.get(i));
					stack.push(node);
					int leftChinldIndex = node.getLeftChild();
					if(leftChinldIndex != -1)
						node = node(leftChinldIndex);
					else
						node = null;
				}
				node = stack.pop();
				while(!stack.isEmpty() && getAllRightChildren(node).size() == 0)
				{
					System.out.print(node.getValue());
					node = stack.pop();
				}
				System.out.print(node.getValue());
				if(!stack.isEmpty())
				{
					node = stack.pop();
				}
				else
					node = null;
			}
		}
	}

	/**
	 * 后序遍历
	 * @param node [description]
	 */
	public void postTrav(TreeNode<E> node)
	{
		if(node==null)
			return;
		int leftChild = node.getLeftChild();
		if(leftChild != -1)
			postTrav(node(leftChild));
		List<TreeNode<E>> list = getAllRightChildren(node);
		if(list.size() != 0)
		{
			for(TreeNode<E> item:list)
			{
				postTrav(item);
			}
		}	
		if(node != null)
			System.out.print(node.getValue());
	}

	/**
	 * 选择是否使用递归的方式后序遍历
	 * @param node      [description]
	 * @param recursive [description]
	 */
	public void postTrav(TreeNode<E> node, boolean recursive)
	{
		if(recursive)
			postTrav(node);
		else
		{
			postTrav(node);
		}
	}
	/**
	 * 层序遍历
	 */
	public void layerTrav(TreeNode<E> root)
	{
		Deque<TreeNode<E>> queue = new LinkedList<TreeNode<E>>();
		if(root != null)
			queue.offer(root);
		while(!queue.isEmpty())
		{
			System.out.print(queue.peek().getValue());
			TreeNode<E> tmpnode = queue.poll();
			List<TreeNode<E>> list = getAllChildren(tmpnode);
			if(list.size() != 0)
			{
				for(TreeNode<E> item : list)
					queue.offer(item);
			}
		}
	}

	/* Array Tree 定义*/

	/**
	 * 
	 */
	public TreeNode<E> addNode(E element)
	{
		TreeNode<E> node = new TreeNode<E>(element);
		if(root == null)
		{
			root = node;
			root.setRightSibling(-1);
		}
		nodes.add(node);
		count++;
		return node;
	}

	/**
	 * 增加一个节点，并指定父节点
	 * @param element   [description]
	 * @param parent    [description]
	 * @return 			返回创建的节点
	 */
	public TreeNode<E> addNode(E element, TreeNode<E> parent)
	{
		TreeNode<E> node = new TreeNode<E>(element, nodeIndex(parent));
		if(root == null)
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
	public TreeNode<E> getRoot()
	{
		if(root == null)
			return null;
		return node(0);
	}

	/**
	 * 获取某一个节点的双亲节点
	 * @param  index [description]
	 * @return       [description]
	 */
	public TreeNode<E> getParent(TreeNode<E> node)
	{
		return node(node.getParent());
	}

	/**
	 * 设置某个节点的最左儿子
	 * @param a [description]
	 * @param b [description]
	 */
	public void setLeftChild(TreeNode<E> a, TreeNode<E> b)
	{
		a.setLeftChild(nodeIndex(b));
	}

	/**
	 * 获取最左儿子节点
	 * @param  index [description]
	 * @return       [description]
	 */
	public TreeNode<E> getLeftChild(TreeNode<E> node)
	{
		return node(node.getLeftChild());
	}

	/**
	 * 设置节点的右兄弟
	 * @param a [description]
	 * @param b [description]
	 */
	public void setRightSibling(TreeNode<E> a, TreeNode<E> b)
	{
		a.setRightSibling(nodeIndex(b));
	}

	/**
	 * 若右兄弟存在，则返回该节点
	 * 不存在则返回-1
	 * @param  index [description]
	 * @return       [description]
	 */
	public TreeNode<E> getRightSibling(TreeNode<E> node)
	{
		return node(node.getRightSibling());
	}

	/**
	 * 获取某一个节点的所有子节点
	 * @param  node [description]
	 * @return      [description]
	 */
	public List<TreeNode<E>> getAllChildren(TreeNode<E> node)
	{
		List<TreeNode<E>> list = new ArrayList<TreeNode<E>>();
		if(node == null)
			return null;
		for(int i=0; i<count; ++i)
		{
			TreeNode<E> runnerNode = node(i);
			TreeNode<E> parent = node(runnerNode.getParent());
			if(parent != null && parent == node)
				list.add(runnerNode);
		}
		return list;
	}

	public List<TreeNode<E>> getAllRightChildren(TreeNode<E> node)
	{
		List<TreeNode<E>> list = new ArrayList<TreeNode<E>>();
		int leftChildIndex = node.getLeftChild();
		if(node == null)
			return null;
		for(int i=0; i<count; ++i)
		{
			if(leftChildIndex == i)
				continue;
			TreeNode<E> runnerNode = node(i);
			TreeNode<E> parent = node(runnerNode.getParent());
			if(parent != null && parent == node)
				list.add(runnerNode);
		}
		return list;	
	}
	/**
	 * ！！！尚未实现
	 * 指定一个节点parentNode，增加一个子树childNode
	 * 子树的位置由index指定
	 * @param  parentNode [description]
	 * @param  childNode  [description]
	 * @param  index      [description]
	 * @return            [description]
	 */
	//public boolean insertChild(TreeNode<E> parentNode, TreeNode<E> childNode, int index)
	/**
	 * 指定一个节点parentNode，增加一个子节点childNode
	 * @param  parentNode [description]
	 * @param  childNode  [description]
	 * @param  index      [description]
	 * @return            [description]
	 */
	public boolean insertChild(TreeNode<E> parentNode, E element)
	{
		//获取父亲节点的位置
		int parentNodeIndex = nodeIndex(parentNode);
		//创建新节点
		TreeNode<E> childNode = new TreeNode<E>(element, parentNodeIndex);
		//在存储结构中增加该节点
		if(childNode != null)
		{
			nodes.add(childNode);
			count++;
		}
		else
			return false;
		//获取要插入节点的位置
		int childNodeIndex = nodeIndex(childNode);
		//若被插入的节点没有孩子，则直接插入
		//若被插入的节点有孩子，则在最后一个孩子之后的位置插入
		if(parentNode.getLeftChild() == -1)
		{
			parentNode.setLeftChild(childNodeIndex);
			childNode.setParent(parentNodeIndex);
			return true;
		}
		else
		{

			TreeNode<E> leftChild = node(parentNode.getLeftChild());
			//若被插入的节点的左孩子没有右兄弟，则直接插入
			//若有，则找到最后一个孩子
			if(leftChild.getRightSibling() == -1)
			{
				leftChild.setRightSibling(childNodeIndex);
				childNode.setParent(parentNodeIndex);
			}
			else
			{
				TreeNode<E> rightRunner = node(leftChild.getRightSibling());
				while(rightRunner.getRightSibling() != -1 && rightRunner != null)
					rightRunner = node(rightRunner.getRightSibling());
				rightRunner.setRightSibling(childNodeIndex);
			}
		}
		return true;
	}

	/**
	 * ！！！尚未实现
	 * 删除一颗子树
	 * @param  node [description]
	 * @return      [description]
	 */
	public boolean deleteChild(TreeNode<E> node)
	{
		return true;
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
	
	/**
	 * 节点定义
	 * 可根据需要增加和删除左儿子，右兄弟
	 */
	public static class TreeNode<E>{
		private E value;
		private int parent, leftChild, rightSib;
		
		TreeNode(E value)
		{
			this.value = value;
			this.parent = -1;
			this.leftChild = -1;
			this.rightSib = -1;
		}
		/**
		 * 构造一个指定了父节点的新节点
		 */
		TreeNode(E value, int parent)
		{
			this.value = value;
			this.parent = parent;
			this.leftChild = -1;
			this.rightSib = -1;
		}

		/**
		 * 设置当前节点的值
		 * @param value [description]
		 */
		public void setValue(E value)
		{
			this.value = value;
		}

		/**
		 * 获取当前节点值
		 */
		public E getValue()
		{
			return this.value;
		}

		public void setParent(int parent)
		{
			this.parent = parent;
		} 

		public int getParent()
		{
			return parent;
		}

		public void setLeftChild(int leftChild)
		{
			this.leftChild = leftChild;
		}

		public int getLeftChild()
		{
			return leftChild;
		}

		public void setRightSibling(int rightSib)
		{
			this.rightSib = rightSib;
		}

		public int getRightSibling()
		{
			return rightSib;
		}
	}

	/**
	 * 测试主函数
	 * @param  args [description]
	 * @return      [description]
	 */
	public static void main(String[] args)
	{
		ArrayTree1<String> pat = new ArrayTree1<String>("A");
		TreeNode<String> A = pat.getRoot();
		TreeNode<String> B = pat.addNode("B",A);
		TreeNode<String> C = pat.addNode("C",A);
		TreeNode<String> D = pat.addNode("D",B);
		TreeNode<String> E = pat.addNode("E",C);
		TreeNode<String> F = pat.addNode("F",C);
		TreeNode<String> G = pat.addNode("G",D);
		TreeNode<String> H = pat.addNode("H",D);
		TreeNode<String> I = pat.addNode("I",D);
		TreeNode<String> J = pat.addNode("J",E);

		pat.setLeftChild(A,B);
		pat.setLeftChild(B,D);
		pat.setRightSibling(B,C);
		pat.setRightSibling(C,null);
		pat.setLeftChild(C,E);
		pat.setRightSibling(D,null);
		pat.setLeftChild(D,G);
		pat.setLeftChild(E,J);
		pat.setRightSibling(E,F);
		pat.setLeftChild(F,null);
		pat.setRightSibling(F,null);
		pat.setLeftChild(G,null);
		pat.setRightSibling(G,H);
		pat.setLeftChild(H,null);
		pat.setRightSibling(H,I);
		pat.setLeftChild(I,null);
		pat.setRightSibling(I,null);
		pat.setLeftChild(J,null);
		pat.setRightSibling(J,null);

		pat.insertChild(D, "Z");

		TreeNode<String> tmp = pat.getLeftChild(B);
		if(tmp != null)
			System.out.println(tmp.getValue());

		System.out.println(pat.getTreeDepth());

		List<TreeNode<String>> list = pat.getAllChildren(D);
		// List<TreeNode<String>> list = pat.getAllRightChildren(D);
		for(TreeNode<String> item : list)
			System.out.print(item.getValue());
		System.out.println();

		pat.doTraverse();

		pat.clearTree();
		pat.doTraverse();
		pat.destroyTree();
		pat.doTraverse();
	}
}