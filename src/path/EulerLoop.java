package path;

import common.Graph;
import depth.ConnectedComponent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 判断图中是否有欧拉回路
 * 1.联通分量为1
 * 2.所有顶点的度是偶数
 * @author chenjian on 18/4/21
 */
public class EulerLoop
{
    private Graph graph;
    private int[] visited;
    // 连通分量的个数
    private int count;

    public EulerLoop(Graph graph) {
        this.graph = graph;
    }

    private int getCount() {
        return count;
    }

    public boolean hasEulerLoop() {
        ConnectedComponent connectedComponent = new ConnectedComponent(graph);
        if (connectedComponent.getCount() > 1) {
            return false;
        }
        for (int i = 0; i < graph.getV(); ++i) {
            if (graph.degree(i) % 2 == 1) {
                return false;
            }
        }
        return true;
    }

    // time :O(E)
    public List<Integer> result() {
        List<Integer> res = new ArrayList<>();
        if (!hasEulerLoop()) {
            return res;
        }
        Graph g = (Graph) this.graph.clone();

        Deque<Integer> stack = new ArrayDeque<>();
        int curV = 0;
        stack.push(curV);
        while (!stack.isEmpty()) {
            if (g.degree(curV) != 0) {
                stack.push(curV);
                int w = g.adj(curV).iterator().next();
                // remove edge curV -> w
                g.removeEdge(curV, w);
                curV = w;
            } else {
                res.add(curV);
                curV = stack.pop();
            }
        }
        return res;
    }
}
