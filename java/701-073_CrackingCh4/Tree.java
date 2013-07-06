/**
 * Tree ADT
 * 定义树的标准操作
 *
 * @author xmtsui
 * @version v1.0
 */
interface Tree<E>{
	/**
	 * 构造空树
	 * @param t [description]
	 */
	void initTree();
	
	/**
	 * 销毁树
	 * @param t [description]
	 */
	void destroyTree();
	
	/**
	 * 若树存在，则清空
	 * @param t [description]
	 */
	void clearTree();

	/**
	 * 判断树是否为空
	 * @return [description]
	 */
	boolean isTreeEmpty();

	/**
	 * 获取树的最大长度
	 * @return [description]
	 */
	int getTreeDepth();

	void doTranverse();
}