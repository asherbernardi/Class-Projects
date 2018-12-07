/**
 * Insertion.java
 *
 * A sort() method that implements insertion sort.
 *
 * @author 
 * Wheaton College, CSCI 235, Fall 2016
 * Project 3
 * Date?
 */

public class Insertion {

	/**
     * Sort an array, in-place, using insertion sort.
     * @param array The array to be sorted.
     * POSTCONDITION: The elements of array are in ascending order.
     */
    public static void sort(int[] array) {

        // The indexes before i correspond to the sorted values.
    	for (int i = 0; i < array.length; i++) {

    		// Starting at i and going down to 0, replace the first
    		// unsorted value with the last sorted value if it is 
    		// smaller than the last sorted value. Continue to swap the
    		// values until the value that was unsorted is larger or equal
    		// to the value before it.
    		for (int j = i; j > 0; j--) {
    			if (array[j] < array[j - 1]) {
    				int temp = array[j];
		    		array[j] = array[j - 1];
		    		array[j - 1] = temp;
		    	}
    		}
    	}


    }
}
