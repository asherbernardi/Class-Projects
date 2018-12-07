/**
 * IList.java
 *
 * A first stab at a linked list (iterative version)
 *
 * @author the class and ASher Bernardi and Kaitie McCollum
 * Wheaton College, CSCI 235, Fall 2016
 * Lab 11 
 * Nov. 3, 2016
 */

public class IList {

    /**
     * The first INode in this list.
     */
    private INode head;

    /**
     * Constructor.
     * POSTCONDITION: This list is empty.
     */
    public IList() {
	head = null;
    }

    /**
     * Alternative to constructor: create a list for use by sorting methods.
     * To simplify the insertion methods used for sorting, this starts
     * out the list with an extra zero item on the front.
     * Part 4:  After fixing the insertions to work on an empty list,
     * change this method  to start with an empty list.
     */
    public static IList newSortList() {
	IList theList = new IList();
	// get rid of the next line in Lab 11: Part 4 
	theList.insertAtFront(0);
	return theList;
    }

    /**
     * Is this list empty?
     * @return Whether this list is empty.
     */
    public boolean isEmpty() {
	return head == null;
    }

    /**
     * Insert an item at the front of this list.
     * @param item The item to add.
     */
    public void insertAtFront(int item) {
	head = new INode(item, head);
    }

    /**
     * Delete the front item from this list.
     * PRECONDITION: This list is not empty.
     */
    public void deleteFront() {
	head = head.next();
    }

    /**
     * Delete and return the front item from this list.
     * @return The item that was deleted.
     * PRECONDITION: This list is not empty.
     */
    public int removeFront() {
		int temp = head.datum();
		head = head.next();
		return temp;
	//throw new RuntimeException("method not written");
    }

    /**
     * Print the elements of this list to System.out.
     */
    public void printList() {
	System.out.print("[ ");
	INode place = head; // position within this list
	while (place != null) {
	    System.out.print(place.datum()+" ");
	    place = place.next();
	}
	System.out.println("]");
    }

    /**
     * How many items are in this list?
     * @return The number of items in this list.
     */
    public int length() {
	throw new RuntimeException("method not written");
    }
 
    /**
     * What is the sum of items in the list?
     * @return The sum of all items in this list.
     */
    public int sum() {
	throw new RuntimeException("method not written");
    }

    /**
     * Does the list contain an item?
     * @param item The value to look for.
     * @return Whether this list contains the item.
     */
    public boolean contains(int item) {
		INode place = head;
		while (place != null) {
			if (place.datum() == item) return true;
			place = place.next();
		}
		return false;
	//throw new RuntimeException("method not written");
    }

    /**
     * Get the value of the item at a specified position.
     * @param position The position in the list.
     * @return The value at position in the list.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public int elementAt(int position) {
	/* This is not the most compact way to write this,
	 * but this form can be adapted to other uses.
	 */
	INode place = head;  // the node we are looking at
	int i = 0;           // the position of place
	while (place != null) {
	    if (i == position) { // is place the node we want?
		return place.datum();
	    }
	    // do something before going to the next node
	    place = place.next();
	    i += 1;
	}
	throw new RuntimeException("precondition violated");
    }

    /**
     * Insert a new item at after a specified position.
     * @param item The value to insert.
     * @param position The index after which the item will be inserted.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public void insertAfter(int item, int position) {
	throw new RuntimeException("method not written");
    }

    /**
     * Delete the item at a specified position.
     * @param position The position in the list.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public void deleteAt(int position) {
		if (position == 0) deleteFront();
		else {
			INode place = head;    // where we are in the list
			int i = 0;             // count corresponding to place
			INode previous = null; // the node before place
									// There is nothing before head, so null?
			while (place != null) {
				if (i == position) {
					previous.setNext(place.next());
					break;
				}
				previous = place;
				place = place.next();
				i += 1;
			}
		}
	    
    }

    /**
     * Delete the first occurrence of item in this list, if any.
     * @param item The item to delete
     */
    public void deleteFirstOccurrence(int item) {
	// Look through each node in the IList and keep track of the node before place.
	for (INode place = head, previous = null; place != null; previous = place, place = place.next()) {
	    // If the head has the value of item, remove the first node.
	    if (head.datum() == item) {
		head = head.next();
	    }
	    // otherwise, when you find the first occurence of item, remove place.
	    else if (place.datum() == item) {
		    previous.setNext(place.next());
	    }
	}
    }

    /**
     * Delete all occurrences of item in this list, if any.
     * @param item The item to delete
     */
    public void deleteAll(int item) {
	throw new RuntimeException("method not written");
    }

    /**
     * Make a new list that is the reverse of this one.
     * @return the reversed new list
     */
    public IList makeReverse() {
	throw new RuntimeException("method not written");
    }

    /**
     * Reverse this list (without making a new list).
     * (Ideally, do it without making any new nodes.)
     */
    public void reverse() {
	throw new RuntimeException("method not written");
    }

  
    /**
     * Insert a new item at the right place in an (assumed sorted) list.
     * @param item The item to insert in the right place.
     */
    public void insertSorted(int item) {
	throw new RuntimeException("method not written");
    }


    /**
     * Insert an item at the back of the list.
     * @param item The item to to add
     */
    public void insertAtBack(int item) {
	// loop to find the last node.
	INode place = head;
	while (place.next() != null) {
	    place = place.next();
	}
	// add item into a node at the back.
        INode temp = new INode (item, null);
	place.setNext(temp);
    }

    /**
     * Remove and return the smallest element of the list.
     * First find the smallest, then delete it, then return it.
     * @return The (now removed) smallest element in the list.
     * PRECONDITION: the list is not empty.
     */
    public int removeSmallest() {
            /* Previous code:
    	int smallest = findSmallest();
            deleteFirstOccurrence(smallest);
            return smallest;
    	*/

    	// initialize a node variable to keep track of the node with has the smallest value.
    	INode min = head;

    	// and the node before it.
    	INode beforeMin = null;
        
    	// go through all the nodes, keeping track of the previous.
    	for (INode place = head, previous = null; place != null; previous = place, place = place.next()) {
    	    // If the datum of place is smaller than the datum of min, replace min with place.
    	    if (place.datum() < min.datum()) {
    		min = place;
    		beforeMin = previous;
    	    }
    	}
    	// Now that we have the minimum node, remove it and return its datum value.
    	if (head.datum() == min.datum()) {
    	    head = head.next();
    	}
    	else  {
    	    beforeMin.setNext(min.next());
    	}
    	return min.datum();
    }

    /**
     * Return the value of the smallest element of the list.
     * @return The smallest element in the list.
     * PRECONDITION: the list is not empty.
     */
    private int findSmallest() {
        // find the smallest element value
	INode place = this.head;
	int min = head.datum();  // initialize a minimum value as the first value.

	while (place != null) {
	    // If place is smaller than min, replace min with the datum of place.
	    if (place.datum() < min) {
		min = place.datum();
	    }
	    place = place.next(); // increment
	}
	return min;
    }

    public static void main(String[] args) {
	// whatever we need to test the class
	IList x = new IList();
	System.out.println("isEmpty()? "+x.isEmpty());
	System.out.print("Initial: ");
	x.printList();
	System.out.println("insertAtFront(4)");
	x.insertAtFront(4);  // [ 4 ]
	x.printList();
	System.out.println("insertAtFront(12)");
	x.insertAtFront(12);  // [ 12 4  ]
	System.out.println("insertAtFront(15)");
	x.insertAtFront(15);  // [ 15 12 4  ]
	x.printList();
	System.out.println("deleteAt(2)");
	x.deleteAt(2);  // [ 15 12 ]
	x.printList();
	/*System.out.println("isEmpty()? "+x.isEmpty());
	System.out.println("length() "+x.length());
	System.out.println("deleteFront()");
	x.deleteFront();
	System.out.println("deleteFront()");
	x.deleteFront();
	x.printList();
	System.out.println("insertAtFront(15)");
	x.insertAtFront(15);
	System.out.println("insertAtFront(7)");
	x.insertAtFront(7);
	System.out.println("removeFront() "+x.removeFront());
	x.printList();
	*/
    }
}
