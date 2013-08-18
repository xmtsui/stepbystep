/**
 * 双亲表示法实现的树
 * 采用线性存储结构
 * 除了双亲，还增加了长子与右兄弟
 * 
 * 可以进行o(1)操作获取某一节点的如下结构：
 * 双亲
 * 最左孩子（长子）
 * 右兄弟
 *
 * 使用方法：
 * 先构造一个带root节点的树
 * 然后按照层次遍历的顺序
 * 对每一个节点依次添加左儿子
 * 对每一个非根节点添加右兄弟
 *
 * buglist:
 * 该实现中把兄弟与右兄弟的概念混淆了
 * 节点定义中把所有右边的兄弟都添加进去了
 * 
 * 应该区分（兄弟）和（右兄弟）以及（堂兄弟）的概念
 * 
 * @author xmtsui
 * @version v1.0
 */
import java.util.List;
import java.util.ArrayList;
class ParentArrayTree<E> extends AbstractArrayTree<E> implements Tree<E>{
	private PTNode root;
	private int count;
	// private PTNode<E>[] nodes;
	// 此种定义方式错误，因为java不支持范型数组
	// 本类的实现暂时没有实现范型编程
	// 有一种解决思路是定义成List,如：
	private List<PTNode<E>> nodes;
	// private PTNode[] nodes;

	/**
	 * 构造函数：构造空树
	 */
	ParentArrayTree(){}

	/**
	 * 构造带有根结点的树
	 * 根的parent为-1
	 * 无儿子节点为-1
	 * 无右兄弟，兄弟列表为null
	 */
	ParentArrayTree(E element)
	{
		initTree();
		if(nodes != null)
			nodes.add(new PTNode<E>(element, -1));
	}

	/*Tree接口实现*/

	/**
	 * 构造空树
	 * @param t [description]
	 */
	public void initTree()
	{
		root = null;
		count = 0;
		nodes = new ArrayList<PTNode<E>>();
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
			for(PTNode<E> node : nodes)
			{
				node.rightSib = null;
				node = null;
			}
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
			for(PTNode<E> node : nodes)
			{
				node.rightSib = null;
				node = null;
			}
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
	 * 把树的节点按照树的层次编号,从0开始
	 * 对某一个节点增加左儿子
	 * @param  index   [description]
	 * @param  element [description]
	 * @return         成功返回true,失败返回false
	 */
	public boolean addLeftChild(int index, E element)
	{
		if(getNode(index) == null)
			return false;
		PTNode<E> node = new PTNode<E>(element, index);
		nodes.add(index+1, node);
		getNode(index).setLeftChild(index+1);
		return true;
	}

	/**
	 * 把树的节点按照树的层次编号,从0开始
	 * 对该节点增加右兄弟
	 * 该节点必须为最左边的孩子（长子）
	 * @param  index    [description]
	 * @param  elements [description]
	 * @return          成功返回true,失败返回false
	 */
	public boolean addRightSibling(int index, List<E> elements)
	{
		//若节点空，则失败
		if(getNode(index) == null)
			return false;
		
		//若为根结点，不能添加右兄弟
		if(getNode(index).getParent() == -1)
			return false;

		//对第一个儿子添加右兄弟
		int number = elements.size();
		int[] siblings = new int[number];
		for(int i=0; i<number; ++i)
		{
			siblings[i] = index + 1 + i;
		}
		getNode(index).setRightSib(siblings);

		//依次对新添加的右兄弟，添加右兄弟
		for(int i=0; i<number; ++i)
		{
			siblings = null;
			int sibling_num = number - i - 1;
			//注意区分
			//int[] a = null;
			//以及
			//int[] a = new int[0];
			if(sibling_num != 0)
				siblings = new int[sibling_num];
			for(int j=0; j<sibling_num; ++j)
			{
				//计算右兄弟的右兄弟
				siblings[j] = index + 1 + (i+1) + j;
			}
			//因为本函数只能操作第一个儿子，所以以下两种计算父节点的方式一样
			PTNode<E> node 
				= new PTNode<E>(elements.get(i), getParentIndex(index), -1, siblings);
			// PTNode<E> node = new PTNode<E>(elements.get(i), index-1, -1, siblings);
			nodes.add(node);
		}
		return true;
	}

	/**
	 * 获取某一个节点的双亲节点编号
	 * @param  index [description]
	 * @return       [description]
	 */
	public int getParentIndex(int index)
	{
		return getNode(index).getParent();
	}


	/**
	 * 获取树的高度(深度)
	 * @return [description]
	 */
	public int getTreeDepth()
	{
		return 0;
	}

	/**
	 * 遍历树
	 */
	public void doTranverse(){
		if(nodes != null)
		{
			for(PTNode<E> node : nodes)
			{
				System.out.print("value: " + node.getValue());
				System.out.print(" | parent: " + node.getParent());
				System.out.print(" | leftchild: " + node.getLeftChild());
				System.out.print(" | rightSib: ");
				int[] sibs = node.getRightSib();
				if(sibs == null)
				{
					System.out.print("null");
				}
				else
				{
					for(int item : node.getRightSib())
						System.out.print(item + " ");
				}
				System.out.println();
			}
		}
		else
			System.out.println("NULL TREE");
	}

	/*自定义操作*/

	/**
	 * 获取某个位置的节点
	 * @param  index [description]
	 * @return       [description]
	 */
	private PTNode<E> getNode(int index)
	{
		return nodes.get(index);
	}

	/**
	 * 节点定义
	 * 可根据需要增加和删除左儿子，右兄弟
	 */
	private static class PTNode<E>{
		private E value;
		private int parent, leftChild;
		private int[] rightSib;
		
		/**
		 * 构造一个叶子节点
		 * 当parent=-1时，为根结点
		 */
		PTNode(E value, int parent)
		{
			this.value = value;
			this.parent = parent;
			this.leftChild = -1;
			this.rightSib = null;
		}

		/**
		 * 构造一个中间节点
		 */
		PTNode(E value, int parent, int leftChild,  int[] rightSib)
		{
			this.value = value;
			this.parent = parent;
			this.leftChild = leftChild;
			this.rightSib = rightSib;
		}

		/**
		 * 设置当前节点的值
		 * @param value [description]
		 * @return      旧的节点值
		 */
		E setValue(E value)
		{
			E oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		/**
		 * 获取当前节点值
		 */
		E getValue()
		{
			return this.value;
		}

		/**
		 * 设置当前节点的父节点
		 * @param  parent [description]
		 * @return        返回旧的父节点index
		 */
		int setParent(int parent)
		{
			int oldParent = this.parent;
			this.parent = parent;
			return oldParent;
		}

		/**
		 * 获取当前节点的父节点
		 * @return [description]
		 */
		int getParent()
		{
			return this.parent;
		}

		/**
		 * 设置当前节点的左儿子
		 * @param  leftChild [description]
		 * @return           返回旧的左儿子节点index
		 */
		int setLeftChild(int leftChild)
		{
			int oldLeftChild = this.leftChild;
			this.leftChild = leftChild;
			return oldLeftChild;
		}

		/**
		 * 获取当前节点的左儿子
		 * @return [description]
		 */
		int getLeftChild()
		{
			return this.leftChild;
		}

		/**
		 * 设置当前节点的右兄弟
		 * @param  leftChild [description]
		 * @return           返回所有旧的右兄弟节点index
		 */
		int[] setRightSib(int[] rightSib)
		{
			int[] oldSib = this.rightSib;
			this.rightSib = rightSib;
			return oldSib;
		}

		/**
		 * 获取当前节点的右兄弟
		 */
		int[] getRightSib()
		{
			return this.rightSib;
		}
	}
	public static void main(String[] args)
	{
		ParentArrayTree<String> pat = new ParentArrayTree<String>("HelloTree");
		System.out.println(pat.isTreeEmpty());
		pat.doTranverse();
		pat.addLeftChild(0,"left child");
		pat.doTranverse();
		List<String> siblings = new ArrayList<String>();
		siblings.add("sib0");
		siblings.add("sib1");
		siblings.add("sib2");
		System.out.println(pat.addRightSibling(0, siblings));
		System.out.println(pat.addRightSibling(1, siblings));
		pat.doTranverse();
	}
}