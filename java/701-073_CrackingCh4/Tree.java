/**
 * Tree ADT
 * 定义树的标准操作
 *
 * @author xmtsui
 * @version v1.0
 */
import java.util.List;
interface Tree<E>{
	/**
	 * 构造空树
	 */
	void initTree();
	
	/**
	 * 销毁树
	 */
	void destroyTree();
	
	/**
	 * 若树存在，则清空
	 */
	void clearTree();

	/**
	 * 判断树是否为空
	 * @return boolean
	 */
	boolean isTreeEmpty();

	/**
	 * 把树的节点按照树的层次编号,从0开始
	 * 对某一个节点增加左儿子
	 * @param  index   [description]
	 * @param  element [description]
	 * @return         成功返回true,失败返回false
	 */
	boolean addLeftChild(int index, E element);

	/**
	 * 把树的节点按照树的层次编号,从0开始
	 * 对该节点增加右兄弟
	 * 该节点必须为其双亲节点的第一个儿子
	 * @param  index    [description]
	 * @param  elements [description]
	 * @return          成功返回true,失败返回false
	 */
	boolean addRightSibling(int index, List<E> elements);

	/**
	 * 获取某一个节点的双亲节点编号
	 * @param  index [description]
	 * @return       [description]
	 */
	int getParent(int index);

	/**
	 * 获取树的最大长度
	 * @return [description]
	 */
	int getTreeDepth();

	/**
	 * 遍历树
	 */
	void doTranverse();
}