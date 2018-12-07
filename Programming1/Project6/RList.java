/**
 * RList.java
 *
 * Linked list, recursively.
 *
 * Note that in the recursive version, most of the work is done
 * in the node class.
 *
 * @author TVD/CGG and ???
 * Wheaton College, CSCI 235, Fall 2016
 * Project 6 (from in-class example)
 * Nov. 11, 2016
 */

public class RList implements List235 {

    /**
     * The first node in this list.
     */
    private RNode head;

    /**
     * Constructor.
     * POSTCONDITION: This list is empty.
     */
    public RList() {
    	head = null;
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
    	head = new RNode(item, head);
    }

    /**
     * Delete the front item from this list.
     * PRECONDITION: This list is not empty.
     */
    public void deleteFront() {
    	head = head.deleteFront();
    }

    /**
     * Delete and return the front item from this list.
     * @return The item that was deleted.
     * PRECONDITION: This list is not empty.
     */
    public int removeFront() {
    	int result = head.datum();
    	deleteFront();
        return result;
    }

    /**
     * How many items are in this list?
     * @return The number of items in this list.
     */
    public int length() {
    	if (head == null) {
    	    return 0;
    	}
    	return head.length();
    }

    /**
     * Does the list contain an item?
     * @param item The value to look for.
     * @return Whether this list contains the item.
     */
    public boolean contains(int item) {
        return head.contains(item);
    }

    /**
     * Get the value of the item at a specified position.
     * @param position The position in the list.
     * @return The value at position in the list.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public int elementAt(int position) {
		if (head != null) return head.elementAt(position);
		throw new RuntimeException("The list is empty");
    }

    /**
     * Insert a new item at after a specified position.
     * @param item The value to insert.
     * @param position The index after which the item will be inserted.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public void insertAfter(int item, int position) {
        head.insertAfter(item, position);
    }

    /**
     * Delete the item at a specified position.
     * @param position The position in the list.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public void deleteAt(int position) {
        if (head != null) {
            head = head.deleteAt(position);
        }
    }
    

    /**
     * Delete the first occurrence of item in this list, if any.
     * @param item The item to delete
     */
    public void deleteFirstOccurrence(int item) {
    	if (head != null) {
    	    head = head.deleteFirstOccurrence(item);
    	}
    }

    /**
     * Delete all occurrences of item in this list, if any.
     * @param item The item to delete
     */
    public void deleteAll(int item) {
        head = head.deleteAll(item);
    }

    // more methods specifically for sorting
    
    /**
     * Insert a new item at the right place in an (assumed sorted) list.
     * @param item The item to insert in the right place.
     */
    public void insertSorted(int item) {
    	if (head == null) {
    	    insertAtFront(item);
    	} else {
    	    head = head.insertSorted(item);
    	}
    }

    /**
     * Insert an item at the back of this list.
     * @param item The item to add.
     */
    public void insertAtBack(int item) {
    	if (head == null) {
    	    insertAtFront(item);
    	} else {
    	    insertAfter(item,head.length() - 1);
    	}
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
        return head.findSmallest();
    }

    /**
     * Print the elements of this list to System.out.
     */
    public void printList() {
	System.out.print("[ ");
	if (head != null) {
	    head.print();
	}
	System.out.println("]");
    }

    public static void main(String[] args) {
	// for testing
	RList x = new RList();
	
	x.insertAtFront(15);
	x.insertAtFront(12);
	x.insertAtFront(4);
	x.printList();
	

	x.insertSorted(8);
	x.printList();
	x.insertSorted(2);
	x.insertSorted(2);
	x.printList();
	x.insertSorted(20);
	x.printList();

    System.out.println("Contains 20? " + x.contains(20));

    x.deleteFront();
    x.printList();
    System.out.println("insertAfter(3,2)");
    x.insertAfter(3,2);
    x.printList();
    System.out.println("deleteAt(6)");
    x.deleteAt(6);
    x.printList();

	System.out.println ("Element at 4 is "+ x.elementAt(4));

    x.insertAtFront(12);
    x.printList();
    System.out.println("deleteAll(12)");
    x.deleteAll(12);
    x.printList();

    x.insertAfter(1, 2);
    x.insertAfter(-3, 5);
    x.printList();

    System.out.println("smallest = " + x.findSmallest());
	
	x.deleteFirstOccurrence(8);
	x.printList();
	x.deleteFirstOccurrence(8);
	x.printList();
	x.deleteFirstOccurrence(20);
	x.printList();
	x.deleteFirstOccurrence(2);
	x.printList();
	//System.out.println(x.count());

	x.insertAfter(22, 2);
	x.insertAfter(22, 2);
	x.insertAfter(23, 0);
	x.printList();

	x.deleteFirstOccurrence(22);
	x.printList();

	x.deleteAt(2);
	x.printList();

	x.deleteFirstOccurrence(9);
	x.printList();
    }
}
