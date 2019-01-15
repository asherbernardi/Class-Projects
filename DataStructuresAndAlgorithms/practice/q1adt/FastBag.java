package q1adt;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * FastBag
 * 
 * An implementation of a Bag whose keys are known 
 * ahead of time and whose main operations (add, count,
 * and remove) operate in logarithmic time.
 * 
 * The keys are given to the constructor; they all initially
 * have count 0. Behavior is undefined if add or remove is
 * called using a key besides those given to the constructor.
 * 
 * CSCI 345
 * Test 2 Practice Problem 1.
 */

public class FastBag implements Bag<String> {
	class Element {
		String key;
		int count;
	}

	Element[] elements;
	int size = 0;
    

    /**
     * Constructor that takes an iterator that gives
     * the keys in sorted order and the number of keys.
     * Behavior is undefined if the number of items returned
     * by the iterator differs from numKeys.
     * @param keys An iterator that gives the potential keys
     * in sorted order.
     * @param numKeys The number of keys
     */
	public FastBag(Iterator<String> keys, int numKeys) {
		elements = new Element[numKeys];
		for(int i = 0; i < numKeys; i++) {
			elements[i] = new Element();
			elements[i].key = keys.next();
			elements[i].count = 0;
		}
	}

	int indexOf(String item, int begin, int end) {
		if (end < begin) return -1;
		if (item.compareTo(elements[(end + begin)/2].key) == 0)
			return (end+begin)/2;
		else if (item.compareTo(elements[(end + begin)/2].key) > 0)
			return indexOf(item,(end + begin)/2 + 1,end);
		else
			return indexOf(item,begin,(end + begin)/2 - 1);
	}


	/**
	 * Add an item to the bag, increasing its count by 1.
	 * Behavior undefined if the given item is not one of the
	 * keys supplied to the constructor.
	 * @param item The item to add
	 */
	public void add(String item) {
	    elements[indexOf(item, 0, elements.length)].count++;
	    size++;
	}

    /**
     * How many times does this bag contain this item?
     * Items supplied to the constructor but either have never been
     * added or have been delete have count 0.
     * Behavior is undefined for items not supplied to the
     * constructor.
     * @param item The item to check
     * @return The count for the given item.
     */
	public int count(String item) {
		return elements[indexOf(item, 0, elements.length)].count;
	}

	/**
	 * Remove the given item, resetting its count to 0.
	 * Behavior is undefined for items not supplied to the
	 * constructor.
	 * @param item The item to remove
	 */
	public void remove(String item) {
		elements[indexOf(item, 0, elements.length)].count--;
		size--;
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
     * Is the set empty?
     * @return True if the set is empty, false otherwise.
     */
	public boolean isEmpty() {
	    return size == 0;
	}

	/**
	 * Make an iterator over the items in this bag which will
	 * return each item the number times indicated by its count.
	 * @return An iterator over the bag
	 */
	public Iterator<String> iterator() {
	    return new Iterator<String>() {
	    	int posKey = 0;
	    	int posCount = 0;
			@Override
			public boolean hasNext() {
				if (posKey < elements.length - 1) {
					return true;
				}
				else if (posKey == elements.length - 1 && posCount < elements[posKey].count)
					return true;
				else return false;
			}

			@Override
			public String next() {
				if (hasNext()) {
					if (posCount == elements[posKey].count) {
						posCount = 0;
						return elements[posKey++].key;
					}
					else {
						posCount++;
						return elements[posKey].key;
					}
				}
				else throw new NoSuchElementException();
			}
		};
    }

}
