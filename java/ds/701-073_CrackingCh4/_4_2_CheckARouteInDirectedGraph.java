/**
 * Given a directed graph, design an algorithm to find out 
 * whether there is a route between two nodes.
 *
 * solution:
 * This problem can be solved by just simple graph traversal, 
 * We should mark any node found in the course of the algorithm 
 * as ‘already visited’ to avoid cycles and repetition of the nodes.
 * @author xmtsui
 * @version v1.0
 */
import java.util.Deque;
import java.util.LinkedList;
class _4_2_CheckARouteInDirectedGraph{
	public static void main(String[] args)
	{
		String[] vertex ={"A","B","C","D","E","F","G"};
		Graph2<String> g = new Graph2<String>(true);
		g.addVertexes(vertex);
		g.addEdge(1,0,0);
		g.addEdge(0,2,0);
		g.addEdge(0,3,0);
		g.addEdge(1,2,0);
		g.addEdge(2,3,0);
		g.addEdge(3,6,0);
		g.addEdge(2,4,0);
		g.addEdge(4,5,0);
		// g.doTraverse();
		System.out.println(search(g, g.getVertexNode(0), g.getVertexNode(5)));
	}
	
	@SuppressWarnings("unchecked")
	static boolean search(Graph2 g, Graph2.VertexNode<String> start, 
		Graph2.VertexNode<String> end)
	{
		int vertex_num = g.getVertexNum();
		boolean[] visited = new boolean[vertex_num];
		for(int i=0; i<vertex_num; ++i)
		{
			visited[i] = false;
		}
		Deque<Graph2.VertexNode<String>> stack = new LinkedList<Graph2.VertexNode<String>>();
		stack.push(start);
		while(!stack.isEmpty())
		{
			Graph2.VertexNode<String> node = stack.pop();
			if(node != null)
			{
				Graph2.EdgeNode edge = node.first_edge;
				while(edge!=null)
				{
					if(!visited[edge.adj_vertex])
					{
						Graph2.VertexNode<String> tmp = g.getVertexNode(edge.adj_vertex);
						if(tmp == end)
							return true;
						else
							stack.push(tmp);
					}
					edge = edge.next_edge;
				}
			}
			visited[g.getVertexNodeIndex(node)] = true;
		}
		return false;
	}
}