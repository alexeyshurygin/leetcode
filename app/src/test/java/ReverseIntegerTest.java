import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Alexey Shurygin
 */
class ReverseIntegerTest {
    ReverseInteger i;

    @BeforeEach
    void setUp() {
        i = new ReverseInteger();
    }

    @Test
    void reverse() {
        Assertions.assertEquals(321, i.reverse(123));
        Assertions.assertEquals(-321, i.reverse(-123));
        Assertions.assertEquals(0, i.reverse(1534236469));
    }
}
