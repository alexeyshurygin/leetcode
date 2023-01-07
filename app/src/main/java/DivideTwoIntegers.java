import java.util.Map;
import java.util.TreeMap;

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
        var pow = new TreeMap<Long, Long>();
        long pow2 = 1;
        long div = div2;
        for (int i = 1; i <= 33; i++) {
            pow.put(pow2, div);
            pow2 += pow2;
            div += div;
            if (pow2 < 0 || div < 0) break;
        }
        long r = 0;
        for (Map.Entry<Long, Long> entry : pow.descendingMap().entrySet()) {
            if (div1 < div2) break;
            Long v = entry.getValue();
            if (div1 >= v) {
                div1 -= v;
                r += entry.getKey();
            }
        }
        if (!sameSign) r = -r;
        r = Math.min(Integer.MAX_VALUE, r);
        r = Math.max(Integer.MIN_VALUE, r);
        return (int) r;
    }
}
