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
abstract class AbstractaArrayTree<E> implements Tree<E>{
	protected AbstractaArrayTree(){
	}
	/**
	 * 构造空树
	 * @param t [description]
	 */
	public abstract void initTree();

	/**
	 * 销毁树
	 * @param t [description]
	 */
	public abstract void destroyTree();
	
	/**
	 * 若树存在，则清空
	 * @param t [description]
	 */
	public abstract void clearTree();

	/**
	 * 判断树是否为空
	 * @return [description]
	 */
	public abstract boolean isTreeEmpty();

	/**
	 * 获取树的最大长度
	 * @return [description]
	 */
	public abstract int getTreeDepth();

	public abstract void doTranverse();		
}