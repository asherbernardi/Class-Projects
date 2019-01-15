/**
 * Merge.java
 *
 * This program implements Merge sort.
 *
 * @author 
 * Wheaton College, CSCI 235, Fall 2016
 * Project 3
 * Date?
 */

public class Merge {

    /**
     * Sort an array, in-place, using merging.
     * @param array The array to be sorted.
     * POSTCONDITION: The elements of array are in ascending order.
     */
    public static void sort(int[] array) {

        // create new array which is the merge sorted original array.
        int[] sortedarray = mergeSort(array);

        // to sort in-place, replace each of the value in array one by one with the values in sortedarray.
        for (int i = 0; i < sortedarray.length; i++) {
            array[i] = sortedarray[i];
        }

    }

    /**
     * Extract the portion of an array from start up to (excluding) stop.
     * @param array The source array.
     * @param start The starting index (inclusive).
     * @param stop  The ending index (exclusive).
     * @return An array containing the same elements the portion of array.
     * PRECONDITION: 0 <= start <= stop <= array.length
     */
    private static int[] subarray(int[] array, int start, int stop) {

        // start by defining a new array which has the length of the difference between the start index and the stop index.
        int[] newArray = new int[stop - start];

        // For each iteration from i starting at start and ending at the index before stop, replace each value in newArray
        // with the corresponding value in array.
        for (int i = start; i < stop; i++) {

            // If I starts at start, then i - start will always be the equivalent index for newArray.
            newArray[i - start] = array[i];
        }

        return newArray;

    }

    /**
     * Merge two sorted arrays into one new array.
     * @param first The first array, already sorted.
     * @param second The second array, already sorted.
     * @return A new array containing the elements of first and second,
     *         in order.
     */
    private static int[] merge(int[] first, int[] second) {

        // define three values, i is the index for first, j is the index for second, and n is the index for the final merged array.
        int i = 0, j = 0, n = 0;

        // define a new array which is the length of the two arrays given combined.
        int[] merged = new int[first.length + second.length];

        // After each iteration, the smallest of the values from the indexes for first and second is assigned to the next available
        // index in merged. Once one of the indexes reaches the end of the first or second array, the remaining values of the
        // other array are assigned to merged.
        while (i < first.length || j < second.length) {
            if (i == first.length) { // if the index i reaches the end of first, place the next value from second in merged and j++.
                merged[n] = second[j];
                j++;
            }
            else if (j == second.length) { // if the index j reaches the end of second, place the next value from first in merged and i++.
                merged[n] = first[i];
                i++;
            }
            else if (first[i] < second[j]) { // if the next value of first is smaller than the next value of second, add the next value 
                merged[n] = first[i];        // of first to merged and i++.
                i++;
            }
            else if (second[j] <= first[i]) { // if the next value of second is smaller than the next value of first, add the next value 
                merged[n] = second[j];        // of second to merged and j++.
                j++;
            }
            n++;
        }

        return merged;

    }

    /**
     * Sort an array by merging.
     * @param array The array to sort.
     * @return A new array containing the elements of array, in order.
     */
    private static int[] mergeSort(int[] array) {

        if (array.length == 1) return array; // basecase: if the length of the array is 1, then the array is sorted. Return the array itself.
        else { // otherwise, cut the array into half1 and half2. If the length of the array is odd, make half1 one index shorter than half2.
            int[] half1 = subarray(array, 0, (int)(array.length/2));
            int[] half2 = subarray(array, (int)(array.length/2), array.length);
            half1 = mergeSort(half1); // mergesort half1 with recursion.
            half2 = mergeSort(half2); // mergesort half2 with recursion.
            return merge(half1, half2); // merge the two sorted halves together in order and return the sorted array.
        }


    }


}
