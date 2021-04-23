package common;

import java.util.TreeMap;

/**
 * @author chenjian on 20/4/21
 */
public class WeightedGraph
{
    private int V;
    private int E;
    private TreeMap<Integer, Integer>[] adj;

    public int getWeight(int s, int e) {
        return adj[s].get(e);
    }

    public int getV()
    {
        return V;
    }

    public void setV(int v)
    {
        V = v;
    }

    public int getE()
    {
        return E;
    }

    public void setE(int e)
    {
        E = e;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v].keySet();
    }
    // build weighted graph
}
