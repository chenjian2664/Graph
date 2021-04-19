package path;

import common.DirectedGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author chenjian on 20/4/21
 */
public class TopoSort
{
    private DirectedGraph graph;

    private List<Integer> res;
    private boolean hasCycle;

    public TopoSort(DirectedGraph graph) {
        this.graph = graph;
        res = new ArrayList<>();

        int[] indegree = buildIndegrees(graph);
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < graph.getV(); ++i) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            res.add(cur);
            for (int next : graph.adj()) {
                --indegree[next];
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        hasCycle = res.size() == graph.getV();
    }

    private int[] buildIndegrees(DirectedGraph directedGraph) {
        int[] res = new int[directedGraph.getV()];
        boolean[] visited = new boolean[directedGraph.getV()];
        for (int v = 0; v < directedGraph.getV(); ++v) {
           if (visited[v])  {
               continue;
           }
           dfs(v, visited, directedGraph, res);
        }
        return res;
    }

    private void dfs(int v, boolean[] visited, DirectedGraph directedGraph, int[] res) {
        if (visited[v]) {
           return;
        }
        visited[v] = true;
        for (int w : directedGraph.adj()) {
            ++res[w];
            dfs(v, visited, directedGraph, res);
        }
    }

    private boolean hasCycle() {
        return hasCycle;
    }


}
