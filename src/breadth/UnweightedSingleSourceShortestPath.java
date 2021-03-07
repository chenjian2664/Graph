package breadth;

import common.Graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author chenjian on 7/3/21
 */
public class UnweightedSingleSourceShortestPath
{
    private Graph graph;
    private int source;
    private boolean[] visited;
    private int[] pre;
    private int[] dis;

    public UnweightedSingleSourceShortestPath(Graph graph, int source)
    {
        this.graph = graph;
        this.source = source;
        this.graph.validateVertex(source);

        this.pre = new int[graph.getV()];
        this.visited = new boolean[graph.getV()];
        this.dis = new int[graph.getV()];

        Arrays.fill(pre, -1);
        Arrays.fill(dis, -1);
        bfs(source);
    }

    private void bfs(int source) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        pre[source] = source;
        visited[source] = true;
        dis[source] = 0;

        while (!queue.isEmpty()) {
            int v = queue.remove();

            for (int w : graph.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                    dis[w] = dis[v] + 1;
                }
            }
        }
    }

    public void printDis() {
        for (int i = 0; i < dis.length; ++i) {
            System.out.print(dis[i] + " ");
        }
        System.out.println();
    }

    public int distance(int target) {
        this.graph.validateVertex(target);
        return dis[target];
    }

    public static void main(String[] args)
    {
        Graph graph = new Graph("graph-4.txt");
        UnweightedSingleSourceShortestPath path = new UnweightedSingleSourceShortestPath(graph, 0);
        System.out.println("0 -> 6 shortest distance : " + path.distance(6));
        path.printDis();
    }
}
