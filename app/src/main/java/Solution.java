import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/submissions/
 *
 * @author Alexey Shurygin
 */
class Solution {
    public int maxProfit(int k, int[] p) {
        results.clear();
        hit = 0;
        miss = 0;
        int max = max(k, p, 0);
        System.out.printf("Hit=%d\n", hit);
        System.out.printf("Miss=%d\n", miss);
        System.out.printf("Cache size=%d\n", results.size());
        return max;
    }

    record K(int k, int start) {
    }

    Map<K, Integer> results = new HashMap<>();
    int hit;
    int miss;

    public int max(int k, int[] p, int start) {
        /*
        prices^2 * days
         */
        if (k <= 0 || p.length - start < 2)
            return 0;
        int max = 0;
        for (int s = p.length - 1; s >= start + 1; s--) {
            int stepProfit = 0;
            for (int b = start; b < s; b++) {
                stepProfit = Math.max(stepProfit, p[s] - p[b]);
            }
            int newS = s + 1;
            int newK = Math.min(Math.max(0, k - 1), (p.length - newS) / 2);
            if (newK >= 0 && p.length - newS >= 2) {
                Integer nextProfit = results.get(new K(newK, newS));
                if (nextProfit != null) {
                    hit++;
                } else {
                    miss++;
                    nextProfit = max(newK, p, newS);
                }
                max = Math.max(max, stepProfit + nextProfit);
            } else {
                max = Math.max(max, stepProfit);
            }
        }
        results.put(new K(k, start), max);
        return max;
    }
}
