import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author chenjian on 2/3/21
 */
public class AdjMatrix
{

    private int V;
    private int E;
    private int[][] adj;

    public AdjMatrix(String fileName)
    {
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            adj = new int[V][V];
            E = scanner.nextInt();

            for (int i = 0; i < E; ++i) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                adj[a][b] = adj[b][a] = 1;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        AdjMatrix adjMatrix = new AdjMatrix("graph.txt");
        System.out.println(adjMatrix);
    }

    public int getV()
    {
        return V;
    }

    public int getE()
    {
        return E;
    }

    private void validateVertex(int x)
    {
        if (x < 0 || x >= V) {
            throw new IllegalArgumentException("Illegal arguments");
        }
    }

    public boolean hasEdge(int v, int w)
    {
        validateVertex(v);
        validateVertex(w);
        return adj[v][w] == 1;
    }

    /**
     * 顶点v的邻边
     *
     * @param v
     * @return 相邻顶点List
     */
    public List<Integer> adj(int v)
    {
        validateVertex(v);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; ++i) {
            if (adj[v][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 度
     *
     * @param v
     * @return
     */
    public int degree(int v)
    {
        return adj(v).size();
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("V = ").append(V).append(", E = ").append(E).append("\n");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                builder.append(adj[i][j]).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
