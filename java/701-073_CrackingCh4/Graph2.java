/**
 * 邻接表实现的图
 * Ajacency list
 * 
 * BugList:
 * 1.不能动态增加删除Vertex
 * 
 * @author xmtsui
 * @version v1.0
 */
import java.util.List;
import java.util.ArrayList;
class Graph2<E> implements Graph<E>{
	private List<VertexNode<E>> adj_list;
	private int vertex_num;
	private int edge_num;
	private boolean directed;

	/**
	 * 根据是否是有向图，初始化
	 * @param  directed [description]
	 * @return          [description]
	 */
	public Graph2(boolean directed)
	{
		initGraph();
		this.directed = directed;
	}

	/**
	 * 初始化图
	 */
	@Override
	public void initGraph(){
		adj_list = new ArrayList<VertexNode<E>>();
	}

	/**
	 * 清空图
	 */
	@Override
	public void clearGraph(){
		adj_list.clear();
		vertex_num = 0;
		edge_num = 0;
	}

	/**
	 * 销毁图
	 */
	@Override
	public void destroyGraph(){
		clearGraph();
		adj_list = null;
	}

	/**
	 * 遍历
	 */
	@Override
	public void doTraverse(){
		System.out.println("一般遍历：");
		doTraverseNorm();
		// System.out.println("");
		// doTraverseDFS();
		// System.out.println("");
		// doTraverseBFS();
	}

	/**
	 * 普通遍历，方便测试
	 */
	private void doTraverseNorm(){
		if(adj_list == null)
		{
			System.out.println("null graph");
			return;
		}
		if(vertex_num == 0)
		{
			System.out.println("null vertex");
			return;
		}
		System.out.print("vertex info:");
		for(VertexNode<E> item : adj_list)
			System.out.print(item.element);
		System.out.println("\nedge info:");
		for(VertexNode<E> item : adj_list)
		{
			System.out.print(item.element + "->");
			EdgeNode temp = item.first_edge;
			while(temp != null)
			{
				System.out.print(temp.adj_vertex+"->");
				temp = temp.next_edge;
			}
			System.out.println("^");
		}
	}

	/**
	 * 一次添加所有顶点
	 * @param  vertex [description]
	 * @return        [description]
	 */
	public boolean addVertexes(E[] vertex){
		if(vertex == null)
			return false;
		int size = vertex.length;
		for(int i=0; i<size; ++i)
		{
			adj_list.add(new VertexNode<E>(vertex[i]));
		}
		this.vertex_num = size;
		return true;
	}

	/**
	 * 增加一条边，重复添加（不能像Graph1一样覆盖）
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
		{
			VertexNode<E> node = adj_list.get(start);
			EdgeNode first_edge = node.first_edge;
			if(first_edge == null)
				node.first_edge = new EdgeNode(end, weight);
			else
			{
				EdgeNode temp = new EdgeNode(end, weight);
				while(first_edge.next_edge != null)
					first_edge = first_edge.next_edge;
				first_edge.next_edge = temp;
			}
		}
		else
		{
			VertexNode<E> startnode = adj_list.get(start);
			EdgeNode start_first_edge = startnode.first_edge;
			VertexNode<E> endnode = adj_list.get(end);
			EdgeNode end_first_edge = endnode.first_edge;
			if(start_first_edge == null)
				startnode.first_edge = new EdgeNode(end, weight);
			else
			{
				EdgeNode temp = new EdgeNode(end, weight);
				while(start_first_edge.next_edge != null)
					start_first_edge = start_first_edge.next_edge;
				start_first_edge.next_edge = temp;
			}

			if(end_first_edge == null)
				endnode.first_edge = new EdgeNode(start, weight);
			else
			{
				EdgeNode temp = new EdgeNode(start, weight);
				while(end_first_edge.next_edge != null)
					end_first_edge = end_first_edge.next_edge;
				end_first_edge.next_edge = temp;
			}
		}
		edge_num++;
		return true;
	}

	/**
	 * 顶点节点
	 * 包含顶点信息
	 * 邻接表的第一个节点引用
	 */
	private static class VertexNode<E>{
		E element;
		EdgeNode first_edge;
		VertexNode(){}

		VertexNode(E element)
		{
			this.element = element;
		}
	}

	/**
	 * 边节点
	 *  包含弧头位置(无向图叫边的另一端)，边的权重
	 *  以及下一个邻接点引用
	 */
	private static class EdgeNode{
		int adj_vertex;
		int weight;
		EdgeNode next_edge;
		EdgeNode(int adj_vertex, int weight)
		{
			this.adj_vertex = adj_vertex;
			this.weight = weight;
		}
	}

	/**
	 * 测试主函数
	 * @param  args [description]
	 * @return      [description]
	 */
	public static void main(String[] args)
	{
		String[] vertex ={"A","B","C","D"};
		Graph2<String> g = new Graph2<String>(false);
		g.addVertexes(vertex);
		g.addEdge(0,1,0);
		g.addEdge(0,2,0);
		g.addEdge(0,3,0);
		g.addEdge(1,2,0);
		g.addEdge(2,3,0);
		g.doTraverse();
		g.clearGraph();
		g.doTraverse();
		g.destroyGraph();
		g.doTraverse();
	}
}
