package breadth;

import common.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author chenjian on 7/3/21
 */
public class GraphBFS
{
    private Graph graph;
    private boolean[] visited;

    private List<Integer> order = new ArrayList<>();

    public GraphBFS(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];

        for (int v = 0; v < this.graph.getV(); ++v) {
            if (!visited[v]) {
                bfs(v);
            }
        }
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()) {
            int v = queue.remove();
            order.add(v);

            for (int w : this.graph.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public List<Integer> getOrder() {
        return order;
    }

    public static void main(String[] args)
    {
        Graph graph0 = new Graph("graph-4.txt");
        GraphBFS graphBFS = new GraphBFS(graph0);
        System.out.println("order: " + graphBFS.getOrder());
        Graph graph1 = new Graph("graph-5.txt");
        GraphBFS graphBFS1 = new GraphBFS(graph1);
        System.out.println("order: " + graphBFS1.getOrder());
    }
}
