/**
 * @author chenjian on 3/3/21
 */
public class ConnectedComponent
{
    private Graph graph;
    private boolean[] visited;

    private int count;

    public ConnectedComponent(Graph graph)
    {
        this.count = 0;
        this.graph = graph;
        visited = new boolean[graph.getV()];

        for (int v = 0; v < graph.getV(); ++v) {
            if (visited[v]) {
                continue;
            }
            ++count;
            dfs(v);
        }
    }

    private void dfs(int v)
    {
        visited[v] = true;
        for (int w : graph.adj(v)) {
            if (visited[w]) {
                continue;
            }
            dfs(w);
        }
    }

    public int getCount()
    {
        return count;
    }

    public static void main(String[] args)
    {
        Graph graph = new Graph("graph-3.txt");
        ConnectedComponent connectedComponent = new ConnectedComponent(graph);
        System.out.println(connectedComponent.getCount());
    }
}
