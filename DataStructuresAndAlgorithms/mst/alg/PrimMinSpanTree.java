package alg;

import impl.BasicHashSet;
import impl.HeapPriorityQueue;
import adt.PriorityQueue;
import adt.Set;
import adt.WeightedGraph;
import adt.WeightedGraph.WeightedEdge;

/**
 * PrimMinSpanTree
 * 
 * Implementation of Prim's algorithm for computing
 * the minimum spanning tree of a graph.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * June 24, 2015
 */
public class PrimMinSpanTree implements MinSpanTree {

    /**
     * Compute the minimum spanning tree of a given graph.
     * @param g The given graph
     * @return A set of the edges in the minimum spanning tree
     */
    public Set<WeightedEdge> minSpanTree(WeightedGraph g) {
        Set<WeightedEdge> mstEdges = new BasicHashSet<WeightedEdge>(g.numVertices());
        VertexRecord[] records = new VertexRecord[g.numVertices()];
        for (int i = 0; i < g.numVertices(); i++)
            records[i] = new VertexRecord(i, Double.POSITIVE_INFINITY);
        PriorityQueue<VertexRecord> pq = 
                new HeapPriorityQueue<VertexRecord>(records, new VertexRecord.VRComparator());
        
        int[] parents = new int[g.numVertices()];
        for (int i = 0; i < g.numVertices(); i++)
            parents[i] = -1;

        while (!pq.isEmpty()) {
            VertexRecord u = pq.extractMax();
            if (parents[u.id] != -1)
                mstEdges.add(new WeightedEdge(parents[u.id], u.id, g.weight(parents[u.id], u.id), false));
            for (int vid : g.adjacents(u.id)) {
                if (pq.contains(records[vid]) && g.weight(u.id, vid) < records[vid].getDistance()) {
                    parents[vid] = u.id;
                    records[vid].setDistance(g.weight(u.id,vid));
                    pq.increaseKey(records[vid]);
                }
            }
        }

        return mstEdges;
    }

}
