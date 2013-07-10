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
	 * @return 空的话返回true
	 */
	boolean isTreeEmpty();

	/**
	 * 获取树的深度（高度）
	 * @return 空的话返回0
	 */
	int getTreeDepth();
	
	/**
	 * 遍历树
	 */
	void doTranverse();
}