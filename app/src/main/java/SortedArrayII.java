/**
 * Beats 100% of solutions of
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
 */
public class SortedArrayII {
    public boolean search(final int[] n, final int t) {
        if (n[0] <= t) {
            for (int i = 0; i < n.length; i++) {
                if (n[i] == t)
                    return true;
                if (n[i] > t)
                    return false;
            }
        } else if (n[n.length - 1] >= t) {
            for (int i = n.length - 1; i >= 0; i--) {
                if (n[i] == t)
                    return true;
                if (n[i] < t)
                    return false;
            }
        }
        return false;
    }
}
