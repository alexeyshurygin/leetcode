import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alexey Shurygin
 */
class SolutionTest {
    Solution i;

    @BeforeEach
    void setUp() {
        i = new Solution();
    }

    @Test
    void test0() {
        assertEquals(0, i.longestValidParentheses(""));
    }

    @Test
    void test1() {
        assertEquals(2, i.longestValidParentheses("()"));
    }

    @Test
    void test2() {
        assertEquals(4, i.longestValidParentheses("()()"));
    }

    @Test
    void test3() {
        assertEquals(4, i.longestValidParentheses("(()()"));
    }

    @Test
    void test33() {
        assertEquals(2, i.longestValidParentheses("(()"));
    }

    @Test
    void test4() {
        assertEquals(4, i.longestValidParentheses(")()())"));
    }

    @Test
    void test5() {
        assertEquals(2, i.longestValidParentheses("()(()"));
    }

    @Test
    void test6() {
        assertEquals(2, i.longestValidParentheses("(()(((()"));
    }

    @Test
    void test7() {
        assertEquals(6, i.longestValidParentheses("((()))"));
    }

    @Test
    void test8() {
        assertEquals(8, i.longestValidParentheses("((()()))"));
    }

    @Test
    void test9() {
        assertEquals(4, i.longestValidParentheses("(()()(()"));
    }

    @Test
    void test10() {
        assertEquals(12, i.longestValidParentheses("(((((())))))"));
    }

    @Test
    void test11() {
        assertEquals(4, i.longestValidParentheses("(())"));
    }

    @Test
    void test12() {
        assertEquals(8, i.longestValidParentheses("()()()()"));
    }

    @Test
    void test13() {
        assertEquals(10, i.longestValidParentheses("(((())(()))"));
    }

    @Test
    void test15() {
        assertEquals(4, i.longestValidParentheses("(()))"));
    }

    @Test
    void test16() {
        assertEquals(4, i.longestValidParentheses(")(()))"));
    }

    @Test
    void test17() {
        assertEquals(4, i.longestValidParentheses("\"(())(\""));
    }
}
