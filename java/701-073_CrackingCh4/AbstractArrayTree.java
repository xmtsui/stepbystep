/**
 * 线性存储结构实现Tree
 * 
 * 双亲表示实现(ParentArrayTree.java)
 * 	快速获取某一个节点的父亲节点
 * 双亲加最左孩子，以及最左孩子的右兄弟实现(t.java) 
 * 	快速获取某一节点的父亲节点以及孩子节点
 * 	@author xmtsui
 * 	@version v1.0
 */
import java.util.List;
abstract class AbstractArrayTree<E> implements Tree<E>{
	protected AbstractArrayTree(){
	}
	/**
	 * 构造空树
	 * @return [description]
	 */
	public abstract void initTree();

	/**
	 * 销毁树
	 * @return [description]
	 */
	public abstract void destroyTree();
	
	/**
	 * 若树存在，则清空
	 * @return [description]
	 */
	public abstract void clearTree();

	/**
	 * 判断树是否为空
	 * @return [description]
	 */
	public abstract boolean isTreeEmpty();

	/**
	 * 把树的节点按照树的层次编号,从0开始
	 * 对某一个节点增加左儿子
	 * @param  index   [description]
	 * @param  element [description]
	 * @return         成功返回true,失败返回false
	 */
	public abstract boolean addLeftChild(int index, E element);

	/**
	 * 把树的节点按照树的层次编号,从0开始
	 * 对该节点增加右兄弟
	 * 该节点必须为其双亲节点的第一个儿子
	 * @param  index    [description]
	 * @param  elements [description]
	 * @return          成功返回true,失败返回false
	 */
	public abstract boolean addRightSibling(int index, List<E> elements);

	/**
	 * 获取某一个节点的双亲节点编号
	 * @param  index [description]
	 * @return       [description]
	 */
	public abstract int getParent(int index);

	/**
	 * 获取树的最大长度
	 * @return [description]
	 */
	public abstract int getTreeDepth();

	/**
	 * 遍历树
	 * @return [description]
	 */
	public abstract void doTranverse();		
}