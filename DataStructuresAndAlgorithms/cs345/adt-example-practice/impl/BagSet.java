package impl;

import java.util.Iterator;

import adt.Set;
import adt.Bag;

/**
 * BagSet
 * 
 * Class to serve as an example of the set ADT, using
 * a bag. (Specifically, in the versions in place at the time
 * of this writing, this BagSet uses a MapBag which uses a
 * ListMap which uses a WimpList which uses a java.util.ArrayList.
 * Five layers of classes above the underlying array. :)
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * July 10, 2014
 * @param <E> The base-type of the set
 */

public class BagSet<E> implements Set<E> {

    private Bag<E> internal;

    public BagSet() {
        internal = new MapBag<E>();
    }

    /**
     * Add an item to the set. (Do nothing if it's 
     * already there.)
     * @param item The item to add
     */
    public void add(E item) {
    	throw new UnsupportedOperationException();
    }

    /**
     * Does this set contain the item?
     * @param item The item to check
     * @return True if the item is in the bag, false otherwise
     */
    public boolean contains(E item) {
    	throw new UnsupportedOperationException();
    }

    /**
     * Remove an item from the bag, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */
    public void remove(E item) {
    	throw new UnsupportedOperationException();
    }

    /**
     * The number of items in the bag
     * @return The number of items.
     */
    public int size() {
    	throw new UnsupportedOperationException();
    }

    /**
     * Is the set empty?
     * @return True if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
    	throw new UnsupportedOperationException();
    }

    public Iterator<E> iterator() {
    	throw new UnsupportedOperationException();
    }
    
    @Override
    public String toString() {
    	String toReturn = "[";
    	boolean prefix = false;
    	for (E item : this) {
    		if (prefix)
    			toReturn += ", ";
    		toReturn += item;
    		prefix = true;
    	}
    	return toReturn +"]";
    }

}
