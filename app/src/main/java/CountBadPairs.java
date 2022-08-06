import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode.com/contest/biweekly-contest-84/problems/count-number-of-bad-pairs/
 *
 * @author Alexey Shurygin
 */
public class CountBadPairs {
    public long countBadPairs(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] -= i;
        }
        HashMap<Long, Long> m = new HashMap<>();
        Arrays.stream(a).forEach(i -> m.merge((long) i, 1L, Long::sum));
        long v = m.values().stream().map(i -> i * (i - 1) / 2).reduce(Long::sum).orElse(0L);
        long r = (long) a.length * (a.length - 1) / 2 - v;
        //n*n-1+n-1*n-2+...=(n^2-n)/2=n*(n-1)/2
        //j - i != nums[j] - nums[i]
        //0!= nums[j] - nums[i]
        return r;
    }
}
