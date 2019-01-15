package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import adt.Bag;
import adt.List;

/**
 * ListBag
 * 
 * An implementation of Bag using a List as the underlying
 * implementation.
 * 
 * Recall that our Bag interface differs from what Sedgewick 
 * calls a "bag" (but he's wrong). 
 * 
 * CSCI 345, Wheaton College
 * Spring 2016
 * @param <E> The base-type of the bag
 */
public class ListBag<E> implements Bag<E> {

    private class Entry {
        E item;
        int occurrences;
        Entry(E d, int o) {
            item = d;
            occurrences = o;
        }
        @Override
        public String toString() {
            return item + " x " + occurrences;
        }
    }

    /**
     * The internal representation (can be any implementation of
     * List)
     */
    private List<Entry> internal;
    
    /**
     * Number of elements in the bag.
     */
    private int size = 0;

    public ListBag() {
            internal = new MapList<>();
    }

    /**
     * Return an iterator over this collection (remove() is
     * unsupported, nor is concurrent modification checked).
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Iterator<Entry> iter = internal.iterator();
            Entry entry = null;
            // counter to see how many occurrences of the item
            // are left in the bag.
            int j = -1;
            public boolean hasNext() {
                if (iter.hasNext()) {
                    return true;
                }
                else if (size != 0) {
                    if (entry == null)
                        entry = iter.next();
                    return j < entry.occurrences - 1;
                }
                else
                    return false;
            }
            public E next() {
                if (hasNext()) {
                    if (entry == null)
                        entry = iter.next();
                    // increment the occurrence counter
                    j++;
                    // If returned all occurrences, go to next occ.
                    if (j >= entry.occurrences) {
                        j = 0;
                        entry = iter.next();
                    }
                    return entry.item;
                }
                return null;
                //else throw new NoSuchElementException();
            }
        };
    }

    /**
     * retrieve the index of associated with an item.
     * return -1 if unfound.
     * @param item the item to find
     * @return the index associated with that item.
     */
    private int indexOf(E item) {
        for (int i = 0; i < internal.size(); i++)
            if (internal.get(i).item.equals(item))
                return i;
        return -1;
    }

    /**
     * Add an item to the bag, increasing its count if it's
     * already there.
     * @param item The item to add
     */
    public void add(E item) {
        int index = indexOf(item);
        if (index < 0)
            internal.add(new Entry(item, 1));
        else
            internal.get(indexOf(item)).occurrences++;
        size++;
    }

    /**
     * How many times does this bag contain this item?
     * @param item The item to check
     * @return The number of occurences of this item in the bag
     */
    public int count(E item) {
        int index = indexOf(item);
        if (index >= 0) {
            Entry entry = internal.get(indexOf(item));
            if (entry != null)
                return entry.occurrences;
            else
                throw new NoSuchElementException();
        }
        return 0;
    }

    /**
     * Remove (all occurrences of) an item from the bag, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */
    public void remove(E item) {
        int index = indexOf(item);
        if (index >= 0) {
            size -= internal.get(index).occurrences;
            internal.remove(index);
        }
    }

    /**
     * The number of items in the bag. This is the sum
     * of the counts, not the number of unique items.
     * @return The number of items.
     */
    public int size() {
        return size;
    }

    /**
     * Is the bag empty?
     * @return True if the bag is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public String toString() {
    	String toReturn = "[1";
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
