/**
 * DList.java
 *
 * A doubly-linked list.
 *
 * @author TVD/CGG and ??
 * Wheaton College, CSCI 235, Fall 2016
 * Project 6
 * Nov. 16, 2016
 */

public class DList implements List235 {

    /**
     * The first node in this list.
     */
    private DLNode head;

    /**
     * Constructor.
     * POSTCONDITION: This list is empty.
     */
    public DList() {
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
    	// set the link going forward (next)
    	DLNode n = new DLNode(item);
    	n.spliceAsHead(head);
    	head = n;
    }

    /**
     * Delete the front item from this list.
     * PRECONDITION: This list is not empty.
     */
    public void deleteFront() {
    	head = head.spliceOut();
    }

    /**
     * Delete and return the front item from this list.
     * @return The item that was deleted.
     * PRECONDITION: This list is not empty.
     */
    public int removeFront() { 
    	DLNode oldHead = head;
    	head = head.spliceOut();
    	return oldHead.datum();
    }

    // Note that most of these can be the same as for singly-linked
    /**
     * How many items are in this list?
     * @return The number of items in this list.
     */
    public int length() {
    	// copy from IList
        int length = 0;        // The placeholder for the length
        DLNode place = head;   // The node we are looking at.
        while (place != null) {
            length++;
            place = place.next();
        }
        return length;
    }

    /**
     * Does the list contain an item?
     * @param item The value to look for.
     * @return Whether this list contains the item.
     */
    public boolean contains(int item) {
        // copy from IList
        DLNode place = head;   // The Node we are looking at.
        while (place != null) {
            if (place.datum() == item) return true;
            place = place.next();
        }
        return false;
    }

    /**
     * Get the value of the item at a specified position.
     * @param position The position in the list.
     * @return The value at position in the list.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public int elementAt(int position) {
        // copy from IList
        DLNode place = head;  // the node we are looking at
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
        if (position < 0 | position >= this.length())
            throw new RuntimeException("precondition violated");
        int i = 0;             // The position correstponding to place.
        DLNode place = head;   // The Node we are looking at.
        while (place != null) {
            if (i == position) {
                DLNode newNode = new DLNode(item);  // The node that will hold item.
                newNode.spliceAfter(place);
                break;
            }
            place = place.next();
            i++;
        }
    }

    /**
     * Delete the item at a specified position.
     * @param position The position in the list.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public void deleteAt(int position) {
        if (position == 0) deleteFront();
        else {
            DLNode place = head;    // where we are in the list
            int i = 0;              // count corresponding to place
            while (place != null) {
                if (i == position) {
                    place.delete();
                    break;
                }
                place = place.next();
                i += 1;
            }
        }
    }

    /**
     * Delete the first occurrence of item in this list, if any.
     * Iterate through each element of the list. If you find the item, delete
     * place. Once you change a value, break.
     * @param item The item to delete
     */
    public void deleteFirstOccurrence(int item) {
        DLNode place = head;    // The Node we are looking at.
        while (place != null) {
            if (place.datum() == item) {
                place.delete();
                break;
            }
            place = place.next();
        }
    }

    /**
     * Delete all occurrences of item in this list, if any.
     * Iterate through each element of the list. If you find the item, point
     * place to its previous, then delete where place was (place.next). Once you
     * change a value, do not break.
     * @param item The item to delete
     */
    public void deleteAll(int item) {
        DLNode place = head;    // The Node we are looking at.
        while (place != null) {
            if (place.datum() == item) {
                place = place.previous();
                place.next().delete();
            }
            place = place.next();
        }
    }

    /**
     * Insert a new item at the right place in an (assumed sorted) list.
     * @param item The item to insert in the right place.
     */
    public void insertSorted(int item) {
        int i = 0;   // the index of the node we are looking at.
        DLNode place = head;    // The Node we are looking at.
        if (head == null) insertAtFront(item);
        else {
            while (place != null && item > place.datum()) {
                place = place.next();
                i++;
            }
            if (i == 0) {
                insertAtFront(item);
            }
            else if (place == null) {
                insertAtBack(item);
            }
            else {
                insertAfter(item, i - 1);
            }
        }

    }

    /**
     * Find the last node in this (non-empty) list.
     * @return The last node in this list.
     * PRECONDITION: this is not empty
     */
    private DLNode findLast() {
        DLNode place = head;    // The Node we are looking at.
        while (place.next() != null) {
            place = place.next();
        }
        return place;
    }
	
   /**
     * Insert an item at the back of this list.
     * @param item The item to add.
     */
    public void insertAtBack(int item) {
    	if (head == null) {
    	    insertAtFront(item);
    	} else {
    	    DLNode last = findLast();
    	    DLNode n = new DLNode(item);
    	    n.spliceAfter(last);
    	}
    }

    /**
     * Remove and return the smallest element of the list.
     * First find the smallest, then delete it, then return it.
     * @return The (now removed) smallest element in the list.
     * PRECONDITION: the list is not empty.
     */
    public int removeSmallest() {
    	DLNode smallest = findSmallest();
    	smallest.spliceOut();
    	return smallest.datum();
    }

    /**
     * Return the smallest element of the list.
     * @return The node containing the smallest element in the list.
     * PRECONDITION: the list is not empty.
     */
    private DLNode findSmallest() {
        DLNode smallest = head;
        DLNode place = head;
        while (place != null) {
            if (place.datum() < smallest.datum())
                smallest = place;
            place = place.next();
        }
        return smallest;
    }

    /**
     * Print the elements of this list to System.out.
     */
    public void printList() {
    	System.out.print("[ ");
    	DLNode place = head;
    	while (place != null) {
    	    System.out.print(place.datum()+" ");
    	    place = place.next();
    	}
    	System.out.println("]");
    }
    
    // You can have a main() here if you want to test
    public static void main(String[] args) {
        DList x = new DList();
        x.insertSorted(2);
        x.insertSorted(3);
        x.insertSorted(1);
        x.insertSorted(5);
        x.insertSorted(4);
        System.out.println(x.length());     // 4
        System.out.println(x.contains(3));  // true
        System.out.println(x.elementAt(1)); // 3
        x.printList();
        System.out.println("" + x.findSmallest().datum());
    }
}
