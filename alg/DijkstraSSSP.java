package alg;

import impl.HashSet;
import impl.HeapPriorityQueue;
import adt.PriorityQueue;
import adt.Set;
import adt.WeightedGraph;
import adt.WeightedGraph.WeightedEdge;

/**
 * DijkstraSSSP
 * 
 * An implementation of Dijkstra's algorithm for computing the single-source
 * shortest paths of a graph given a source.
 * 
 * @author Thomas VanDrunen CSCI 345, Wheaton College June 29, 2015
 */
public class DijkstraSSSP implements SSSP {

	/**
	 * Compute the shortest paths in a given tree from a given source to all over
	 * vertices.
	 * 
	 * @param g
	 *            The given graph
	 * @param source
	 *            The vertex from which to compute paths
	 * @return A set of edges constituting the tree of shortest paths.
	 */
	public Set<WeightedEdge> sssp(WeightedGraph g, int source) {
		VertexRecord[] distanceBounds = new VertexRecord[g.numVertices()];
		int[] parents = new int[g.numVertices()];
		for (int i = 0; i < g.numVertices(); i++) {
			distanceBounds[i] = new VertexRecord(i, Double.POSITIVE_INFINITY);
			parents[i] = -1;
		}
		distanceBounds[source].setDistance(0);
		PriorityQueue<VertexRecord> pq = new HeapPriorityQueue<VertexRecord>(distanceBounds,
				new VertexRecord.VRComparator());

		distanceBounds[source].setDistance(0);

		// loop through every vertex
		while (!pq.isEmpty()) {

			// current vertex
			VertexRecord v = pq.extractMax();

			// loop through the adjacents of current vertex v
			for (int adj : g.adjacents(v.id)) {
				// distance from the source to the current vertex
				double weight = v.getDistance() + g.weight(v.id, adj);

				// update the path if the weight is higher than a previous path's weight.
				if (distanceBounds[adj].getDistance() > weight) {
					distanceBounds[adj].setDistance(weight);
					parents[adj] = v.id;
					pq.increaseKey(distanceBounds[adj]);
				}
			}

		}

		Set<WeightedEdge> treeEdges = new HashSet<WeightedEdge>();

		for (int v = 0; v < g.numVertices(); v++) {
			int u = parents[v];
			if (u != -1)
				treeEdges.add(new WeightedEdge(u, v, g.weight(u, v), true));
		}

		return treeEdges;
	}

}
