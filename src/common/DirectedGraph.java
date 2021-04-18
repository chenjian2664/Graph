package common;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author chenjian on 18/4/21
 */
public class DirectedGraph
{
    private int v;
    private int e;
    private boolean[] visited;
    private boolean[] onPath;
    private TreeSet<Integer>[] adj;

    public DirectedGraph(String fileName)
    {
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {
            this.v = scanner.nextInt();
            if (v < 0) {
                throw new IllegalArgumentException();
            }
            adj = new TreeSet[this.v];
            for (int i = 0; i < this.v; ++i) {
                adj[i] = new TreeSet<>();
            }
            this.e = scanner.nextInt();
            if (this.e < 0) {
                throw new IllegalArgumentException();
            }

            for (int i = 0; i < this.e; ++i) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                adj[a].add(b);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getV()
    {
        return this.v;
    }

    public int getE()
    {
        return this.e;
    }

    public Set<Integer> adj()
    {
        return adj[v];
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Directed graph v = ").append(this.v).append(", e= ").append(this.e).append("\n");
        for (int i = 0; i < this.v; ++i) {
            builder.append(i).append(" : ");
            for (int w : adj[i]) {
                builder.append(w).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public boolean hasCycle() {
        for (int i = 0; i < v; ++i) {
            if (dfs(i)) {
                return true;
            }
        }
        return false;
    }

    // 从顶点v开始，判断图中是否有环
    private boolean dfs(int v) {
        visited[v] = true;
        onPath[v] = true;
        for (int w : this.adj[v]) {
            if (!visited[w]) {
                if (dfs(w)) {
                    return true;
                }
            } else if (onPath[w]) {
                return true;
            }
        }
        onPath[v] = false;
        return false;
    }

    public static void main(String[] args)
    {
        DirectedGraph directedGraph = new DirectedGraph("graph-6.txt");
        System.out.println(directedGraph);
    }
}
