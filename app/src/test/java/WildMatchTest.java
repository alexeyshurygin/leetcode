import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

/**
 * @author Alexey Shurygin
 */
//@Execution(CONCURRENT)
class WildMatchTest {
    private WildMatch i;

    @BeforeEach
    void setUp() {
        i = new WildMatch();
    }

    @Test
    void isMatch0() {
        assertEquals(false, i.isMatch("aaabbb", "*a"));
    }

    @Test
    void isMatch1() {
        assertEquals(true, i.isMatch("abc", "*a*"));
    }

    @Test
    void isMatch2() {
        assertEquals(true, i.isMatch("adceb", "*a*b"));
    }

    @Test
    void isMatch3() {
        assertEquals(true, i.isMatch("ab", "*b"));
    }

    @Test
    void isMatch4() {
        assertEquals(true, i.isMatch("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba", "*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*"));
    }

    @Test
    void isMatch5() {
        assertEquals(false, i.isMatch("a", ""));
    }

    @Test
    void isMatch6() {
        assertEquals(false, i.isMatch("", "a"));
    }

    @Test
    void isMatch7() {
        assertEquals(true, i.isMatch("a", "a"));
    }

    @Test
    void isMatch8() {
        assertEquals(true, i.isMatch("abc", "abc"));
    }

    @Test
    void isMatch9() {
        assertEquals(true, i.isMatch("aabc", "aabc"));
    }

    @Test
    void isMatch10() {
        assertEquals(false, i.isMatch("aabc", "abc"));
    }

    @Test
    void isMatch11() {
        assertEquals(false, i.isMatch("abc", "aabc"));
    }

    @Test
    void isMatch12() {
        assertEquals(false, i.isMatch("ab", "abc"));
    }

    @Test
    void isMatch13() {
        assertEquals(false, i.isMatch("abc", "ab"));
    }

    @Test
    void isMatch14() {
        assertEquals(true, i.isMatch("a", "a*"));
    }

    @Test
    void isMatch15() {
        assertEquals(true, i.isMatch("a", "*"));
    }

    @Test
    void isMatch16() {
        assertEquals(true, i.isMatch("aaa", "a*"));
    }

    @Test
    void isMatch17() {
        assertEquals(true, i.isMatch("aaa", "*"));
    }

    @Test
    void isMatch18() {
        assertEquals(true, i.isMatch("aaabbb", "*"));
    }

    @Test
    void isMatch19() {
        assertEquals(true, i.isMatch("aaabbb", "a**"));
    }

    @Test
    void isMatch20() {
        assertEquals(true, i.isMatch("aaabbb", "*b*"));
    }

    @Test
    void isMatch21() {
        assertEquals(true, i.isMatch("aaabbb", "a*b*"));
    }

    @Test
    void isMatch22() {
        assertEquals(false, i.isMatch("aaabbb", "b*"));
    }

    @Test
    void isMatch23() {
        assertEquals(false, i.isMatch("aaabbb", "*a"));
    }

    @Test
    void isMatch24() {
        assertEquals(false, i.isMatch("a", "b"));
    }

    @Test
    void isMatch25() {
        assertEquals(false, i.isMatch("ab", "a"));
    }

    @Test
    void isMatch26() {
        assertEquals(true, i.isMatch("ab", "a*"));
    }

    @Test
    void isMatch27() {
        assertEquals(false, i.isMatch("b", "a*"));
    }

    @Test
    void isMatch28() {
        assertEquals(true, i.isMatch("a", "a*"));
    }

    @Test
    void isMatch29() {
        assertEquals(true, i.isMatch("ab", "a*b"));
    }

    @Test
    void isMatch30() {
        assertEquals(true, i.isMatch("aab", "a*b"));
    }

    @Test
    void isMatch31() {
        assertEquals(true, i.isMatch("aab", "**b"));
    }

    @Test
    void isMatch32() {
        assertEquals(true, i.isMatch("mississippi", "mis*is*ip*?"));
    }

    @Test
    void isMatch33() {
        assertEquals(true, i.isMatch("aabcbcbcaccbcaabc", "**a**b*?c**a*"));
    }

    @Test
    void isMatch34() {
        assertEquals(true, i.isMatch("a", "*"));
    }

    @Test
    void isMatch35() {
        assertEquals(true, i.isMatch("ab", "a*"));
    }

    @Test
    void isMatch36() {
        assertEquals(true, i.isMatch("ab", "*b*"));
    }

    @Test
    void isMatch37() {
        assertEquals(true, i.isMatch("ab", "*b"));
    }

    @Test
    void isMatch38() {
        assertEquals(true, i.isMatch("ab", "*"));
    }

    @Test
    void isMatch39() {
        assertEquals(true, i.isMatch("aba", "*b*"));
    }

    @Test
    void isMatch40() {
        assertEquals(true, i.isMatch("a", "?"));
    }

    @Test
    void isMatch41() {
        assertEquals(false, i.isMatch("ab", "?"));
    }

    @Test
    void isMatch42() {
        assertEquals(true, i.isMatch("ab", "a?"));
    }

    @Test
    void isMatch43() {
        assertEquals(true, i.isMatch("ab", "?b"));
    }

    @Test
    void isMatch44() {
        assertEquals(true, i.isMatch("abcd", "*a*"));
    }

    @Test
    void isMatch45() {
        assertEquals(true, i.isMatch("abcd", "*a*?"));
    }

    @Test
    void isMatch46() {
        assertEquals(true, i.isMatch("abcd", "*a**"));
    }

    @Test
    void isMatch47() {
        assertEquals(true, i.isMatch("abcd", "?*c*"));
    }

    @Test
    void isMatch48() {
        assertEquals(true, i.isMatch("", ""));
    }

    @Test
    void isMatch49() {
        assertEquals(false, i.isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb",
                "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));
    }

    @Test
    void isMatch50() {
        assertEquals(true, i.isMatch("adceb", "*a*b"));
    }
}
