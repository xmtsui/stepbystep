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
	 * 获取树的深度（高度）
	 * @return 
	 */
	int getTreeDepth();
	
	/**
	 * 遍历树
	 */
	void doTranverse();
}