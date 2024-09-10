package assignment1.part2;

import assignment1.part2.myEdge;
import java.util.LinkedList;

/*
 *  Implementation of the interface Graph with adjacency matrix.
 */

public class myGraphAdjMatrix implements myGraph {

    // ATTRIBUTES:
    private int[][] adj; // 2-dimensional array representing the edges.
    private int V; // number of vertices
    private int E; // number of edges
    private boolean directed;  // is the graph directed or not ?

    // CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges
    public myGraphAdjMatrix(int V, boolean directed) {
        this.V = V;
        this.directed = directed;
        this.adj = new int[V][V];
    }

    // 1. IMPLEMENTATION METHOD numVerts:
    public int numVerts() {
        return V;
    }

    // 2. IMPLEMENTATION METHOD numEdges:
    public int numEdges() {
        return E;
    }

    //  3. IMPLEMENTATION METHOD addEdge:
    public void addEdge(int v1, int v2, int w) {
        if (v1 >= 0 && v1 < V || v2 >= 0 && v2 < V){
            if (directed) {
                adj[v1][v2] = w;
                E++;
            }else {
                adj[v2][v1] = w;
                E++;
            }
        }
    }

    // 4. IMPLEMENTATION METHOD removeEdge:
    public void removeEdge (int v1, int v2) {
        if (v1 >= 0 && v1 < V || v2 >= 0 && v2 < V) {
            if (directed) {
                adj[v1][v2] = 0;
            }else{
                adj[v2][v1] = 0;
            }
        }
    }

    // 5. IMPLEMENTATION METHOD hasEdge:
    public boolean hasEdge(int v1, int v2) {
        return adj[v1][v2] != 0;
    }

    // 6. IMPLEMENTATION METHOD getWeightEdge:
    public int getWeightEdge(int v1, int v2) {
        return adj[v1][v2];
    }

    // 7. IMPLEMENTATION METHOD getNeighbors:
    public LinkedList<Integer> getNeighbors(int v) {
        LinkedList<Integer> neighbours = new LinkedList<>();
        for (int i=0;i <V;i++){
            if (adj[v][i] != 0){
                neighbours.add(i);
            }
        }
        return neighbours;
    }

    // 8. IMPLEMENTATION METHOD getDegree:
    public int getDegree(int v) {
        int degree = 0;
        for (int i=0;i <V;i++) {
            if (adj[v][i] != 0) {
                degree++;
            }
        }
        return degree;
    }

    public String toString()
    {
        if (V == 0)
            return "Graph is empty";

        String result = new String("");

        result += "Adjacency Matrix\n";
        result += "----------------\n";
        result += "index\t";

        for (int i = 0; i < V;  i++)
        {
            result += "" + i;
            if (i < 10)
                result += " ";
        }
        result += "\n\n";

        for (int i = 0; i < V; i++)
        {
            result += "" + i + "\t";

            for (int j = 0; j < V; j++)
            {
                if (adj[i][j]!=0)
                    result += adj[i][j]+ " ";
                else
                    result += "0 ";
            }
            result += "\n";
        }
        return result;
    }
}
