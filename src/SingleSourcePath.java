import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author chenjian on 3/3/21
 */
public class SingleSourcePath
{
    private Graph graph;
    private int source;

    private boolean[] visited;
    private int[] pre;

    public SingleSourcePath(Graph graph, int source)
    {
        this.graph = graph;
        this.graph.validateVertex(source);
        this.source = source;

        visited = new boolean[graph.getV()];
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);

        dfs(source, source);
    }

    private void dfs(int v, int parent)
    {
        visited[v] = true;
        pre[v] = parent;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            }
        }
    }

    public boolean isConnected(int target) {
        graph.validateVertex(target);
        return visited[target];
    }

    public List<Integer> path(int target) {
        if (!isConnected(target)) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        int cur = target;
        while (cur != source) {
           result.add(cur);
           cur = pre[cur];
        }
        result.add(source);

        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args)
    {
//        Graph graph = new Graph("graph.txt");
        Graph graph = new Graph("graph-3.txt");
        SingleSourcePath singleSourcePath = new SingleSourcePath(graph, 0);
        System.out.println("path from 0 to 6: \n" + singleSourcePath.path(6));
    }
}
