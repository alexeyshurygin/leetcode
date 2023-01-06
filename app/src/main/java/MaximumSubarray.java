/**
 * https://leetcode.com/problems/maximum-subarray/
 *
 * @author Alexey Shurygin
 */
public class MaximumSubarray {
    public int maxSubArray(int[] a) {
        //O(n^3)
        if (a.length == 0)
            return 0;
        int sum = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j <= a.length; j++) {
                var s = sum(a, i, j);
                sum = Math.max(s, sum);
            }
        }
        return sum;
    }

    private int sum(int[] a, int i, int j) {
        int s = 0;
        for (int k = i; k < j; k++) {
            s += a[k];
        }
        return s;
    }

    public static void main(String[] args) {

    }
}
