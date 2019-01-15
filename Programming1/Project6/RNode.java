/**
 * RNode.java
 *
 * Node for a recursive linked list.
 *
 * Note that in the recursive version, most of the work for the list methods
 * is done here.
 *
 * @author TVD/CGG/HK, the class, and ??
 * Wheaton College, CSCI 235, Fall 2016
 * Project 6
 * Nov. 11, 2016
 */

public class RNode {

    /**
     * The payload for this RNode.  This should be treated as immutable.
     */

    private final int datum;
    /**
     * The INode following this one.
     */
    private RNode next;

    /**
     * Constructor.
     * @param datum The payload for this INode.
     * @param next  The node that follows this one.
     */
    public RNode(int datum, RNode next) {
        this.datum = datum;
        this.next = next;
    }

    /**
     * Accessor method.
     */
    public int datum() { return datum; }

    /**
     * print this node and its successors
     */
    public void print() {
		System.out.print(datum+" ");
		if (next != null) {
		    next.print();
		}
    }

    /**
     * return the next node to delete the first node.
     * @return next
     */
    public RNode deleteFront() {
    	return next;
    }

    public int length() {
    	if (next == null) {
    		return 1;
    	}
    	return 1 + next.length();
    }

    /**
     * Does the item appear in the rest of the list?
     * @param item   The integer to look for.
     * @return whether the rest of the list has item or not.
     */
    public boolean contains(int item) {
    	if (datum == item) return true;
    	if (next == null) return false;
    	return next.contains(item);
    }

    public RNode insertSorted(int item) {
		if (item > datum) {
		    if (next == null) {
			next = new RNode(item, null);
		    } else {
			next = next.insertSorted(item);
		    }
		    return this;
		} else {
		    return new RNode(item, this);
		}

    }

    public RNode deleteFirstOccurrence(int item) {
		if (datum == item) {
		    return next;
		} else if (next == null) {
		    return this;
		} else {
		    next = next.deleteFirstOccurrence(item);
		    return this;
		}
	}
	
	public int elementAt(int position) {
		if (position == 0) return datum;
		else return next.elementAt(position-1);
	}

	/**
	 * Insert a new item at after a specified position.
	 * @param item     The integer to insert.
     * @param position The position in the list.
     * PRECONDITION: position >= 0 and position < length of this list
	 */
	public void insertAfter(int item, int position) {
		if (position < 0 || position >= length())
			throw new RuntimeException("Precondition violated");
		if (position == 0) next = new RNode(item, next);
		else next.insertAfter(item, position - 1);
	}

    /**
     * Delete the item at a specified position.
     * @param position The position in the list.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public RNode deleteAt(int position) {
    	if (position == 0) return next;
		else if (next == null) return this;
		else {
			next = next.deleteAt(position-1);
			return this;
		}
    }

    /**
     * Delete all occurrences of item in this list, if any.
     * @param item The item to delete
     */
    public RNode deleteAll(int item) {
    	if (next == null)
        	return null;
        if (datum == item)
        	return next.deleteAll(item);
        else {
        	this.next = next.deleteAll(item);
		    return this;
        }
    }

    /**
     * Return the value of the smallest element of the list.
     * @return The smallest element in the list.
     * PRECONDITION: the list is not empty.
     */
    public int findSmallest() {
    	if (next == null) return datum;
    	else if (datum < next.findSmallest()) return datum;
    	else return next.findSmallest();
    }

}