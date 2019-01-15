/**
 * List235.java
 *
 * An interface for lists of integers.
 *
 * (Named List235 to avoid collision with the standard library.)
 *
 * @author CGG
 * Wheaton College, CSCI 235, Fall 2016
 * Project 6
 * Nov. 11, 2016
 */

interface List235 {

    /*
     * Constructor.
     * POSTCONDITION: This list is empty.
     */
    //public List235();

    /**
     * @return Whether this list is empty.
     */
    public boolean isEmpty();

    /**
     * Add an item to the front of this list.
     */
    public void insertAtFront(int item);

    /**
     * Add an item to the back of this list.
     */
    public void insertAtBack(int item);

    /**
     * Delete the front item from this list.
     * PRECONDITION: this list is not empty.
     */
    public void deleteFront();

    /**
     * Remove and return the front item from this list.
     * PRECONDITION: This list is not empty.
     * @return The item removed.
     */
    public int removeFront();

    /**
     * How many items in this list?
     * @return the number of items in this list.
     */
    public int length();

    /**
     * @param item The value to look for.
     * @return Whether this list contains the item.
     */
    public boolean contains(int item);

    /**
     * @param position The position in the list.
     * @return The value at position in the list.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public int elementAt(int position);

    /**
     * @param item The value to insert.
     * @param position The index after which the item will be inserted.
     * PRECONDITION: position is within the list
     */
    public void insertAfter(int item, int position);

    /**
     * Delete the item at a specified position.
     * @param position The position in the list.
     * PRECONDITION: position >= 0 and position < length of this list
     */
    public void deleteAt(int position);

    /**
     * Delete the first occurrence of item in this list, if any.
     * @param item The item to delete
     */
    public void deleteFirstOccurrence(int item);

    /**
     * Delete all occurrences of item in this list, if any.
     */
    public void deleteAll(int item);

    /**
     * Insert a new item at the right place in an (assumed sorted) list.
     * @param item The item to insert in the right place.
     */
    public void insertSorted(int item);

    /**
     * Remove and return the smallest element of the list.
     * First find the smallest, then delete it, then return it.
     * @return The (now removed) smallest element in the list.
     * PRECONDITION: the list is not empty.
     */
    public int removeSmallest();
  
    /**
     * Print this list on a single line.
     */
    public void printList();
}
