import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-ice-cream-bars
 *
 * @author Alexey Shurygin
 */
public class MaximumIceCreamBars {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int sum = 0;
        int n = 0;
        for (int i = 0; i < costs.length; i++) {
            int c = costs[i];
            if (coins - sum >= c) {
                sum += c;
                n++;
            }
        }
        return n;
    }
}
