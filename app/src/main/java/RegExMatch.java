import java.util.List;

/**
 * https://leetcode.com/problems/regular-expression-matching
 *
 * @author Alexey Shurygin
 */
public class RegExMatch {
    enum Kind {
        START, //start
        LETTER,//letter
        SEQ, //a*
        ANY, //.
        ANYSEQ, //.*
        END //pattern ended
    }

    public boolean isMatch(String s, String p) {
        return tryAdvance(s, p, Kind.START, (char) 0, 0, 0);
    }

    Kind getPatternKind(String p, int ip) {
        if (ip >= p.length()) {
            return Kind.END;
        }
        final char cp = p.charAt(ip);
        if (p.startsWith(".*", ip)) {
            return Kind.ANYSEQ;
        }
        if (cp == '.') {
            return Kind.ANY;
        }
        if (p.length() > ip + 1 && p.charAt(ip + 1) == '*') {
            return Kind.SEQ;
        }
        return Kind.LETTER;
    }

    boolean nextPattern(String s, String p, int ip, int is) {
        final Kind kind = getPatternKind(p, ip);
        switch (kind) {
            case START:
                throw new RuntimeException("START");
            case END:
                return tryAdvance(s, p, kind, (char) 0, ip + 1, is);
            case LETTER:
                return tryAdvance(s, p, kind, p.charAt(ip), ip + 1, is);
            case ANY:
                return tryAdvance(s, p, kind, (char) 0, ip + 1, is);
            case SEQ:
                return tryAdvance(s, p, kind, p.charAt(ip), ip + 2, is);
            case ANYSEQ:
                return tryAdvance(s, p, kind, (char) 0, ip + 2, is);
        }
        throw new RuntimeException("NO");
    }

    /**
     * Tries one pattern. Returns if that was unsuccessful.
     *
     * @param kind - previous kind of pattern
     * @param l    - letter for SEQ/LETTER
     * @param ip   - current p pointer
     * @param is   - current s pointer
     */
    public boolean tryAdvance(String s, String p, Kind kind, char l, int ip, int is) {
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
                return nextPattern(s, p, ip, is);
            case END:
                return is >= s.length();
            case LETTER, ANY:
                if (is >= s.length() || (kind == Kind.LETTER && s.charAt(is) != l)) return false;
                return nextPattern(s, p, ip, is + 1);
            case SEQ, ANYSEQ:
                if (is >= s.length())
                    return nextPattern(s, p, ip, is);
                if (kind == Kind.ANYSEQ || s.charAt(is) == l) {
                    //inc string
                    boolean result = tryAdvance(s, p, kind, l, ip, is + 1);
                    if (result) {
                        return result;
                    } else {
                        //inc pattern
                        return nextPattern(s, p, ip, is);
                    }
                } else {
                    //inc pattern
                    return nextPattern(s, p, ip, is);
                }
            default:
                throw new RuntimeException("NO");
        }
    }

    record T(String s, String p, boolean r) {
    }

    public static void main(String[] args) {
        var sol = new RegExMatch();
        List<T> tests = List.of(new T("", "", true),
                new T("a", "", false),
                new T("", "a", false),
                new T("a", "a", true),
                new T("abc", "abc", true),
                new T("aabc", "aabc", true),
                new T("aabc", "abc", false),
                new T("abc", "aabc", false),
                new T("ab", "abc", false),
                new T("abc", "ab", false),
                new T("a", "a*", true),
                new T("a", ".*", true),
                new T("aaa", "a*", true),
                new T("aaa", ".*", true),
                new T("aaabbb", ".*", true),
                new T("aaabbb", "a*.*", true),
                new T("aaabbb", ".*b*", true),
                new T("aaabbb", "a*b*", true),
                new T("aaabbb", "b*", false),
                new T("aaabbb", "a*", false),
                new T("a", "b", false),
                new T("ab", "a", false),
                new T("ab", "a*", false),
                new T("b", "a*", false),
                new T("a", "a*", true),
                new T("ab", "a*b", true),
                new T("aab", "a*b", true),
                new T("aab", "c*a*b", true),
                new T("mississippi", "mis*is*ip*.", true),
                new T("aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*", true),
                new T("a", ".*", true),
                new T("ab", "a.*", true),
                new T("ab", ".*b*", true),
                new T("ab", ".*b", true),
                new T("ab", ".*", true),
                new T("aba", ".*b.*", true),
                new T("a", ".", true),
                new T("ab", ".", false),
                new T("ab", "a.", true),
                new T("ab", ".b", true),
                new T("abcd", ".*a.*", true),
                new T("abcd", ".*a.*.", true),
                new T("abcd", ".*a.*.*", true),
                new T("abcd", "..*c.*", true)
        );
        tests.parallelStream().filter(t -> sol.isMatch(t.s, t.p) != t.r).forEach(System.out::println);
    }
}
