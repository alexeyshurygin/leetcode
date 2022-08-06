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
    }

    @Test
    void test1() {
        assertEquals(0, i.minimumReplacement(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void test2() {
        assertEquals(6, i.minimumReplacement(new int[]{12, 9, 7, 6, 17, 19, 21}));
    }

    @Test
    void test3() {
        assertEquals(17748, i.minimumReplacement(new int[]{368, 112, 2, 282, 349, 127, 36, 98, 371, 79, 309, 221, 175, 262, 224, 215, 230, 250, 84, 269, 384, 328, 118, 97, 17, 105, 342, 344, 242, 160, 394, 17, 120, 335, 76, 101, 260, 244, 378, 375, 164, 190, 320, 376, 197, 398, 353, 138, 362, 38, 54, 172, 3, 300, 264, 165, 251, 24, 312, 355, 237, 314, 397, 101, 117, 268, 36, 165, 373, 269, 351, 67, 263, 332, 296, 13, 118, 294, 159, 137, 82, 288, 250, 131, 354, 261, 192, 111, 16, 139, 261, 295, 112, 121, 234, 335, 256, 303, 328, 242, 260, 346, 22, 277, 179, 223}));
    }
}
