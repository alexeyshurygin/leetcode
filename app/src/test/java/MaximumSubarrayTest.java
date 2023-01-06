import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alexey Shurygin
 */
class MaximumSubarrayTest {
    MaximumSubarray i;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        i = new MaximumSubarray();
    }

    @org.junit.jupiter.api.Test
    void t1() {
        assertEquals(6, i.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        assertEquals(0, i.maxSubArray(new int[]{}));
        assertEquals(1, i.maxSubArray(new int[]{1, 0}));
        assertEquals(1, i.maxSubArray(new int[]{0, 1}));
        assertEquals(2, i.maxSubArray(new int[]{0, 1, -1, 2}));
        assertEquals(3, i.maxSubArray(new int[]{0, 2, -1, 2}));
    }
}
