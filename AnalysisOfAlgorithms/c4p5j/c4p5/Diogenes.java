package c4p5;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class Diogenes {

    /**
     * Mark chips in a set as good or bad.
     * @param chips The set of chips to test.
     * PRECONDITION: The majority of the chips are good.
     * POSTCONDITION: Each chip in the set is (correctly) tagged as good or bad.
     */
    public static void diogenes(Set<Chip> chips) {
        int n = chips.size();
        Chip good = findOneGood(chips);
        for (Iterator<Chip> iter = chips.iterator(); iter.hasNext(); ) {
            Chip next = iter.next();
            if (good.test(next))
                next.setTag(true);
            else
                next.setTag(false);
        }
        good.setTag(true);
    }

    private static Chip findOneGood(Set<Chip> chips) {
        int n = chips.size();
        Set<Chip> newChips = new HashSet<Chip>();
        Object[] chipArr = chips.toArray();
        if (n == 1)
            return (Chip) chipArr[0];
        for (Object c: chipArr) {
            newChips.add((Chip) c);
        }
        boolean isOddDoubles = false;
        for (int i = 0; i < n - 1; i += 2) {
            if (!(((Chip) chipArr[i]).test((Chip) chipArr[i+1]) && ((Chip) chipArr[i+1]).test((Chip) chipArr[i]))) {
                newChips.remove(chipArr[i]);
                newChips.remove(chipArr[i+1]);
            }
            else {
                isOddDoubles = !isOddDoubles;
                newChips.remove(chipArr[i+1]);
            }
        }
        if (n%2 != 0 && isOddDoubles)
            newChips.remove(chipArr[n-1]);
        return findOneGood(newChips);
    }

}
