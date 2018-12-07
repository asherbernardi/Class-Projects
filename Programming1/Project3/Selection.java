/**
 * Selection.java
 *
 * A sort() method that implements selection sort.
 *
 * @author Asher Bernardi
 * Wheaton College, CSCI 235, Fall 2016
 * Project 3
 * Date?
 */

public class Selection {

	/**
     * Sort an array, in-place, using selection sort.
     * @param array The array to be sorted.
     * POSTCONDITION: The elements of array are in ascending order.
     */
    public static void sort(int[] array) {

        // Goes through each index and swaps the smallest value with the first value.
        // After each iteration, the smallest is placed at i.
    	for (int i = 0; i < array.length; i++) {

    		int smallest = array[i]; // placeholder for the smallest value.
    		int indexOfSmallest = i; // placeholder for the index of the smallest value.

    		// Check for the smallest value after i, then record the smallest value and its index.
    		for (int j = i + 1; j < array.length; j++) {
    			if (array[j] < smallest) {
    				smallest = array[j];
    				indexOfSmallest = j;
    			}
    		}

    		// Swap the values of the smallest with the value at index i.
    		int temp = array[i];
		    array[i] = array[indexOfSmallest];
		    array[indexOfSmallest] = temp;
    	}

    }
}
