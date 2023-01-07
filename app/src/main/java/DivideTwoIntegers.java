/**
 * https://leetcode.com/problems/divide-two-integers/
 *
 * @author Alexey Shurygin
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        long result = (long) dividend / divisor;
        result = Math.min(Integer.MAX_VALUE, result);
        result = Math.max(Integer.MIN_VALUE, result);
        return (int) result;
    }
}
