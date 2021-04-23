package path;

import common.WeightedGraph;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author chenjian on 20/4/21
 */
public class Dijkstra
{

    private WeightedGraph graph;
    private int source;
    private int[] dis;
    private final boolean[] visited;

    private class Node implements Comparable<Node> {
        public int v, dis;

        public Node(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o)
        {
            return this.dis - o.dis;
        }
    }

    public Dijkstra(WeightedGraph graph, int source) {
        this.graph = graph;
        this.source = source;
        this.dis = new int[graph.getV()];

        // Dijkstra, init state. Assume vertex distance is positive infinity
        // source -> source distance is 0
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[source] = 0;
        visited = new boolean[graph.getV()];

        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(source, 0));
        while (!queue.isEmpty()) {
//            int curDis = Integer.MAX_VALUE, cur = -1;
            // O(V)
//            for (int v = 0; v < graph.getV(); ++v) {
//                if (!visited[v] && dis[v] < curDis) {
//                    curDis = dis[v];
//                    cur = v;
//                }
//            }
            // O(logV)
            int cur = queue.remove().v;
            if (visited[cur]) {
                continue;
            }
//            if (cur == -1) {
//                break;
//            }
            visited[cur] = true;
            for (int w : graph.adj(cur)) {
                if (!visited[w]) {
                    // if from cur to w distance is less than current dis[w] then update dis[w]
                    if (dis[cur] + graph.getWeight(cur, w) < dis[w]) {
                        dis[w] = dis[cur] + graph.getWeight(cur, w);
                        queue.add(new Node(w, dis[w]));
                    }
                }
            }
        }
        // here, array dis keep all from source to different vertex minimum distance
    }
}
