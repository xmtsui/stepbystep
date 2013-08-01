/**
 * Graph ADT
 *
 * @author xmtsui
 * @version v1.0
 */
interface Graph<E>{
	/**
	 * 初始化图
	 */
	void initGraph();

	/**
	 * 清空图
	 */
	void clearGraph();

	/**
	 * 销毁图
	 */
	void destroyGraph();

	/**
	 * 遍历
	 */
	void doTraverse();
}