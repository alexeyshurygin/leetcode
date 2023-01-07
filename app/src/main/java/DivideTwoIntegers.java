/**
 * https://leetcode.com/problems/divide-two-integers/
 *
 * @author Alexey Shurygin
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        boolean sameSign = dividend >= 0 && divisor >= 0 || dividend < 0 && divisor < 0;
        long div1 = dividend >= 0 ? dividend : -(long) dividend;
        long div2 = divisor >= 0 ? divisor : -(long) divisor;
        long r;
        for (r = 0; div1 >= div2; div1 -= div2, r++) ;
        if (!sameSign) r = -r;
        r = Math.min(Integer.MAX_VALUE, r);
        r = Math.max(Integer.MIN_VALUE, r);
        return (int) r;
    }
}
