import java.util.*;

/**
 * https://leetcode.com/problems/smallest-number-in-infinite-set/
 *
 * @author Alexey Shurygin
 */
public class SmallestInfiniteSet {
    public static final int NBITS = 1001;
    BitSet ex = new BitSet(NBITS);

    public int popSmallest() {
        int i;
        for (i = 1; i < NBITS; i++) {
            if (!ex.get(i)) {
                ex.set(i, true);
                return i;
            }
        }
        return i;
    }

    public void addBack(int num) {
        ex.set(num, false);
    }
}
