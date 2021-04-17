package path;

import common.Graph;
import depth.ConnectedComponent;

/**
 * 判断图中是否有欧拉回路
 * 1.联通分量为1
 * 2.所有顶点的入度是偶数
 * @author chenjian on 18/4/21
 */
public class EulerLoop
{
    private Graph graph;
    private int[] visited;
    // 连通分量的个数
    private int count;

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
}
