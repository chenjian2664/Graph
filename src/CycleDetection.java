import java.util.Arrays;

/**
 * 检查图中是否有环
 * @author chenjian on 5/3/21
 */
public class CycleDetection
{
    private Graph graph;
    private boolean[] visited;
    private int[] parent;

    public CycleDetection(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];
        this.parent = new int[graph.getV()];

        Arrays.fill(parent, -1);
    }

    public boolean hasCycle() {
        for (int i = 0; i < graph.getV(); ++i) {
            if (!visited[i] && dfs(i, -1)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(int v, int pre) {
        if (visited[v]) {
            //       0
            //      /   \
            //     1 ---- 2
            // 遍历顺序 0 -> 1 -> 2. visited[0]=true.adj(0) = [1,2]
            // 遍历到1时，adj(1) == [0,2], visited[0]=true, 此时由于parent[1]=0,因此不能算作有环。即v=0,pre=1。
            // 遍历到2时，adj(2) == [0,1]，visited[0]=true,当由2进入0时.v=0, pre=2.此时parent[2]=1 != 0, 因此是有环的
            // check一下由2进入1。visited[1]=true, v=1,pre=2.由于parent[2]=1 == v=1, 因此1与2确认不能算作有环。
            // 原因在于：当进入当前次调用时发现v被访问过了，那么一定是v和pre有一条边，逻辑是从pre进入v，且v被完全访问过，pre正在遍历所有邻边
            // 也就隐含v遍历要先于pre.所以如果v是pre的parent，那么就一定没有环，但是如果v不是pre的parent，那么就表明还有一条别的路能够到达
            // pre，因此一定有个环。
            return v != parent[pre];
        }
        visited[v] = true;
        parent[v] = pre;
        for (int w : graph.adj(v)) {
            if (dfs(w, v)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args)
    {
        Graph graph = new Graph("graph-3.txt");
        CycleDetection cycleDetection = new CycleDetection(graph);
        System.out.println("the graph has cycle: " + cycleDetection.hasCycle());

        Graph graph1 = new Graph("graph-4.txt");
        CycleDetection cycleDetection1 = new CycleDetection(graph1);
        System.out.println("the graph has cycle: " + cycleDetection1.hasCycle());
    }
}
