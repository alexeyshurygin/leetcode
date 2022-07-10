import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alexey Shurygin
 */
class IsPowerOfTwoTest {
    IsPowerOfTwo inst;

    @BeforeEach
    void setUp() {
        inst = new IsPowerOfTwo();
    }

    @Test
    void test1() {
        assertTrue(inst.isPowerOfTwo(1));
        assertTrue(inst.isPowerOfTwo(2));
        assertTrue(inst.isPowerOfTwo(4));
        assertTrue(inst.isPowerOfTwo(8));
        assertTrue(inst.isPowerOfTwo(65536));
        assertFalse(inst.isPowerOfTwo(0));
        assertFalse(inst.isPowerOfTwo(3));
        assertFalse(inst.isPowerOfTwo(10));
        assertFalse(inst.isPowerOfTwo(-2147483648));
    }
}
