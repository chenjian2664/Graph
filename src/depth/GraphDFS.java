package depth;

import common.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjian on 2/3/21
 */
public class GraphDFS
{
    private Graph graph;
    private boolean[] visited;
    private List<Integer> pre = new ArrayList<>();
    private List<Integer> post = new ArrayList<>();

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
        pre.add(v);
        for (int w : graph.adj(v)) {
            if (visited[w]) {
                continue;
            }
            dfs(w);
        }
        post.add(v);
    }

    public List<Integer> getPre()
    {
        return pre;
    }

    public List<Integer> getPost()
    {
        return post;
    }

    public static void main(String[] args)
    {
        Graph graph = new Graph("graph-2.txt");
        GraphDFS graphDFS = new GraphDFS(graph);
        System.out.println(graphDFS.getPre());
        System.out.println(graphDFS.getPost());
    }
}
