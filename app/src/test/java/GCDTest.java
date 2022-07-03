import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alexey Shurygin
 */
class GCDTest {

    private GCD g;

    @BeforeEach
    void setUp() {
        g = new GCD();
    }

    @Test
    void t1() {
        int r = g.generalizedGCD(5, new int[]{2, 3, 4, 5, 6});
        assertEquals(r, 1);
    }

    @Test
    void t2() {
        int r = g.generalizedGCD(5, new int[]{2, 4, 6, 8, 10});
        assertEquals(r, 2);
    }
    @Test
    void t3() {
        int r = g.generalizedGCD(5, new int[]{1, 4, 6, 8, 10});
        assertEquals(r, 1);
    }

    @Test
    void t4() {
        int r = g.generalizedGCD(5, new int[]{0, 4, 6, 8, 10});
        assertEquals(r, 0);
    }
    @Test
    void t5() {
        int r = g.generalizedGCD(0, new int[]{});
        assertEquals(r, 0);
    }

    @Test
    void t6() {
        int r = g.generalizedGCD(1, new int[]{1});
        assertEquals(r, 1);
    }
    @Test
    void t7() {
        int r = g.generalizedGCD(2, new int[]{3,5});
        assertEquals(r, 1);
    }

    @Test
    void t8() {
        int r = g.generalizedGCD(1, new int[]{3,5});
        assertEquals(r, 3);
    }
}
