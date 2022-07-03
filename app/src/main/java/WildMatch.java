import java.util.List;

/**
 * https://leetcode.com/problems/wildcard-matching/
 *
 * @author Alexey Shurygin
 */
public class WildMatch {
    enum Kind {
        START, //start
        LETTER,//letter
        ANY, //?
        SEQ, //*
        END //pattern ended
    }

    public boolean isMatch(String s, String p) {
//        System.out.print(p);
        do {
            p = p.replace("**", "*");
        } while (p.contains("**"));
//        System.out.println(" -> " + p);
        int[] count = new int[p.length()];
        int sum = 0;
        for (int i = count.length - 1; i >= 0; i--) {
            count[i] = sum;
            if (p.charAt(i) != '*')
                sum++;
        }
        return tryAdvance(s, p, Kind.START, (char) 0, 0, 0, count);
    }

    Kind getPatternKind(String p, int ip) {
        if (ip >= p.length())
            return Kind.END;
        switch (p.charAt(ip)) {
            case '*':
                return Kind.SEQ;
            case '?':
                return Kind.ANY;
            default:
                return Kind.LETTER;
        }
    }

    boolean nextPattern(String s, String p, int ip, int is, int[] count) {
        final Kind kind = getPatternKind(p, ip);
        switch (kind) {
            case START:
                throw new RuntimeException("START");
            case END:
                return tryAdvance(s, p, kind, (char) 0, ip + 1, is, count);
            case LETTER:
                return tryAdvance(s, p, kind, p.charAt(ip), ip + 1, is, count);
            case ANY:
                return tryAdvance(s, p, kind, (char) 0, ip + 1, is, count);
            case SEQ:
                return tryAdvance(s, p, kind, (char) 0, ip + 1, is, count);
        }
        throw new RuntimeException("NO");
    }

    /**
     * Tries one pattern. Returns if that was unsuccessful.
     *
     * @param kind  - previous kind of pattern
     * @param l     - letter for SEQ/LETTER
     * @param ip    - current p pointer
     * @param is    - current s pointer
     * @param count
     */
    public boolean tryAdvance(String s, String p, Kind kind, char l, int ip, int is, int[] count) {
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
                    return s.isEmpty();
                return nextPattern(s, p, ip, is, count);
            case END:
                return is >= s.length();
            case LETTER, ANY:
                if (is >= s.length() || (kind == Kind.LETTER && s.charAt(is) != l)) return false;
                return nextPattern(s, p, ip, is + 1, count);
            case SEQ:
                if (is >= s.length())
                    return nextPattern(s, p, ip, is, count);
                for (int i = s.length() - count[ip - 1]; i >= is; i--) {
                    //inc string
                    //inc pattern
                    if (nextPattern(s, p, ip, i, count)) {
                        return true;
                    }
                }
                return false;
            default:
                throw new RuntimeException("NO");
        }
    }
}
