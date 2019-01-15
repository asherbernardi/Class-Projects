package impl;

import static impl.OptimalBSTMap.dummy;

import java.util.Arrays;

import impl.OptimalBSTMap.Internal;



/**
 * OptimalBSTMapFactory
 * 
 * Build an optimal BST, given the keys, values, key probabilities
 * and miss probabilities.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * Feb 25, 2015
 */

public class OptimalBSTMapFactory {

    /**
     * Exception to throw if the input to building an optimal BST
     * is not right: either the number of keys, values, key probs,
     * and miss probs aren't consistent, or the total probability
     * is not 1.
     */
    public static class BadOptimalBSTInputException extends RuntimeException {
        private static final long serialVersionUID = -444687298513060315L;

        private BadOptimalBSTInputException(String msg) {
            super(msg);
        }
    }
    
    /**
     * Build an optimal BST from given raw data, passed as a single object.
     * A convenient overloading of the other buildOptimalBST().
     * @param rawData The collection of data for building this BST
     * @return A BST with the given keys and values, optimal with the
     * given probabilities.
     */
    public static OptimalBSTMap buildOptimalBST(OptimalBSTData rawData) {
        return buildOptimalBST(rawData.keys, rawData.values, rawData.keyProbs, rawData.missProbs);
    }
    
    /**
     * Build an optimal BST from given raw data, passed as individual arrays.
     * @param rawData The collection of data for building this BST
     * @return A BST with the given keys and values, optimal with the
     * given probabilities.
     */
    public static OptimalBSTMap buildOptimalBST(String[] keys, String[] values, double[] keyProbs,
            double[] missProbs) {
        // keep these checks
        checkLengths(keys, values, keyProbs, missProbs);
        checkProbs(keyProbs, missProbs);        
        
        // The number of keys (so we don't need to say keys.length every time)
        int n = keys.length;
        Internal[][] optSubTrees = new Internal[n][n];
        double[][] C = new double[n][n];
        double[][] T = new double[n][n];
        // Initial the base cases
        for (int i = 0; i < n; i++) {
            T[i][i] = missProbs[i] + keyProbs[i] + missProbs[i+1];
            C[i][i] = 2*missProbs[i] + keyProbs[i] + 2*missProbs[i+1];
            optSubTrees[i][i] = new Internal(dummy, keys[i], values[i], dummy);
        }
        for (int interval = 1; interval < n; interval++) {
            for (int i = 0, j = interval; j < n; i++, j = i + interval) {
                T[i][j] = missProbs[i] + keyProbs[i] + T[i+1][j];
                Internal bestSubTree; // The node that will be placed into the results table
                double bestCost; // The total weighted depth associated with bestSubTree
                double cost;
                // I) Special case r = i. Treat it as the best so far
                bestCost = missProbs[i] + T[i][j] + C[i+1][j];
                bestSubTree = new Internal(dummy, keys[i], values[i], optSubTrees[i+1][j]);
                // II) Middle case
                for (int r = i+1; r < j; r++) {
                    cost = C[i][r-1] + T[i][j] + C[r+1][j];
                    // Update the best cost up to this point
                    if (cost < bestCost) {
                        bestCost = cost;
                        bestSubTree = new Internal(optSubTrees[i][r-1], keys[r], values[r], optSubTrees[r+1][j]);
                    }
                }
                // III) Special case r = j
                cost = C[i][j-1] + T[i][j] + missProbs[j+1];
                // Update the best cost
                if (cost < bestCost) {
                    bestCost = cost;
                    bestSubTree = new Internal(optSubTrees[i][j-1], keys[j], values[j], dummy);
                }
                C[i][j] = bestCost;
                optSubTrees[i][j] = bestSubTree;
            }
        }
        return new OptimalBSTMap(optSubTrees[0][n-1]);
    }

    /**
     * Check that the given probabilities sum to 1, throw an
     * exception if not.
     * @param keyProbs 
     * @param missProbs
     */
    public static void checkProbs(double[] keyProbs, double[] missProbs) {
        double[] allProbs = new double[keyProbs.length + missProbs.length];
        int i = 0;
        for (double keyProb : keyProbs)
            allProbs[i++] = keyProb;
        for (double missProb : missProbs)
            allProbs[i++] = missProb;
        // When summing doubles, sum from smallest to greatest
        // to reduce round-off error.
        Arrays.sort(allProbs);
        double totalProb = 0;
        for (double prob : allProbs)
            totalProb += prob;
        // Don't compare doubles for equality directly. Check that their
        // difference is less than some epsilon.
        if (Math.abs(1.0 - totalProb) > .0001)
            throw new BadOptimalBSTInputException("Probabilities total to " + totalProb);
    }

    /**
     * Check that the arrays have appropriate lengths (keys, values, and
     * keyProbs all the same, missProbs one extra), throw an exception
     * if not.
     * @param keys
     * @param values
     * @param keyProbs
     * @param missProbs
     */
    public static void checkLengths(String[] keys, String[] values,
            double[] keyProbs, double[] missProbs) {
        int n = keys.length;
        if (values.length != n || keyProbs.length != n || missProbs.length != n+1)
            throw new BadOptimalBSTInputException(n + "keys, " + values.length + " values, " +
                    keyProbs.length + " key probs, and " + missProbs.length + " miss probs");
    }
    
}
