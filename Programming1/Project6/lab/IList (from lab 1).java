/**
 * IList.java
 *
 * A first stab at a linked list (iterative version)
 *
 * @author Asher Bernardi and Elijah Yi
 * Wheaton College, CSCI 235, Fall 2016
 * Lab 10
 * Oct. 27, 2016
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
	INode temp = head;
	head = head.next();
	return temp.datum();
	
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
	throw new RuntimeException("method not written");
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
	// ran off the end
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
	INode place = head;  // the node we are looking at
	INode before = null;
	int i = 0;           // the position of place
	while (place != null) {
	    if (place == head.next()) {
            before = head;
	    }
	    if (i == position) { // is place the node we want?
		if (i != 0) {
		    before.setNext(place.next());
		}
		else {
		   this.deleteFront();
		}
	    }
	    // do something before going to the next node
 
	    if (place != head) {
		before = before.next();
	    }
	    place = place.next();
	    i += 1;
	}
	
	
	// reached only if position >= length of list
	// throw new RuntimeException("precondition violated");
    }

    /**
     * Delete the first occurrence of item in this list, if any.
     * @param item The item to delete
     */
    public void deleteFirstOccurrence(int item) {
	throw new RuntimeException("method not written");
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

    /***** Lab 10: Part 3 *****/

    /**
     * Insert a new item at the right place in an (assumed sorted) list.
     * @param item The item to insert in the right place.
     */
    public void insertSorted(int item) {
        // If the list is empty, start a list with an INode that has item as its datum.
        if (head == null) {
            head = new INode (item, null);
        }

    	INode pos = head;          // The variable for the position starts at head.
        INode next = head.next();  // The variable for the INode after position.
        if (next == null) {        // When there is only one element
            // check to see if the new item INode should be placed before or after the first value.
            if (item > head.datum()) {       
                // INode with item as the datum is placed after head
                INode t1 = new INode (item, null); 
                head.setNext(t1);
            }
            else {
                // INode with item as the datum is placed before head
                insertAtFront(item);
            }
        }
        // special case if item is less than head and there are more than one elements
        else if (item < head.datum()) {
            this.insertAtFront(item);
        }
        // if there's more than one element
        else {
            // cycles through each element of the IList except the last one,
            // and stops at the element before where item should be sorted.
            while (pos.datum() < item && next != null) {

                // if we've reached the end of the list
                if (next.next() == null) {
                    // stick the new INode at the end of the list
                    INode temporary = new INode (item, null);
                    next.setNext(temporary);
                    pos = next;
                    next = pos.next();
                }
                // if we're in the middle of the list and we've found where the item goes
                else if (next.datum() > item) {
                    // stick the new node here
                    INode temp = new INode(item, next);
                    pos.setNext(temp);
                }
                pos = next;
                next = pos.next();
            }
        }
    }
    /***** Lab 10: Part 5 *****/

    /**
     * Insert an item at the back of the list.
     * @param item The item to to add
     */
    public void insertAtBack(int item) {
	throw new RuntimeException("method not written");
    }

    /**
     * Remove and return the smallest element of the list.
     * First find the smallest, then delete it, then return it.
     * @return The (now removed) smallest element in the list.
     * PRECONDITION: the list is not empty.
     */
    public int removeSmallest() {
        int smallest = findSmallest();
        deleteFirstOccurrence(smallest);
        return smallest;
    }

    /**
     * Return the value of the smallest element of the list.
     * @return The smallest element in the list.
     * PRECONDITION: the list is not empty.
     */
    private int findSmallest() {
        // find the smallest element value
	throw new RuntimeException("method not written");
    }

    public static void main(String[] args) {
	// whatever we need to test the class
	IList x = new IList();
	/*System.out.println("isEmpty()? "+x.isEmpty());
	System.out.print("Initial: ");
	x.printList();
	System.out.println("insertAtFront(4)");
	x.insertAtFront(15);  // [ 15 ]
	x.printList();
	System.out.println("insertAtFront(12)");
	x.insertAtFront(12);  // [ 12 4  ]
	System.out.println("insertAtFront(15)");
	x.insertAtFront(4);  // [ 4 12 15  ]
	x.printList();
	System.out.println("deleteAt(2)");
        x.deleteAt(2);  // [ 4 12 ]
	x.printList();
	System.out.println("isEmpty()? "+x.isEmpty());
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
	x.printList();*/
	x.insertSorted(3);
	x.printList();
    x.insertSorted(10);
    x.printList();
    x.insertSorted(9);
    x.printList();
    x.insertSorted(17);
    x.printList();
    x.insertSorted(18);
    x.printList();
    x.insertSorted(4);
    x.printList();
    }
}
