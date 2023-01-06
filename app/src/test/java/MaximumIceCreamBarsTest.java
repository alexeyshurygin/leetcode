import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alexey Shurygin
 */
class MaximumIceCreamBarsTest {
    MaximumIceCreamBars i;

    @BeforeEach
    void setUp() {
        i = new MaximumIceCreamBars();
    }

    @Test
    void maxIceCream() {
        assertEquals(4, i.maxIceCream(new int[]{1, 3, 2, 4, 1}, 7));
        assertEquals(0, i.maxIceCream(new int[]{10, 6, 8, 7, 7, 8}, 5));
        assertEquals(6, i.maxIceCream(new int[]{1, 6, 3, 1, 2, 5}, 20));
    }
}
