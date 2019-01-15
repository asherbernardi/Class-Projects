package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.Map;

/**
 * ArrayMap
 * 
 * Class to implement the Map ADT using an array. * 
 * 
 * (Unlike Stack and Queue, Map is not specified to
 * throw NoSuchElementException when get() or remove()
 * are called with non-existent keys. Instead get()
 * returns null and remove() does nothing. The only
 * reason for this decision is that that's what the tests
 * for Maps that I already had assumed. Similarly put() doesn't
 * throw a FullContainerException.)
 * 
 * CSCI 345, Wheaton College, ASher Bernardi
 * Spring 2016
 * @param <K> The key-type of the map
 * @param <V> The value-type of the map
 */
public class ArrayMap<K, V> implements Map<K, V> {

    /**
     * Class for key-value pairs. This map implementation
     * is essentially an array of these.
     */
    private static class Association<K, V> {
        K key;
        V val;
        Association(K key, V val) {
            this.key = key;
            this.val = val;
        }
        @Override
        public String toString() {
        	return key+"="+val;
        }
    }

    /**
     * An array of key-value associations, the internal
     * representation of this map.
     */
    private Association<K,V>[] internal;

    /**
     * The number of elements currently registered in the map.
     * it is initially zero, when the map is empty.
     */
    private int size = 0;
    

    /**
     * Plain constructor. 
     */
    @SuppressWarnings("unchecked")
    public ArrayMap() {
        // 100 as length of the initial array is an arbitrary choice.
        internal = (Association<K,V>[]) new Association[100];
         
    }

    /**
     * Cause the internal array to double in size.
     */
    private void grow() {
        @SuppressWarnings("unchecked")
        Association<K,V>[] temp = (Association<K,V>[]) new Association[internal.length * 2];
        for (int i = 0; i < internal.length; i++)
            temp[i] = internal[i];
        internal = temp;
    }

    /**
     * Return an iterator over this collection (remove() is
     * unsupported, nor is concurrent modification checked).
     */
    public Iterator<K> iterator() {
    	return new Iterator<K>() {
    		int i = 0;
    		public boolean hasNext() {
    			return i < size;
    		}
    		public K next() {
    			if (i < size)
    				return internal[i++].key;
    			else throw new NoSuchElementException();
    		}

    	};
    }

    /**
     * Searches for a key in the map and returns the Association
     * that uses that key. Returns null if key unfound
     * @param key the key to search for
     * @return the Association associated with that key.
     */
    private Association<K,V> getAssociation(K key) {
    	for (int i = 0; i < size; i++)
        	if (key.equals(internal[i].key))
        		return internal[i];
        return null;
    }
    
    /**
     * Add an association to the map.
     * If the key already exists, change that association.
     * If the map has outgrown the size of the array, grow the array.
     * @param key The key to this association
     * @param val The value to which this key is associated
     */
    public void put(K key, V val) {
    	Association<K,V> old = getAssociation(key);
    	if (old != null) {
    		old.val = val;
    		return;
    	}
		if (size >= internal.length)
			grow();
		internal[size++] = new Association<>(key, val);
    }

    /**
     * Get the value for a key.
     * @param key The key whose value we're retrieving.
     * @return The value associated with this key, null if none exists
     */
    public V get(K key) {
    	Association<K,V> assoc = getAssociation(key);
        if (assoc != null) {
            return assoc.val;
        }
        return null;
    }

    /**
     * Test if this map contains an association for this key.
     * @param key The key to test.
     * @return true if there is an association for this key, false otherwise
     */
    public boolean containsKey(K key) {
        return getAssociation(key) != null;
    }

    /**
     * Remove the association for this key, if it exists.
     * @param key The key to remove
     */
    public void remove(K key) {
    	int pos = -1;
        for (int i = 0; i < size; i++) {
            if (key.equals(internal[i].key)) {
                pos = i;
                break;
            }
        }

        // shift every element backward, deleting the element at pos
        if (pos >= 0) {
            for (int i = pos; i < size - 1; i++)
                internal[i] = internal[i + 1];
            size--;
        }
    }
    
    @Override
    public String toString() {
    	String toReturn = "[";
    	boolean prefix = false;
    	for (Association<K,V> item : internal) {
    		if (prefix)
    			toReturn += ", ";
    		toReturn += item;
    		prefix = true;
    	}
    	return toReturn +"]";
    }

}
