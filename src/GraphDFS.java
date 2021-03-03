import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjian on 2/3/21
 */
public class GraphDFS
{
    private Graph graph;
    private boolean[] visited;
    private List<Integer> order = new ArrayList<>();

    public GraphDFS(Graph graph)
    {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];

//        dfs(0); 多个连通分量无法正确遍历
        for (int v = 0; v < graph.getV(); ++v) {
            if (visited[v]) {
                continue;
            }
            dfs(v);
        }
    }

    private void dfs(int v)
    {
        visited[v] = true;
        order.add(v);
        for (int w : graph.adj(v)) {
            if (visited[w]) {
                continue;
            }
            dfs(w);
        }
    }

    public List<Integer> getOrder()
    {
        return order;
    }

    public static void main(String[] args)
    {
        Graph graph = new Graph("graph-2.txt");
        GraphDFS graphDFS = new GraphDFS(graph);
        System.out.println(graphDFS.getOrder());
    }
}
