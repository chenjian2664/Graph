package depth;

import common.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author chenjian on 3/3/21
 */
public class Path
{
    private Graph graph;
    private int source;
    private int target;

    private boolean[] visited;
    private int[] pre;

    /**
     * 寻找一个source -> target的路径
     */
    public Path(Graph graph, int source, int target)
    {
        this.graph = graph;
        this.graph.validateVertex(source);
        this.graph.validateVertex(target);
        this.source = source;
        this.target = target;

        visited = new boolean[graph.getV()];
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);

        dfs(source, source);
        System.out.println("visited case: ");
        for (boolean b : visited) {
            System.out.print(b + " ");
        }
        System.out.println();
    }

    // 从source出发 能否 到达target
    private boolean dfs(int v, int parent)
    {
        visited[v] = true;
        pre[v] = parent;
        if (v == target) {
            return true;
        }
        for (int w : graph.adj(v)) {
            // 提前终止递归
            if (!visited[w] && dfs(w, v)) {
                return true;
            }
        }
        return false;
    }

    public boolean isConnected() {
        return visited[target];
    }

    public List<Integer> path() {
        if (!isConnected()) {
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
//        common.Graph graph = new common.Graph("graph.txt");
        Graph graph = new Graph("graph-3.txt");
        Path path = new Path(graph, 0, 6);
        System.out.println("path from 0 to 6: \n" + path.path());

        // 提前终止了
        Path path1 = new Path(graph, 0, 1);
        System.out.println("path from 0 to 6: \n" + path1.path());
    }
}
