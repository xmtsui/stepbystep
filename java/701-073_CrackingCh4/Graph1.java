/**
 * 邻接矩阵表示方法实现的图
 * Adjacency matrix
 * 
 * BugList:
 * 1.不能动态增加删除顶点
 *
 * @author xmtsui
 * @version v1.0
 */
class Graph1<E> implements Graph<E>{
	private E[] vertex;
	private int[][] adj_matrix;
	private int vertex_num;
	private int edge_num;
	private boolean directed;

	private final static int MAX_VEX = 10;
	private final static int INFINITY = -1;

	/**
	 * 给定定点数与是否是有向图
	 * 
	 * @param  vertex_num [description]
	 * @param  directed   [description]
	 * @return            [description]
	 */
	public Graph1(int vertex_num, boolean directed)
	{
		initGraph();
		this.vertex_num = vertex_num;
		this.directed = directed;
		int[] initial_value = new int[vertex_num];
		for(int i=0; i<vertex_num; ++i)
			for(int j=0; j<vertex_num; ++j)
			{
				if(j == i)
					adj_matrix[i][j] = 0;
				else
					adj_matrix[i][j] = INFINITY;
			}
	}

	/**
	 * 初始化图
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void initGraph(){
		vertex = (E[]) new Object[MAX_VEX];
		adj_matrix = new int[MAX_VEX][MAX_VEX];
	}

	/**
	 * 清空图
	 */
	public void clearGraph(){
		for(int i=0; i<MAX_VEX; ++i)
		{
			vertex[i] = null;
			for(int j=0; j<MAX_VEX; ++j)
			{
				if(j == i)
					adj_matrix[i][j] = 0;
				else
					adj_matrix[i][j] = INFINITY;
			}
		}
	}
	
	/**
	 * 销毁图
	 */
	@Override
	public void destroyGraph(){
		for(int i=0; i<MAX_VEX; ++i)
		{
			vertex[i] = null;
			adj_matrix[i] = null;
		}
		vertex = null;
		adj_matrix = null;
	}

	/**
	 * 遍历
	 */
	public void doTraverse(){
		System.out.println("一般遍历：");
		doTraverseNorm();
		// System.out.println("");
		// doTraverseDFS();
		// System.out.println("");
		// doTraverseBFS();
	}

	/**
	 * 一般遍历，方便测试
	 */
	private void doTraverseNorm(){
		if(vertex == null || adj_matrix == null)
		{
			System.out.println("Vertex or edge null");
			return;
		}
		System.out.print("vertex info:");
		for(E item : vertex)
			System.out.print(item);
		System.out.println("\nedge info:");
		int size = vertex_num;
		for(int i=0; i<size; ++i)
		{
			for(int j=0; j<size; ++j)
			{
				if(adj_matrix[i][j]<0)
					System.out.print(" " + adj_matrix[i][j]);
				else
					System.out.print("  " + adj_matrix[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * 深度优先遍历
	 */
	private void doTraverseDFS(){
		
	}

	/**
	 * 广度优先遍历
	 */
	private void doTraverseBFS(){

	}

	/**
	 * 添加所有顶点
	 * @param  vertex [description]
	 * @return        [description]
	 */
	public boolean addVertexes(E[] vertex)
	{
		if(vertex == null)
		{
			System.out.println("vertex null");
			return false;
		}
		int size = vertex.length;
		if(size > MAX_VEX)
		{
			System.out.println("vertex number max: " + MAX_VEX);
			return false;
		}
		System.arraycopy(vertex, 0, this.vertex, 0, size);
		vertex_num = size;
		return true;
	}

	/**
	 * 添加或者更新边
	 * @param  start  [description]
	 * @param  end    [description]
	 * @param  weight [description]
	 * @return        [description]
	 */
	public boolean addEdge(int start, int end, int weight)
	{
		if(start<0 || start>=vertex_num || end<0 || end>vertex_num)
		{
			System.out.println("index error, should be 0 to " + vertex_num);
			return false;
		}
		if(directed)
			adj_matrix[start][end] = weight;
		else
		{
			adj_matrix[start][end] = weight;
			adj_matrix[end][start] = weight;
		}
		edge_num++;
		return true;
	}

	/**
	 * 测试主函数
	 * @param  args [description]
	 * @return      [description]
	 */
	public static void main(String[] args)
	{
		String[] vertex ={"A","B","C","D","E"};
		Graph1<String> g = new Graph1<String>(vertex.length, false);
		g.addVertexes(vertex);
		g.addEdge(0,4,6);
		g.addEdge(1,0,9);
		g.addEdge(1,2,3);
		g.addEdge(2,0,2);
		g.addEdge(2,3,5);
		g.addEdge(3,4,1);
		g.doTraverse();
		g.clearGraph();
		g.doTraverse();
		g.destroyGraph();
		g.doTraverse();
	}
}