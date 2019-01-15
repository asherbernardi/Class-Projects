package adt;

public interface OrderedMap<K extends Comparable<K>,V> extends Map<K,V> {
    
    /** 
     * Find the minimum key, if any.
     * @return Return the key that comes before every other key, 
     * or null if empty
     */
    K min();
    
    /**
     * Remove the minimum key, if any.
     */
    void removeMin();

    /**
     * Compute the number of keys
     */
    int numKeys();
    
    /**
     * Compute the rank a key has in the ordered map,
     * or would have if it were added.
     * @return The number of keys in the ordered map less
     * than the given key.
     */
    int rank(K key);
    
    /**
     * Compute the total depth of the underlying tree,
     * where a node's depth is defined as the number of
     * links from the root to it, and the total depth of
     * a tree is the sum of the depths of all its nodes.
     */
    int totalDepth();
}
