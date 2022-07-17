import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 *
 * @author Alexey Shurygin
 */
public class LongestValidParentheses {
    record K(char c, int depth, int length) {
    }

    public int longestValidParentheses(String s) {
        Deque<K> stack = new ArrayDeque<>();
        int length = 0;
        int max = 0;
        int depth = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                    stack.push(new K(c, depth++, length));
                    length = 0;
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        max = Math.max(max, length);
                        length = 0;
                        depth = 0;
                        break;
                    }
                    K k = stack.pop();
                    if (k.c == '(') {
                        //hit
                        depth--;
                        length += 2;
                        if (k.depth == depth) {
                            length += k.length;
                        }
                        max = Math.max(max, length);
                    } else {
                        max = Math.max(max, length);
                        length = 0;
                        depth = 0;
                    }
                    break;
            }
        }
        max = Math.max(max, length);
        return max;
    }
}
