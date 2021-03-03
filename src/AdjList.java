import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author chenjian on 2/3/21
 */
public class AdjList
{
    private int V;
    private int E;

    private List<Integer>[] adj;

    public AdjList(String fileName)
    {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("Illegal arguments about Vertex");
            }
            adj = new ArrayList[V];
            for (int i = 0; i < V; ++i) {
                adj[i] = new ArrayList<>();
            }
            E = scanner.nextInt();
            for (int i = 0; i < E; ++i) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                adj[a].add(b);
                adj[b].add(a);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getV()
    {
        return V;
    }

    public int getE()
    {
        return E;
    }

    public boolean hasEdge(int v, int w)
    {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    public int degree(int v)
    {
        return adj(v).size();
    }

    public List<Integer> adj(int v)
    {
        validateVertex(v);
        return adj[v];
    }

    private void validateVertex(int v)
    {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("Illegal argument about vertex : " + v);
        }
    }

    public static void main(String[] args)
    {
        AdjList adjList = new AdjList("graph.txt");
        System.out.println(adjList);
    }

    @Override
    public String toString()
    {
        return "AdjList{" +
                "V=" + V +
                ", E=" + E +
                ", adj=" + Arrays.toString(adj) +
                '}';
    }
}
