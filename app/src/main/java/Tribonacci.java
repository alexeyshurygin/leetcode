/**
 * https://leetcode.com/problems/n-th-tribonacci-number/
 *
 * @author Alexey Shurygin
 */
public class Tribonacci {
    public int tribonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 1;
        int prev3 = 0;
        int prev2 = 1;
        int prev = 1;
        for (int i = 3; i <= n; i++) {
            int tri = prev + prev2 + prev3;
            prev3 = prev2;
            prev2 = prev;
            prev = tri;
        }
        return prev;
    }
}
