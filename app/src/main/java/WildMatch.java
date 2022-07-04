import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/wildcard-matching/
 *
 * @author Alexey Shurygin
 */
public class WildMatch {
    private Set<K> cache = new HashSet<>();

    record K(int ip, int is) {
    }

    private int[] count;
    private char[] s;
    private char[] p;

    public boolean isMatch(String s, String p) {
        if (p.length() == 0)
            return s.length() == 0;
        do {
            p = p.replace("**", "*");
        } while (p.contains("**"));
        count = new int[p.length()];
        int sum = 0;
        this.p = p.toCharArray();
        for (int i = count.length - 1; i >= 0; i--) {
            count[i] = sum;
            if (this.p[i] != '*')
                sum++;
        }
        this.s = s.toCharArray();
        boolean result = tryAdvance(1, 0);
        return result;
    }

    /**
     * Tries one pattern. Returns if that was unsuccessful.
     *
     * @param ip - current p pointer
     * @param is - current s pointer
     */
    public boolean tryAdvance(int ip, int is) {
        /*
        start - define 1st pattern and go to the cycle.
        letter and any match to 1 symbol, if not - rollback.
        seq, anyseq match to the longest string until it does not. if not a match - go to the next pattern, if no more patterns - false
        (backtrack)
        end - no more string chars and no more pattern chars.
        exit criteria - end of string, not match for pattern, end of pattern (success if string is exhausted too, false otherwise)
         */
        while (true) {
            if (ip - 1 >= p.length) {
                return is >= s.length;
            }
            if (p[ip - 1] == '*') {
                if (is >= s.length)
                    return count[ip - 1] == 0;
                //inc string
                if (count[ip - 1] == 0)
                    return true;
                for (int i = s.length - count[ip - 1]; i >= is; i--) {
                    //inc pattern
                    K k = new K(ip + 1, i);
                    if (cache.contains(k))
                        continue;
                    if (tryAdvance(ip + 1, i)) {
                        return true;
                    }
                    cache.add(k);
                }
                return false;
            }
            if (is >= s.length || (p[ip - 1] != '?' && s[is] != p[ip - 1])) return false;
            ip++;
            is++;
        }
    }
}
