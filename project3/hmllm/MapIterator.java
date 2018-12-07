package hmllm;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MapIterator
 * 
 * An Iterator for HomemadeLLMap.
 * 
 * @author ASher Bernardi
 * CSCI 245, Wheaton College
 * February 10, 2017
 */

public class MapIterator implements Iterator<String> {
	
	/**
	 * The current position in the map as you iterate.
	 */
	private Node pos;
	
	/**
	 * Constructor
	 * @param p The start of the iterator.
	 */
	public MapIterator(Node p) {
		pos = new Node("", "", p);
	}

	/**
	 * @return Whether the iterator has an element after it.
	 */
	@Override
	public boolean hasNext() {
		if (pos == null) return false;
		if (pos.getNext() != null) return true;
		return false;
	}

	/**
	 * Increment the element forward 1.
	 * @return the next element.
	 */
	@Override
	public String next() {
		if (pos == null) throw new NoSuchElementException();
		if (pos.getNext() == null) throw new NoSuchElementException();
		pos = pos.getNext();
		return pos.getKey();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
