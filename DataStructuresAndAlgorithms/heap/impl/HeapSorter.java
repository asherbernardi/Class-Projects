package impl;

import java.util.Comparator;


/**
 * HeapSorter.java
 *
 * Class to implement the heapsort algorithm.
 *
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College   
 * Originally for CSCI 245, Spring 2007
 * Revised June 2, 2016
 */

public class HeapSorter extends Heap<Integer> {


    /**
     * Constructor. Take an array an sets it up as a (max-) heap.
     * @param array The array to be used for the internal representation.
     */
    private HeapSorter(int[] array) {
        internal = new Integer[array.length];
        for (int i = 0; i < array.length; i++)
            internal[i] = array[i];
        heapSize = array.length;
        
        //fix this; set compy to an appropriate comparator
        compy = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        
        // insert code for rearranging this as a heap
        // for each index of the heap, starting at the end
        // make sure the subtree is a heap. If not, sinkKeyAt.
        for (int i = heapSize - 1; i >= 0; i--) {
            sinkKeyAt(i);
        }
    }
    
    /**
     * Sort this array, in place.
     * @param array The array to sort.
     */
    public static void sort(int[] array) {
    
        HeapSorter heap = new HeapSorter(array);
        
        // insert code for completing the heap sort algorithm,
        // with post condition that heap.internal is sorted
        while (heap.heapSize > 0) {
            // swap the greatest value to be at the end
            // decrement the size
            heap.swap(0, heap.heapSize - 1);
            heap.heapSize--;
            // sink the new root to be in the right place
            heap.sinkKeyAt(0);
        }

        // copy elements from internal (now sorted) back to array
        for (int i = 0; i < array.length; i++)
            array[i] = heap.internal[i];
        
    }

}
