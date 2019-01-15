package expr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import impl.RecursiveBSTMap;

public class BSTExperiment {

    private static Random randy = new Random();
    
    private static class Results {
        int worstTotalDepth, bestTotalDepth, minTotalDepth, maxTotalDepth;
        long worstNanos, bestNanos, minNanos, maxNanos;
        double meanTotalDepth, meanNanos;

        public String toString() {
            return "\t\ttotal depth \t running time\n" +
                    "Worst case:\t" + worstTotalDepth + "\t" + worstNanos + "\n" +
                    "Max random:\t" + maxTotalDepth + "\t\t" + maxNanos + "\n" +
                    "Mean random:\t" + ((int) Math.round(meanTotalDepth)) + "\t\t" + ((int) Math.round(meanNanos)) + "\n" +
                    "Min random:\t" + minTotalDepth + "\t\t" + minNanos + "\n" +
                    "Best case:\t" + bestTotalDepth + "\t\t" + bestNanos;
        }
    }
    
    
    private static Results runExperiment(int sizeSeed, int trials) {
        Results results = new Results();
        int size = ((int) Math.pow(2, sizeSeed)) -1;
        ArrayList<Integer> range = new ArrayList<Integer>();
        for (int i = 0; i < size; i++)
            range.add(i);
        ArrayList<Integer> sequence = new ArrayList<Integer>(range);
        
        // worst
        RecursiveBSTMap<Integer, String> mappy = new RecursiveBSTMap<Integer, String>();
        if (sizeSeed < 14) {
            for (Integer x : range)
                mappy.put(x, "");
            results.worstTotalDepth = mappy.totalDepth();
            results.worstNanos = timeContainsKeys(mappy, size);
        }
        
        // best
        mappy = new RecursiveBSTMap<Integer, String>();
        for (int i = (range.size() + 1) / 2; i > 0; i /= 2)
            for (int j = -1 + i; j < range.size(); j += i) 
                mappy.put(range.get(j), "");
        results.bestTotalDepth = mappy.totalDepth();
        results.bestNanos = timeContainsKeys(mappy, size);
        
        
        // randomly generated
        results.maxNanos = 0;
        results.maxTotalDepth = 0;
        results.minNanos = Integer.MAX_VALUE;
        results.minTotalDepth = Integer.MAX_VALUE;
        long totalNanos = 0;
        int totalTotalDepth = 0;
        
        for (int i = 0; i < trials; i++) {
            mappy = new RecursiveBSTMap<Integer, String>();
            Collections.shuffle(sequence);
            for (Integer x : sequence)
                mappy.put(x, "");
            int totalDepth = mappy.totalDepth();
            long nanos = timeContainsKeys(mappy, size);
            if (totalDepth < results.minTotalDepth)
                results.minTotalDepth = totalDepth;
            if (totalDepth > results.maxTotalDepth)
                results.maxTotalDepth = totalDepth;
            if (nanos < results.minNanos)
                results.minNanos = nanos;
            if (nanos > results.maxNanos)
                results.maxNanos = nanos;
            totalNanos += nanos;
            totalTotalDepth += totalDepth;
        }
        results.meanNanos = ((double) totalNanos) / trials;
        results.meanTotalDepth = ((double) totalTotalDepth) / trials;
        
        
        return results;
    }
    
    private static long timeContainsKeys(RecursiveBSTMap<Integer, String> mappy, int size) {
        long fore = System.nanoTime();
        int runs = size /2;
        for (int i = 0; i < runs; i++)
            mappy.containsKey(randy.nextInt(size));
        return System.nanoTime() - fore;
    }

    public static void main(String[] args) {
 
        // run the experiment once without reporting,
        // to force optimized compilation
        runExperiment(13, 25);

        
        System.out.println(runExperiment(13, 25));
        
        
        
    }

    
}
