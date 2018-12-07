/**
 * ListTest.java
 *
 * A set of tests for List235 methods
 *
 * @author CGG
 * Wheaton College, CSCI 235, Fall 2016
 * Project 6
 * Nov. 16, 2016
 */

public class ListTest {

    public static void main(String[] args) {
	System.out.println("List235 class: "+args[0]);
	List235 x = List235Maker.makeInstance(args[0]);
	System.out.print("Initial: ");
	x.printList();
	System.out.println("isEmpty()? "+x.isEmpty());
	try {
	    System.out.println("length() "+x.length());
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
	System.out.println("insertAtFront(4)");
	x.insertAtFront(4);
	x.printList();
	try {
	    System.out.println("length() "+x.length());
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
	System.out.println("isEmpty()? "+x.isEmpty());
	String pop = "3 2 7 4 7 11";
	System.out.println("populate(\""+pop+"\"");
	populate(x, pop);
	x.printList();
	try {
	    System.out.println("length() "+x.length());
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
	System.out.println("deleteFront()");
	x.deleteFront();
	System.out.println("deleteFront()");
	x.deleteFront();
	x.printList();
	System.out.println("insertAtFront(7)");
	x.insertAtFront(7);
	System.out.println("removeFront() "+x.removeFront());
	System.out.println("insertAtFront(4)");
	x.insertAtFront(4);
	System.out.println("insertAtFront(2)");
	x.insertAtFront(2);
	x.printList();
	System.out.println("elementAt(0) "+x.elementAt(0));
	System.out.println("elementAt(2) "+x.elementAt(2));
	x.printList();
	System.out.println("deleteAt(3)");
	x.deleteAt(3);
	x.printList();
	System.out.println("deleteAt(0)");
	x.deleteAt(0);
	x.printList();
	System.out.println("insertAtFront(2)");
	x.insertAtFront(2);
	try {
	    System.out.println("insertAfter(7, 2) [7 after position 2]");
	    x.insertAfter(7, 2);
	    System.out.println("insertAfter(5, 1) [5 after position 1]");
	    x.insertAfter(5, 1);
	    System.out.println("insertAfter(4, 1) [4 after position 1]");
	    x.insertAfter(4, 1);
	    System.out.println("insertAfter(4, 3) [4 after position 3]");
	    x.insertAfter(4, 3);
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
	x.printList();
	try {
	    System.out.println("contains(4): "+x.contains(4));
	    System.out.println("contains(17): "+x.contains(17));
	    System.out.println("deleteFirstOccurrence(4)");
	    x.deleteFirstOccurrence(4);
	    x.printList();
	    System.out.println("deleteFirstOccurrence(17)");
	    x.deleteFirstOccurrence(17);
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
	x.printList();
	try {
	    System.out.println("deleteAll(4)");
	    x.deleteAll(4);
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
	x.printList();
	try {
	    System.out.println("contains(4): "+x.contains(4));
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
	try {
	    System.out.println("insertAtBack(12)");
	    x.insertAtBack(12);
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
	x.printList();
	System.out.println("insertSorted(15)");
	x.insertSorted(15);
	x.printList();
	System.out.println("insertSorted(6)");
	x.insertSorted(6);
	x.printList();
	System.out.println("insertSorted(1)");
	x.insertSorted(1);
	x.printList();
    }

    // A few methods that might be useful for constructing tests

    public static void populate(List235 theList, String str) {
	String[] strValues = str.split(" ");
	for (int i = strValues.length - 1 ; i >= 0; i--) {
	    int val = Integer.parseInt(strValues[i]);
	    theList.insertAtFront(val);
	}
    }

    public static void populate(List235 theList,
				int start, int nItems, int step) {
	int val = start + (nItems-1)*step;
	for (int i = 0; i < nItems; i++, val-=step) {
	    theList.insertAtFront(val);
	}
    }


}
