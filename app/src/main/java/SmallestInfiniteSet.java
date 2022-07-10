import java.util.*;

/**
 * https://leetcode.com/problems/smallest-number-in-infinite-set/
 *
 * @author Alexey Shurygin
 */
public class SmallestInfiniteSet {
    byte[] ex = new byte[1001];

    public int popSmallest() {
        int i;
        for (i = 1; i < ex.length; i++) {
            if (ex[i] == 0) {
                ex[i] = 1;
                return i;
            }
        }
        return i;
    }

    public void addBack(int num) {
        ex[num] = 0;
    }
}
