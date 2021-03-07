package depth;

import common.Graph;

/**
 * @author chenjian on 5/3/21
 */
public class BiPartitionDetection
{
    private Graph graph;
    private boolean[] visited;
    private int[] colors;

    public BiPartitionDetection(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];
        colors = new int[graph.getV()];

        for (int i = 0; i < graph.getV(); ++i) {
            colors[i] = -1;
        }
    }

    public boolean isBiPartition() {

        for (int v = 0; v < graph.getV(); ++v) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) {
                    return false;
                }
            } else if (colors[w] == colors[v]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        Graph graph = new Graph("graph-4.txt");
        BiPartitionDetection biPartitionDetection = new BiPartitionDetection(graph);
        System.out.println("is binary partition graph: " + biPartitionDetection.isBiPartition());

        Graph graph1 = new Graph("graph-5.txt");
        BiPartitionDetection biPartitionDetection1 = new BiPartitionDetection(graph1);
        System.out.println("is binary partition graph: " + biPartitionDetection1.isBiPartition());
    }
}
