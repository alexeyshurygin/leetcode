import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/submissions/
 *
 * @author Alexey Shurygin
 */
class BestTime {
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
        int stepProfit = Math.max(p[start + 1] - p[start], 0);
        int max = stepProfit;
        int maxS = stepProfit > 0 ? start + 1 : -1;
        for (int s = start + 2; s < p.length; s++) {
            int temp = stepProfit;
            if (maxS >= 0) {
                stepProfit += Math.max(p[s] - p[maxS], 0);
            }
            stepProfit = Math.max(stepProfit, p[s] - p[s - 1]);
            if (stepProfit > temp)
                maxS = s;
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
