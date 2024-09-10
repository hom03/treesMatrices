package assignment1.part2;

import assignment1.part2.myEdge;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Graph implementation that uses Adjacency Lists to store edges. It contains
 * one linked-list for every vertex i of the graph. The list for i contains one
 * instance of VertexAdjList for every vertex that is adjacent to i. For
 * directed graphs, if there is an edge from vertex i to vertex j then there is
 * a corresponding element in the adjacency list of node i (only). For
 * undirected graphs, if there is an edge between vertex i and vertex j, then
 * there is a corresponding element in the adjacency lists of *both* vertex i
 * and vertex j. The edges are not sorted; they contain the adjacent nodes in
 * *order* of edge insertion. In other words, for a graph, the node at the head
 * of the list of some vertex i corresponds to the edge involving i that was
 * added to the graph least recently (and has not been removed, yet).
 */

public class myGraphAdjList implements myGraph {

	// ATTRIBUTES:
	private LinkedList<myEdge>[] edges; // The array of the linked lists with the adjacency edges.
	private int V; // number of vertices
	private int E; // number of edges
	private boolean directed; // is the graph directed or not ?

	// CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges
	public myGraphAdjList(int V, boolean directed) {
		this.V = V;
		this.directed = directed;
		this.edges = new LinkedList[V];

		for (int i=0;i<V;i++){
			edges[i] = new LinkedList<>();
		}
	}

	// 1. IMPLEMENTATION METHOD numVerts:
	public int numVerts() {
		return V;
	}

	// 2. IMPLEMENTATION METHOD numEdges:
	public int numEdges() {
		return E;
	}

	// 3. IMPLEMENTATION METHOD addEdge:
	public void addEdge(int v1, int v2, int w) {
		if (v1 >= 0 && v1 < V || v2 >= 0 && v2 < V) {
			if (directed) {
				myEdge newEdge = new myEdge(v2, w);
				edges[v1].add(newEdge);
				E++;
			} else {
				myEdge edge1 = new myEdge(v2, w);
				myEdge edge2 = new myEdge(v1, w);
				edges[v1].add(edge1);
				edges[v2].add(edge2);
				E++;
			}
		}
	}

	// 4. IMPLEMENTATION METHOD removeEdge:
	public void removeEdge(int v1, int v2) {
		if (v1 >= 0 && v1 < V || v2 >= 0 && v2 < V) {
			if (directed) {
				edges[v1].removeIf(edge -> edge.getVertex() == v2);
			}else {
				edges[v1].removeIf(edge -> edge.getVertex() == v2);
				edges[v2].removeIf(edge -> edge.getVertex() == v1);
			}
		}
	}

	// 5. IMPLEMENTATION METHOD hasEdge:
	public boolean hasEdge(int v1, int v2) {
		if (v1 > 0 && v1 < V || v2 >0 && v2 < V) {
			for (myEdge edge : edges[v1]) {
				if (edge.getVertex() == v2) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	// 6. IMPLEMENTATION METHOD getWeightEdge:
	public int getWeightEdge(int v1, int v2) {
		for (myEdge edge : edges[v1]){
			if (edge.getVertex() == v2){
				return edge.getWeight();
			}
		}
		return 0;
	}

	// 7. IMPLEMENTATION METHOD getNeighbors:
	public LinkedList<Integer> getNeighbors(int v) {
		LinkedList<Integer> neighbours = new LinkedList<>();
		for (myEdge edge : edges[v]){
			neighbours.add(edge.getVertex());
		}
		return neighbours;
	}

	// 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) {
		int degree = 0;
		degree = edges[v].size();
		return degree;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < edges.length; i++) {
			s += i + "->";
			for (Iterator<myEdge> iter = edges[i].iterator(); iter.hasNext();)
				s += " " + iter.next();
			s += "\n";
		}
		return s;
	}
}
