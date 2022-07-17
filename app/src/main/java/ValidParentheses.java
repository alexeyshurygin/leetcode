import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/valid-parentheses/
 *
 * @author Alexey Shurygin
 */
class ValidParentheses {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(', '{', '[':
                    stack.push(c);
                    break;
                case ')', '}', ']':
                    if (stack.isEmpty())
                        return false;
                    char k = stack.pop();
                    switch (c) {
                        case ')':
                            if (k != '(') return false;
                            break;
                        case '}':
                            if (k != '{') return false;
                            break;
                        case ']':
                            if (k != '[') return false;
                            break;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }
}
