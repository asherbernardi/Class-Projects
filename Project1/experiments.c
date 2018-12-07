/* ASher Bernardi */

#include <stdio.h>

#include "array_util.h"
#include "sorts.h"

int main()
{
	int i;
	// The times it takes for each sort to complete.
	long fore, aft, bubbleTime, selectionTime, insertionTime,
		 shellTime, mergeTime, quickTime;
	// The number of comparisons made by each sort.
	int bubbleComparisons, selectionComparisons, insertionComparisons,
		shellComparisons, mergeComparisons, quickComparisons;

	int master[70000];
	int copy[70000];

	int sizes[] = { 10, 50, 100, 150, 500, 10000, 15000, 20000, 25000,
		30000, 35000, 40000, 45000, 50000, 70000 };

	printf("%15s%15s%15s%15s%15s%15s%15s\n", "", "bubble", "selection", "insertion", "shell", "merge", "quick");

	for (i = 0; i < 15; i++) {
		// Which length of array is being tested.
		printf("for %d:\n", sizes[i]);

		random_array(master, sizes[i]);

		// For bubble sort:
		copy_array(master, copy, sizes[i]);
		fore = get_time_millis();
		bubbleComparisons = bubbleSort(copy, sizes[i]);
		aft = get_time_millis();
		bubbleTime = aft - fore;

		// For selection sort:
		copy_array(master, copy, sizes[i]);
		fore = get_time_millis();
		selectionComparisons = selectionSort(copy, sizes[i]);
		aft = get_time_millis();
		selectionTime = aft - fore;

		// For insertion sort:
		copy_array(master, copy, sizes[i]);
		fore = get_time_millis();
		insertionComparisons = insertionSort(copy, sizes[i]);
		aft = get_time_millis();
		insertionTime = aft - fore;

		// For shell sort:
		copy_array(master, copy, sizes[i]);
		fore = get_time_millis();
		shellComparisons = shellSort(copy, sizes[i]);
		aft = get_time_millis();
		shellTime = aft - fore;

		// For merge sort:
		copy_array(master, copy, sizes[i]);
		fore = get_time_millis();
		mergeComparisons = mergeSort(copy, sizes[i]);
		aft = get_time_millis();
		mergeTime = aft - fore;

		// For quick sort:
		copy_array(master, copy, sizes[i]);
		fore = get_time_millis();
		quickComparisons = quickSort(copy, sizes[i]);
		aft = get_time_millis();
		quickTime = aft - fore;

		// For printing:
		printf("%15s%15d%15d%15d%15d%15d%15d\n", "Comparisons:", bubbleComparisons,
			selectionComparisons, insertionComparisons, shellComparisons,
			mergeComparisons, quickComparisons);
		printf("%15s%15ld%15ld%15ld%15ld%15ld%15ld\n", "Run Times:", bubbleTime,
			selectionTime, insertionTime, shellTime, mergeTime, quickTime);

	}


}