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
	 * 获取顶点数
	 * @return [description]
	 */
	int getVertexNum();

	/**
	 * 获取边数
	 * @return [description]
	 */
	int getEdgeNum();
	
	/**
	 * 遍历
	 */
	void doTraverse();
}