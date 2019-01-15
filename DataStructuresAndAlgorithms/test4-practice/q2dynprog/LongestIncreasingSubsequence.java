package q2dynprog;

/**
 * LongestIncreasingSubsequence
 * 
 * Placeholder for a method that calculates the length of the longest
 * increasing subsequence in a sequence of integers.
 * 
 * CSCI 345
 * Test 3 Practice Problem 2.
 */
public class LongestIncreasingSubsequence {

    /**
     * Compute the length of the longest increasing subsequence
     * of a given sequence of integers.
     * @param A The sequence of integers
     * @return The length of the longest subsequence of increasing numbers
     */
    public static int longIncrSubsq(int[] A) {
        int[] L = new int[A.length];
        // init
        for (int i = 0; i < A.length; i++) {
            boolean hasGreaterItem = false;
            for (int j = i + 1; j < A.length; j++) {
                if (A[j] > A[i]) {
                    hasGreaterItem = true;
                    break;
                }
            }
            if (!hasGreaterItem) {
                L[i] = 1;
            }
        }
        // recursive
        int maxL = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            if (L[i] != 1) {
                int maxGreaterL = 0;
                for (int j = i + 1; j < A.length; j++) {
                    if (A[j] > A[i]) {
                        if (L[j] > maxGreaterL) {
                            maxGreaterL = L[j];
                        }
                    }
                }
                L[i] = 1 + maxGreaterL;
            }
            if (L[i] > maxL)
                maxL = L[i];
        }
        return maxL;
    }
}
