import java.util.*;

/**
 * https://leetcode.com/problems/smallest-number-in-infinite-set/
 *
 * @author Alexey Shurygin
 */
public class SmallestInfiniteSet {
    final SortedSet<Integer> ex = new TreeSet<>();

    public int popSmallest() {
        var prev = 0;
        var it = ex.iterator();
        while (it.hasNext()) {
            var v = it.next();
            if (v - 1 >= prev + 1) {
                ex.add(prev + 1);
                return prev + 1;
            }
            prev = v;
        }
        prev++;
        ex.add(prev);
        return prev;
    }

    public void addBack(int num) {
        ex.remove(num);
    }
}
