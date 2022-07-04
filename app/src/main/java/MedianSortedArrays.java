import java.util.Arrays;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * @author Alexey Shurygin
 */
public class MedianSortedArrays {
    public double findMedianSortedArrays(int[] a1, int[] a2) {
        int[] a = Arrays.copyOf(a1, a1.length + a2.length);
        System.arraycopy(a2, 0, a, a1.length, a2.length);
        Arrays.sort(a);
        return a.length % 2 == 1 ? a[a.length / 2] : (a[a.length / 2 - 1] + a[a.length / 2]) / 2.;
    }
}
