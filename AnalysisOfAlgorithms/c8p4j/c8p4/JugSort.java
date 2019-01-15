package c8p4;

import c8p4.Jug.*;

/**
 * JugSort.java
 * 
 * Class to hold public methods for sorting parallel
 * arrays of jugs.
 */
public class JugSort {

    /**
     * Pair up the red and blue jugs so that they are equal.
     * PRECONDITION: The bags of the capacities of reds is equal to
     * the bags of capacities for blues.
     * POSTCONDITION: For all i within bounds, reds[i] = blues[i]
     * NOTE: It is not required that the arrays be sorted by capacity.
     */
    public static void jugSelectionSort(Red[] reds, Blue[] blues) {
    	int n = reds.length;
        for (int i = 0; i < n; i++) {
        	if (reds[i].compareTo(blues[i]) == 0)
        			continue;
    		for (int j = i + 1; j < n; j++) {
    			if (reds[i].compareTo(blues[j]) == 0) {
    				Blue temp = blues[j];
    				blues[j] = blues [i];
    				blues[i] = temp;
    				break;
    			}
    		}
        }
    }
    
    /**
     * Pair up the red and blue jugs so that they are equal.
     * PRECONDITION: The bags of the capacities of reds is equal to
     * the bags of capacities for blues.
     * POSTCONDITION: For all i within bounds, reds[i] = blues[i]
     * NOTE: It is not required that the arrays be sorted by capacity,
     * though in the natural solution they will be.
     */
    public static void jugQuickSort(Red[] reds, Blue[] blues) {
        quickSort(reds, blues, 0, reds.length);
    }

    /**
     * Pair up the red and blue jugs so that they are equal.
     * PRECONDITION (recursion invariant): The bags of the capacities of reds is equal to
     * the bags of capacities for blues in the range [start, stop).
     * POSTCONDITION: For all i in [start, stop), reds[i] = blues[i]
     * NOTE: It is not required that the arrays be sorted by capacity,
     * though in the natural solution they will be.
     */    
    private static void quickSort(Red[] reds, Blue[] blues, int start, int stop) {
    	if (start < stop - 1) {
        // we arbitrarily choose the pivot to be the first element of reds.
    	// Since we can only compare reds to blues, not blues to blues, a Red must
    	// be used as the pivot for the blues, and vice versa. However, we will
    	// make both pivots equivalent in terms of jug capacity and so sorting.
    	Red bluePivot = reds[stop - 1];
    	Blue redPivot = blues[stop - 1];
    	int redPivotPos = stop, bluePivotPos = stop;
    	// for swaps
    	Red tempR;
    	Blue tempB;
    	/****Blue side****/
    	int i = start - 1;
    	// the index where all elements before it are <= and all elements after
    	// are > than bluePivot in blues and than redPivot in the reds.
    	int partition;
    	for (int j = start; j < stop; j++) {
    		if (bluePivot.compareTo(blues[j]) <= 0) {
    			if (bluePivot.compareTo(blues[j]) == 0) {
    				redPivot = blues[j];
    				redPivotPos = i + 1;
    			}
    			i++;
    			tempB = blues[i];
    			blues[i] = blues[j];
    			blues[j] = tempB;
    		}
    		// Here we have found a blue that is the same capacity as Red[stop],
    		// so that will become the redPivot 
    	}
    	tempB = blues[i];
		blues[i] = blues[redPivotPos];
		blues[redPivotPos] = tempB;
		partition = i;
		/****Red side****/
		// this will swap the redPivot item to be in it's proper place, such that
		// the right number of elements will be on either side of it
		tempR = reds[i];
		reds[i] = reds[stop - 1];
		reds[stop - 1] = tempR;
		i = start - 1;
    	for (int j = start; j < stop; j++) {
    		// we only check < not <= because redPivot is already in it's proper
    		// position
    		if (reds[j].compareTo(redPivot) >= 0) {
    			if (reds[j].compareTo(redPivot) == 0) {
    				bluePivotPos = i + 1;
    			}
    			i++;
    			tempR = reds[i];
    			reds[i] = reds[j];
    			reds[j] = tempR;
    		}
    	}
    	tempR = reds[i];
		reds[i] = reds[bluePivotPos];
		reds[bluePivotPos] = tempR;
		partition = i;
    	quickSort(reds, blues, start, partition);
    	quickSort(reds, blues, partition + 1, stop);
    	}
    }
    
}
