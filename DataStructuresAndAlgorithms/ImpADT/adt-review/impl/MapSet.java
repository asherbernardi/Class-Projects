package impl;

import java.util.Iterator;

import adt.Set;
import adt.Map;

/**
 * MapSet
 * 
 * An implementation of Set that uses a Map as its
 * underlying implementation
 * 
 * CSCI 345, Wheaton College, ASher Bernardi
 * Spring 2016
 * @param <E> The base-type of the set
 */
public class MapSet<E> implements Set<E> {

    /**
     * The internal representation. Note this can be any 
     * map implementation. 
     */
    private Map<E, String> internal;

    /**
     * The number of elements in the set.
     */
    private int size = 0;
    
    public MapSet() {
        // since Map is not a very way to implement a set
        // String is a meaningless parameter
        this.internal = new ArrayMap<E,String>();
    }
    
    /**
     * Return an iterator over this collection (remove() is
     * unsupported, nor is concurrent modification checked).
     */
    public Iterator<E> iterator() {
        // Iterator on the internal map.
        final Iterator<E> iter = internal.iterator();
        // Just a decorator.
        return new Iterator<E>() {
            public boolean hasNext() {
                return iter.hasNext();
            }
            public E next() {
                return iter.next();
            }
        };
    }

    /**
     * Add an item to the set. (Do nothing if the item is 
     * already there.)
     * @param item The item to add
     */
    public void add(E item) {
        if (!internal.containsKey(item)) {
            internal.put(item, "");
            size++;
        }
    }

    /**
     * Does this set contain the item?
     * @param item The item to check
     * @return True if the item is in the set, false otherwise
     */
    public boolean contains(E item) {
        return internal.containsKey(item);
    }

    /**
     * Remove an item from the set, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */
    public void remove(E item) {
        if (internal.containsKey(item)) {
            internal.remove(item);
            size--;
        }
    }

    /**
     * The number of itmes in the set
     * @return The number of items.
     */
    public int size() {
        return size;
    }

    /**
     * Is the set empty?
     * @return True if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public String toString() {
    	String toReturn = "[";
    	for (E item : this) {
    		toReturn += item + ", ";
    	}
    	return toReturn +"]";
    }

}
