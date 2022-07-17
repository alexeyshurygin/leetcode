/**
 * https://leetcode.com/problems/fibonacci-number/
 *
 * @author Alexey Shurygin
 */
public class Fibonacci {
    public int fib(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        int prev2 = 0;
        int prev = 1;
        for (int i = 2; i <= n; i++) {
            int fn = prev + prev2;
            prev2 = prev;
            prev = fn;
        }
        return prev;
    }
}
