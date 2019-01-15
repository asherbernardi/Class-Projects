/**
 * Bubble.java
 *
 * A sort() method that uses bubble sort.
 *
 * @author HK
 * Wheaton College, CSCI 235, Fall 2016
 * Project 3 
 * Sep. 30, 2016
 */

public class Bubble {

    public static void sort(int[] array) {


	// after each iteration, the smallest is placed at i.
	for (int i = 0; i < array.length ; i++) {
	    
	    // compare two adjacent values and move the smaller one step up.
	    for (int j = array.length-1; j > i; j--) {
		
			if (array[j] < array[j-1]) { // out of order
			    int temp = array[j-1];
			    array[j-1] = array[j];
			    array[j] = temp;
			}
	    }
	}
    }
}
