package impl;


/**
 * OptimizedLPOpenAddressingHashMap
 * 
 * An extension to open addressing that avoids using sentinel
 * deleted values when using the linear probing strategy.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * May 18, 2017
 * @param <K> The key-type of the map
 * @param <V> The value-type of the map
 */
public class OptimizedLPOpenAddressingHashMap<K,V> extends OpenAddressingHashMap<K, V> {

    /**
     * Actually unnecessary since the default constructor would
     * have the same effect, but this shows intentionality.
     */
    public OptimizedLPOpenAddressingHashMap() {
        super(1);
    }
    
    
    /**
     * Remove the association for this key, if it exists.
     * @param key The key to remove
     */
    @Override  // now that's a REAL override
    public void remove(K key) {
        int i = find(key);
        if (i == -1) return;
        // If the key exists in the table
        int gap = i;
        int pos = (gap + 1) % table.length;
        int ideal;
        while (table[pos] != null && pos != gap) {
            ideal = h.hash(table[pos].key);
            // plug cases
            if (    (ideal <= gap && gap < pos) ||
                    (pos < ideal && ideal <= gap) ||
                    (gap < pos && pos < ideal)) {
                table[gap] = table[pos];
                gap = pos;
            }
            // If not a plug case, skip that position
            // and go to the next one.
            // Either way, go to the next position.
            pos = (pos + 1) % table.length;
        }
        numPairs--;
        table[gap] = null;
    }
    
    
}
