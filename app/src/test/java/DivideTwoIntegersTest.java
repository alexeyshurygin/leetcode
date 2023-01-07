import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alexey Shurygin
 */
class DivideTwoIntegersTest {
    DivideTwoIntegers i;

    @BeforeEach
    void setUp() {
        i = new DivideTwoIntegers();
    }

    @Test
    void divide() {
        assertEquals(3, i.divide(10, 3));
        assertEquals(-2, i.divide(7, -3));
        assertEquals(2147483647, i.divide(-2147483648, -1));
    }
}
