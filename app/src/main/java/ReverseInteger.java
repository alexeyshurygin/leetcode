/**
 * https://leetcode.com/problems/reverse-integer/
 *
 * @author Alexey Shurygin
 */
public class ReverseInteger {
    public int reverse(int x) {
        int r = 0;
        while (x != 0) {
            int prev = r;
            r *= 10;
            if (r / 10 != prev)
                return 0;
            r += x % 10;
            x /= 10;
        }
        return r;
    }
}
