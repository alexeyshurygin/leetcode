import java.util.List;

/**
 * https://leetcode.com/problems/wildcard-matching/
 *
 * @author Alexey Shurygin
 */
public class WildMatch {
    private int[] count;

    enum Kind {
        START, //start
        LETTER,//letter
        ANY, //?
        SEQ, //*
        END //pattern ended
    }

    public boolean isMatch(String s, String p) {
        do {
            p = p.replace("**", "*");
        } while (p.contains("**"));
        count = new int[p.length()];
        int sum = 0;
        var pc = p.toCharArray();
        for (int i = count.length - 1; i >= 0; i--) {
            count[i] = sum;
            if (pc[i] != '*')
                sum++;
        }
        return tryAdvance(s.toCharArray(), pc, Kind.START, 0, 0);
    }

    Kind getPatternKind(char[] p, int ip) {
        if (ip >= p.length)
            return Kind.END;
        switch (p[ip]) {
            case '*':
                return Kind.SEQ;
            case '?':
                return Kind.ANY;
            default:
                return Kind.LETTER;
        }
    }

    boolean nextPattern(char[] s, char[] p, int ip, int is) {
        return tryAdvance(s, p, getPatternKind(p, ip), ip + 1, is);
    }

    /**
     * Tries one pattern. Returns if that was unsuccessful.
     *
     * @param kind - previous kind of pattern
     * @param ip   - current p pointer
     * @param is   - current s pointer
     */
    public boolean tryAdvance(char[] s, char[] p, Kind kind, int ip, int is) {
        /*
        start - define 1st pattern and go to the cycle.
        letter and any match to 1 symbol, if not - rollback.
        seq, anyseq match to the longest string until it does not. if not a match - go to the next pattern, if no more patterns - false
        (backtrack)
        end - no more string chars and no more pattern chars.
        exit criteria - end of string, not match for pattern, end of pattern (success if string is exhausted too, false otherwise)
         */
        switch (kind) {
            case START:
                if (ip != 0) throw new IllegalArgumentException("ip=" + ip);
                kind = getPatternKind(p, ip);
                if (kind == Kind.END)
                    return s.length == 0;
                return nextPattern(s, p, ip, is);
            case END:
                return is >= s.length;
            case LETTER, ANY:
                if (is >= s.length || (kind == Kind.LETTER && s[is] != p[ip - 1])) return false;
                return nextPattern(s, p, ip, is + 1);
            case SEQ:
                if (is >= s.length)
                    return count[ip - 1] == 0;
                if (count[ip - 1] == 0)
                    return true;
                for (int i = s.length - count[ip - 1]; i >= is; i--) {
                    //inc string
                    //inc pattern
                    if (nextPattern(s, p, ip, i)) {
                        return true;
                    }
                }
                return false;
            default:
                throw new RuntimeException("NO");
        }
    }
}
