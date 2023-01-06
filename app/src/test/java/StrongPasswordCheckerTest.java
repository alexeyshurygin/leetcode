import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alexey Shurygin
 */
class StrongPasswordCheckerTest {
    StrongPasswordChecker i;

    @BeforeEach
    void setUp() {
        i = new StrongPasswordChecker();
    }

    @Test
    void strongPasswordChecker() {
        assertEquals(5, i.strongPasswordChecker("a"));
        assertEquals(3, i.strongPasswordChecker("aA1"));
        assertEquals(0, i.strongPasswordChecker("1337C0d3"));
        assertEquals(2, i.strongPasswordChecker("aaa111"));
        assertEquals(2, i.strongPasswordChecker("ABABABABABABABABABAB1"));
        assertEquals(8, i.strongPasswordChecker("bbaaaaaaaaaaaaaaacccccc"));
    }
}
