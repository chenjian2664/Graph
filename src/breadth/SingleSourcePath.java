package breadth;

import common.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author chenjian on 7/3/21
 */
public class SingleSourcePath
{
    private Graph graph;
    private boolean[] visited;
    private int source;
    private int[] pre;


    public SingleSourcePath(Graph graph, int source) {
        this.graph = graph;
        this.graph.validateVertex(source);

        this.source = source;
        this.pre = new int[graph.getV()];
        visited = new boolean[graph.getV()];

        Arrays.fill(pre, -1);
        bfs(source);
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;

        while (!queue.isEmpty()) {
            int v = queue.remove();

            for (int w : this.graph.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }
            }
        }
    }

    private boolean isConnected(int target) {
       this.graph.validateVertex(target);
       return visited[target];
    }

    public List<Integer> path(int target) {
        if (!isConnected(target)) {
            return Collections.emptyList();
        }
        List<Integer> res = new ArrayList<>();

        int cur = target;
        while (cur != source) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args)
    {
        Graph graph = new Graph("graph-4.txt");
        SingleSourcePath singleSourcePath = new SingleSourcePath(graph, 0);
        System.out.println("0 -> 6" + singleSourcePath.path(6));
    }
}
