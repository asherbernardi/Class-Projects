/**
 * IList.java
 *
 * A first stab at a linked list (iterative version)
 *
 * @author the class and ASher Bernardi
 * Wheaton College, CSCI 235, Fall 2016
 * Project 6
 * Nov. 3, 2016
 */

public class IList implements List235 {

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
     * @return An empty list
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
    	if (head == null) return true;
        return false;
    }

    /**
     * Insert an item at the front of this list.
     * @param item The item to add.
     */
    public void insertAtFront(int item) {
    	head = new INode(item, head);
    }

    /**
     * Insert an item at the back of the list.
     * @param item The item to to add
     */
    public void insertAtBack(int item) {
        INode place = head;   // The node we are looking at.
        for (; place.next() != null; place = place.next()) {}
        place.setNext(new INode(item, null));
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
     * How many items are in this list?
     * @return The number of items in this list.
     */
    public int length() {
        int length = 0;       // The placeholder for the length
        INode place = head;   // The node we are looking at.
        while (place != null) {
            length++;
            place = place.next();
        }
        return length;
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
		INode place = head;   // The Node we are looking at.
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
    	if (position < 0 | position >= this.length())
            throw new RuntimeException("precondition violated");
        int i = 0;            // The position correstponding to place.
        INode place = head;   // The Node we are looking at.
        while (place != null) {
            if (i == position) place.setNext(new INode(item, place.next()));
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
     * Iterate through each element of the list. If you find the item, make
     * before point to place.next(), unless the item is at the front of the 
     * list, in which case make head point to head.next(). Once you change 
     * a value, break.
     * @param item The item to delete
     */
    public void deleteFirstOccurrence(int item) {
        INode place = head;    // The Node we are looking at.
        INode before = null;   // The Node before place.
        while (place != null) {
            if (place.datum() == item) {
                if (place == head) {
                    head = head.next();
                    break;
                }
                before.setNext(place.next());
                break;
            }
            before = place;
            place = place.next();
        }
    }

    /**
     * Delete all occurrences of item in this list, if any.
     * Iterate through each element of the list. If you find the item, make
     * before point to place.next(), unless the item is at the front of the 
     * list, in which case make head point to head.next(). Once you change 
     * a value, do not break, continue to iterate.
     * @param item The item to delete
     */
    public void deleteAll(int item) {
        INode place = head;    // The Node we are looking at.
        INode before = null;   // The Node before place.
        while (place != null) {
            if (place.datum() == item) {
                if (place == head) {
                    head = head.next();
                }
                else before.setNext(place.next());
            }
            before = place;
            place = place.next();
        }
    }

    /**
     * Make a new list that is the reverse of this one.
     * Insert each consecutive element of the IList at the front of the
     * new IList like a stack.
     * @return the reversed new list
     */
    public IList makeReverse() {
        IList reversed = new IList();  // The reversed Node.
        for (INode place = head; place != null; place = place.next()) {
            reversed.insertAtFront(place.datum());
        }
        return reversed;
    }

    /**
     * Reverse this list (without making a new list).
     * (Ideally, do it without making any new nodes.)
     */
    public void reverse() {
        int i = 0;   // The position of each consecutive element.
        INode place = head;    // The Node we are looking at.
        while (place != null) {
            this.insertAtFront(place.datum());
            place = place.next();
            i++;
            this.deleteAt(i);
        }
    }

  
    /**
     * Insert a new item at the right place in an (assumed sorted) list.
     * If the List is empty, insert item as the first element in the list.
     * Use a while loop to get to the right position in the List to put item.
     * Once you are there, if you are at the front, insertAtFront. If you are
     * at the back, insertAtBack. If you are in the middle, insert before place.
     * @param item The item to insert in the right place.
     */
    public void insertSorted(int item) {
        int i = 0;   // the index of the node we are looking at.
        INode place = head;    // The Node we are looking at.
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
        int smallest = head.datum();   // placeholder for the smallest value.
        for (INode place = head; place != null; place = place.next()) {
            if (place.datum() < smallest) {
                smallest = place.datum();
            }
        }
        return smallest;
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

    public static void main(String[] args) {
	// whatever we need to test the class
	IList x = new IList();
	System.out.println("isEmpty()? "+x.isEmpty());
	System.out.print("Initial: ");
	x.printList();
	System.out.println("insertAtFront(4)");
	x.insertAtFront(4);  // [ 4 ]
	x.printList();
    System.out.println("isEmpty()? "+x.isEmpty());
	System.out.println("insertAtFront(12)");
	x.insertAtFront(12);  // [ 12 4  ]
	System.out.println("insertAtFront(15)");
	x.insertAtFront(15);  // [ 15 12 4  ]
	x.printList();
    System.out.println(x.length());
	System.out.println("deleteAt(2)");
	x.deleteAt(2);  // [ 15 12 ]
	x.printList();
    System.out.println("length = " + x.length());
    System.out.println("insertAtBack(6)");
    x.insertAtBack(6);  // [ 15 12 6 ]
    x.printList(); 
    System.out.println("insertAtBack(3)");
    x.insertAtBack(3);  // [ 15 12 6 3 ]
    x.printList();
    System.out.println(x.elementAt(3)); // 3
    x.insertAfter(3, 1);
    x.printList();  // [ 15 12 3 6 3 ]
    x.reverse();
    x.printList();
    IList s = new IList();
    System.out.print("Initial s: ");
    s.printList();
    System.out.println("insertSorted(4)");
    s.insertSorted(4);  // [ 4 ]
    s.printList();
    System.out.println("isEmpty()? "+x.isEmpty());
    System.out.println("insertSorted(12)");
    s.insertSorted(12);  // [ 4 12 ]
    s.printList();
    System.out.println("insertSorted(15)");
    s.insertSorted(15);  // [ 4 12 15 ]
    s.printList();
    System.out.println("insertSorted(5)");
    s.insertSorted(5);  // [ 4 5 12 15 ]
    s.printList();
    System.out.println("insertSorted(3)");
    s.insertSorted(3);  // [ 3 4 5 12 15 ]
    s.printList();
    System.out.print(x.findSmallest());
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
