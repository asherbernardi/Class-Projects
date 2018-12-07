package hmllm;

import java.util.Iterator;

/**
 * HomemadeLLMap
 * 
 * An implementation of the HomemadeMap class that uses
 * a completely-homemade linked list on the inside.
 * 
 * @author Thomas VanDrunen and ASher Bernardi
 * CSCI 245, Wheaton College
 * June 30, 2014
 */

public class HomemadeLLMap implements HomemadeMap {

	/**
	 * The first node in the list. Each node points to the next Node.
	 */
	private Node head;
	
	/**
	 * Constructor
	 * Construct an empty list to begin with.
	 */
	public HomemadeLLMap() {
		head = null;
	}
	
    /**
     * Test whether an association exists for this key.
     * Cycle through the linked list. If you reach a key that equals the 
     * key given as an argument, return true. If you reach the end of the list,
     * return false.
     * @param key The key to remove
     * @return true if there is an association for this key, false otherwise
     */
    public boolean containsKey(String key) {
    	for (Node pos = head; pos != null; pos = pos.getNext()) {
    		if (pos.getKey().equals(key))
    			return true;
    	}
    	return false;
    }
   

    /**
     * Add an association to the map.
     * If the key already exists in the map, find the key, then 
     * replace its value with the argument value.
     * Otherwise, add a new key and value at the front of the map.
     * @param key The key to this association
     * @param val The value to which this key is associated
     */
    public void put(String key, String val) {
    	if (this.containsKey(key)) {
    		Node pos = head;
    		for (; pos.getKey() != key; pos = pos.getNext()) {;}
    		pos.setVal(val);
    	}
    	else head = new Node(key, val, head);
    }  

    /**
     * Get the value for a key.
     * Find the key in the map, then return the value.
     * Otherwise, return a null string.
     * @param key The key whose value we're retrieving.
     * @return The value associated with this key, null if none exists
     */
    public String get(String key) {
    	for (Node pos = head; pos != null; pos = pos.getNext()) {
    		if (pos.getKey().equals(key))
    			return pos.getVal();
    	}
    	return null;
    }

    /**
     * Get an iterator for all the keys in this map.
     * @return An iterator over the set of keys.
     */
    public Iterator<String> keyIterator() {
    	return new MapIterator(head);
    }

    
    /**
     * Remove the association for this key.
     * @param key The key to remove
     */   
    public void remove(String key) {
    	Node previous = null, pos = head;
    	while (pos != null && pos.getKey() != key) {
    		previous = pos;
    		pos = pos.getNext();
    	}
    	if (pos == null) return;
    	if (previous == null) head = pos.getNext();
    	else previous.setNext(pos.getNext());
    }
	
}
