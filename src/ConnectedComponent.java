import java.util.Arrays;

/**
 * @author chenjian on 3/3/21
 */
public class ConnectedComponent
{
    private Graph graph;
    private int[] visited;

    private int count;

    public ConnectedComponent(Graph graph)
    {
        this.count = 0;
        this.graph = graph;
        visited = new int[graph.getV()];
        Arrays.fill(visited,  -1);

        for (int v = 0; v < graph.getV(); ++v) {
            if (visited[v] != -1) {
                continue;
            }
            dfs(v, count);
            ++count;
        }
    }

    private void dfs(int v, int id)
    {
        visited[v] = id;
        for (int w : graph.adj(v)) {
            if (visited[w] != -1) {
                continue;
            }
            dfs(w, id);
        }
    }

    public boolean isConnected(int v, int w) {
        graph.validateVertex(v);
        graph.validateVertex(w);
        return visited[v] == visited[w];
    }

    public int getCount()
    {
        for (int i = 0; i < graph.getV(); ++i) {
            System.out.print(i + " ");
        }
        System.out.println();
        return count;
    }

    public static void main(String[] args)
    {
        Graph graph = new Graph("graph-3.txt");
        ConnectedComponent connectedComponent = new ConnectedComponent(graph);
        System.out.println(connectedComponent.getCount());
        System.out.println(connectedComponent.isConnected(1, 4));
        System.out.println(connectedComponent.isConnected(0, 5));
    }
}
