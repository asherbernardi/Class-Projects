package hmllm;

/**
 * Node
 * 
 * One Node as part of a HomemadeLLMap.
 * 
 * @author ASher Bernardi
 * CSCI 245, Wheaton College
 * February 10, 2017
 */

public class Node {
	
	/**
	 * Points to the next Node in the linked list.
	 */
	private Node next;
	
	/**
	 * The key associated with this element of the map.
	 */
	private String key;
	
	/**
	 * The value associated with this key.
	 */
	private String val;
	
	/**
	 * Constructor
	 * @param k The key of the new element of the list.
	 * @param v The value of the new element of the list.
	 * @param n The next Node that this element will point to.
	 */
	public Node(String k, String v, Node n) {
		key = k;
		val = v;
		next = n;
	}
	
	/**
	 * Access the key of the Node.
	 * @return the key.
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Access next Node in the List.
	 * @return the next Node.
	 */
	public Node getNext() {
		return next;
	}
	
	/**
	 * Access the value of the Node.
	 * @return the value.
	 */
	public String getVal() {
		return val;
	}
	
	/**
	 * Modify the Node to which this Node points.
	 * @param n The new next Node.
	 */
	public void setNext(Node n) {
		next = n;
	}
	
	/**
	 * Modify the value of this Node.
	 * @param v The new Value.
	 */
	public void setVal(String v) {
		val = v;
	}

}
