import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alexey Shurygin
 */
class MinReplTest {
    MinRepl i;

    @BeforeEach
    void setUp() {
        i = new MinRepl();
    }

    @Test
    void test0() {
        assertEquals(2, i.minimumReplacement(new int[]{3, 9, 3}));
        assertEquals(0, i.minimumReplacement(new int[]{1, 2, 3, 4, 5}));
    }
}
