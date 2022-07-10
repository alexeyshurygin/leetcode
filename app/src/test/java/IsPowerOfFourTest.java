import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alexey Shurygin
 */
class IsPowerOfFourTest {
    IsPowerOfFour inst;

    @BeforeEach
    void setUp() {
        inst = new IsPowerOfFour();
    }

    @Test
    void test1() {
        assertTrue(inst.isPowerOfFour(1));
        assertFalse(inst.isPowerOfFour(2));
        assertTrue(inst.isPowerOfFour(4));
        assertFalse(inst.isPowerOfFour(8));
        assertTrue(inst.isPowerOfFour(65536));
        assertFalse(inst.isPowerOfFour(0));
        assertFalse(inst.isPowerOfFour(3));
        assertFalse(inst.isPowerOfFour(10));
        assertFalse(inst.isPowerOfFour(-2147483648));
    }
}
