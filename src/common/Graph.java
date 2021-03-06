package common;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author chenjian on 2/3/21
 * 无向无环图
 */
public class Graph
        implements Cloneable
{
    private int V;
    private int E;
    private TreeSet<Integer>[] adj;

    public Graph(String fileName)
    {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("Illegal arguments about Vertex");
            }
            adj = new TreeSet[V];
            for (int i = 0; i < V; ++i) {
                adj[i] = new TreeSet<>();
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

    public Set<Integer> adj(int v)
    {
        validateVertex(v);
        return adj[v];
    }

    public void validateVertex(int v)
    {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("Illegal argument about vertex : " + v);
        }
    }

    public void removeEdge(int v, int w)
    {
        validateVertex(v);
        validateVertex(w);

        adj[v].remove(w);
        adj[w].remove(v);
    }

    @Override
    public Object clone() {
        try {
            Graph cloned = (Graph) super.clone();
            cloned.adj = new TreeSet[getV()];
            for (int i = 0; i < getV(); ++i) {
                cloned.adj[i] = new TreeSet<>();
                for (int w : adj[i]) {
                    cloned.adj[i].add(w);
                }
            }
            return cloned;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
    {
        Graph graph = new Graph("graph.txt");
        System.out.println(graph);
    }

    @Override
    public String toString()
    {
        return "depth.AdjList{" +
                "V=" + V +
                ", E=" + E +
                ", adj=" + Arrays.toString(adj) +
                '}';
    }
}
