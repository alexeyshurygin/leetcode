import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * https://leetcode.com/problems/minimum-replacements-to-sort-the-array/submissions/
 *
 * @author Alexey Shurygin
 */
public class MinRepl {
    public long minimumReplacement(int[] a) {
        if (a.length < 2) return 0;
        long r = 0;
        int next = a[a.length - 1];
        for (int i = a.length - 2; i >= 0; i--) {
            int curr = a[i];
            if (next >= curr) {
                next = curr;
            } else {
                long steps = (long) Math.ceil((double) curr / next);
                r += steps - 1;
                next = (int) (curr / steps);
            }
        }
        return r;
    }
}
