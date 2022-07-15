/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * @author Alexey Shurygin
 */
public class MedianSortedArrays {
    public double findMedianSortedArrays(int[] a, int[] b) {
        switch (a.length) {
            case 0:
                switch (b.length) {
                    case 0:
                        return Integer.MIN_VALUE;
                    case 1:
                        return b[0];
                }
            case 1:
                switch (b.length) {
                    case 0:
                        return a[0];
                }
        }
        int i, j, prev, next;
        i = j = 0;
        prev = next = Integer.MIN_VALUE;
        while (i + j <= (a.length + b.length) / 2) {
            prev = next;
            if (i >= a.length || (j < b.length && a[i] > b[j])) next = b[j++];
            else
                next = a[i++];
        }
        return (a.length + b.length) % 2 == 1 ? next : (next + prev) / 2.0;
    }
}
