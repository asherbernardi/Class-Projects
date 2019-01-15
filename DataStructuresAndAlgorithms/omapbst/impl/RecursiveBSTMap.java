package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.OrderedMap;

/**
 * RecursiveBSTMap
 * 
 * A implementation of a map (augmented with some "ordered map"
 * operations) using a recursively implemented binary search tree.
 * 
 * @author Thomas VanDrunen
 * Feb 23, 2018
 *
 * @param <K> The key type of the map
 * @param <V> The value type of the map
 */
public class RecursiveBSTMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {

    /**
     * An interface defining the functionality that
     * is delegated to the nodes. In each case the
     * behavior is analogous to how these methods
     * are defined for the (ordered) map ADT
     * except specific to the subtree rooted at the
     * given node.
     */
    private static interface Node<KK extends Comparable<KK>,VV> extends Iterable<KK>{        
        Node<KK,VV> put(KK key, VV val);
        VV get(KK key);
        /**
         * Remove the given key from this subtree
         * @return The root of the modified subtree
         */
        Node<KK,VV> remove(KK key);
        boolean containsKey(KK key);
        KK min();
        /**
         * Remove the minimum key from this subtree
         * @return The root of the modified subtree
         */
        Node<KK,VV> removeMin();
        /**
         * Return the number of keys in the subtree rooted here
         */
        int numKeys();
        /** 
         * Return the number of keys in the subtree rooted here
         * less than the given key.
         */
        int rank(KK key);
        /**
         * Compute the contribution that this subtree makes
         * to the total depth of the entire tree. Note that
         * this does NOT compute the total depth of this tree
         * in isolation but the sum of the absolute depths
         * of the nodes in this subtree.
         * @param d The depth of this node (since it has no
         * way of computing it)
         * @return The total depth contribution of this tree to
         * the entire tree
         */
        int totalDepth(int d);
    }

    private class RealNode implements Node<K,V> {
        /**
         * The key stored at this node
         */
        K key;

        /**
         * The value associated with the key at this node
         */
        V value;

        /** 
         * The children of this node
         */
        Node<K,V> left, right;

        /**
         * Basic constructor
         */
        public RealNode(K key, V value, Node<K,V> left, Node<K,V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * Insert (or overwrite) an association in this subtree
         */
        public Node<K, V> put(K key, V val) {
            int compare = key.compareTo(this.key);
            if (compare < 0) left = left.put(key, val);
            else if (compare == 0)  this.value = val;
            else right = right.put(key, val);
            return this;
        }

        /**
         * Test to see if the given key is in this subtree
         */
        public boolean containsKey(K key) {
            int compare = key.compareTo(this.key);
            if (compare < 0) return left.containsKey(key);
            else if (compare == 0)  return true;
            else return right.containsKey(key);
        }

        /**
         * Retrieve that value of the given key in this subtree
         */
        public V get(K key) {
            int compare = key.compareTo(this.key);
            if (compare < 0) return left.get(key);
            else if (compare == 0)  return value;
            else return right.get(key);
        }

        /**
         * Remove the given tree from this subtree
         */
        public Node<K, V> remove(K key) {
            int compare = key.compareTo(this.key);
            // 1. The key to remove is to the left
            if (compare < 0) left = left.remove(key);
            // 2. The key to remove is here
            else if (compare == 0) {
                // 2a. Zero children or right is only child
                if (left == nully) return right;
                // 2b. Left is only child
                else if (right == nully) return left;
                // 2c. Two children
                else {
                    // Get the successor key
                    this.key = right.min();
                    // Get the successor key's value
                    value = right.get(this.key);
                    // Delete successor node by removing
                    // successor key from right subtree
                    right = right.remove(this.key);
                }
            }
            // 3. The key to remove is to the right
            else right = right.remove(key);
            // In any case...
            return this;
        }

        /**
         * Provide an iterator over the keys in this subtree
         */
        public Iterator<K> iterator() {
            return new Iterator<K>() {
                Iterator<K> lit = left.iterator();
                boolean mine = true;
                Iterator<K> rit = right.iterator();
                public boolean hasNext() {
                    return lit.hasNext() || mine || rit.hasNext();
                }
                public K next() {
                    if (! hasNext()) throw new NoSuchElementException();
                    if (lit.hasNext()) return lit.next();
                    else if (mine) {
                        mine = false;
                        return key;
                    }
                    else {
                        assert rit.hasNext();
                        return rit.next();
                    }
                }

            };
        }
        /**
         * Return the minimum key in the subtree rooted
         * at this node.
         */
        public K min() {
             throw new UnsupportedOperationException();
        }

        /**
         * Remove the minimum key from this subtree
         * @return The root of the modified subtree
         */
        public Node<K, V> removeMin() {
             throw new UnsupportedOperationException();
        }

        /**
         * Return the number of keys in the subtree rooted here
         */
        public int numKeys() {
             throw new UnsupportedOperationException();
        }
        
        /** 
         * Return the number of keys in the subtree rooted here
         * less than the given key.
         */
        public int rank(K key) {
             throw new UnsupportedOperationException();
        }
        
        /**
         * Compute the contribution that this subtree makes
         * to the total depth of the entire tree. Note that
         * this does NOT compute the total depth of this tree
         * in isolation but the sum of the absolute depths
         * of the nodes in this subtree.
         * @param d The depth of this node (since it has no
         * way of computing it)
         * @return The total depth contribution of this tree to
         * the entire tree
         */
        public int totalDepth(int d) {
             throw new UnsupportedOperationException();
        }

        public String toString() {
            return "[" + left.toString() + "(" + key + "=" + value + ")" + right.toString() + "]";
        }

    }


    /**
     * Dummy node object (as a singleton class) to stand
     * in for null links in the tree.
     */
    private Node<K,V> nully = new Node<K,V>() {
        public Node<K, V> put(K key, V val) {
            return new RealNode(key, val, this, this);
        }
        public V get(K key) { return null; }
        public Node<K, V> remove(K key) { return this;}
        public boolean containsKey(K key) { return false; }
        public Iterator<K> iterator() {
            return new Iterator<K> () {
                public boolean hasNext() { return false; }
                public K next() { throw new NoSuchElementException(); }
            };
        }
        public K min() { return null; }
        public Node<K, V> removeMin() { return this; }
        public String toString() { return "*"; }
        public int numKeys() { return 0; }
        public int rank(K key) { return 0; }
        // nulls don't count, so nully makes no contribution 
        // to the total depth of the entire tree
        public int totalDepth(int d) { return 0; }
    };

    // ---------- The RecursiveBSTMap class proper starts here --------

    /**
     * The root of this BST
     */
    private Node<K,V> root;

    /**
     * Constructor for an empty map
     */
    public RecursiveBSTMap() {
        root = nully;
    }

    
    /**
     * Add an association to the map.
     * @param key The key to this association
     * @param val The value to which this key is associated
     */
    public void put(K key, V val) { root = root.put(key, val); }

    /**
     * Get the value for a key.
     * @param key The key whose value we're retrieving.
     * @return The value associated with this key, null if none exists
     */
    public V get(K key) { return root.get(key); }

    /**
     * Test if this map contains an association for this key.
     * @param key The key to test.
     * @return true if there is an association for this key, false otherwise
     */
    public boolean containsKey(K key) { return root.containsKey(key); }

    /**
     * Remove the association for this key, if it exists.
     * @param key The key to remove
     */
    public void remove(K key) { root = root.remove(key); }


    /**
     * Iterate of the keys of this map
     */
    public Iterator<K> iterator() { return root.iterator(); }

    /** 
     * Find the minimum key, if any
     * @return Return the key that comes before every other key, 
     * or null if empty
     */
    public K min() { return root.min(); }

    /**
     * Remove the minimum key, if any.
     */
     public void removeMin() { root = root.removeMin(); }
     
     /**
      * Compute the number of keys
      */
     public int numKeys() { return root.numKeys(); }

     /**
      * Compute the rank a key has in the ordered map,
      * or would have if it were added.
      * @return The number of keys in the ordered map less
      * than the given key.
      */
     public int rank(K key) { return root.rank(key); }
     
     /**
      * Compute the total depth of the underlying tree,
      * where a node's depth is defined as the number of
      * links from the root to it, and the total depth of
      * a tree is the sum of the depths of all its nodes.
      */
     public int totalDepth() { return root.totalDepth(0); }

     public String toString() {
         return root.toString();
     }

}
