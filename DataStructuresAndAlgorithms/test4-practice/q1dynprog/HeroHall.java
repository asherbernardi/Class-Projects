package q1dynprog;

/**
 * HeroHall
 * 
 * Placeholder for a method that calculates the best take of
 * treasure a hero can take while running through a hall.
 * 
 * CSCI 345
 * Test 3 Practice Problem 1.
 */
public class HeroHall {

    /**
     * Compute the maximum amount of treasure a hero can accumulate
     * by running through a hall, with a choice of switching sides
     * of the hall at each step. There is treasure on the left and
     * right of each segment of the hall. Between each segment
     * is a guardian who charges treasure for crossing the hall
     * (there is no cost to go straight ahead, staying on the same
     * side of the hall when entering a new segment).
     * @param s Array storing the amounts of treasure at each segment
     * on the left side of the hall
     * @param t Array storing the amounts of treasure at each segment
     * on the right side of the hall
     * @param g Array storing the amount the guardians between segments
     * charge. Note there is one less guardian than segments, and
     * g[i] charges for crossing the hall between segments i and i+1.
     * @return The maximum amount of treasure possible
     */
    public static int bestTreasure(int[] s, int[] t, int[] g) {
        int n = s.length;
        int[][] C = new int[n][2];
        // Init
        C[0][0] = s[0];
        C[0][1] = t[0];
        for (int i = 1; i < n; i++) {
            C[i][0] = s[i] + Math.max(C[i-1][0], C[i-1][1] - g[i-1]);
            C[i][1] = t[i] + Math.max(C[i-1][0] - g[i-1], C[i-1][1]);
        }

        return Math.max(C[n-1][0], C[n-1][1]);
       
            
    }
    
}